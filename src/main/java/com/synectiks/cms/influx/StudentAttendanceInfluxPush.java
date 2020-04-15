package com.synectiks.cms.influx;

import java.text.ParseException;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.CmsLectureVo;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class StudentAttendanceInfluxPush implements InfluxPush {

	@Autowired
	private InfluxDbDataSource influxDbDataSource;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	private InfluxDB influxDB;
	
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@Autowired
    private CommonService commonService;
	
	@Override
	public void pushData() throws ParseException, Exception {
		
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;
		AcademicYear ay = this.commonService.getActiveAcademicYear();
		String fromDate = DateFormatUtil.changeLocalDateFormat(ay.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
		String toDate = DateFormatUtil.changeLocalDateFormat(ay.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy);
		List<CmsLectureVo> lecList = this.commonService.getAllCmsLectures(fromDate, toDate);
		for(CmsLectureVo vo : lecList) {
			StudentAttendance sat = new StudentAttendance();
			sat.setLectureId(vo.getId());
			List<StudentAttendance> list = studentAttendanceRepository.findAll(Example.of(sat));
			for(StudentAttendance sa: list) {
				point = Point.measurement("StudentAttendance")
						.addField("StudentId", sa.getStudent().getId())
						.addField("StudentName", sa.getStudent().getStudentName())
						.addField("AttendanceDate", vo.getStrLecDate())
						.addField("AttendanceStatus", sa.getAttendanceStatus().toString().equalsIgnoreCase("PRESENT") ? 0 : 1)
						.addField("Subject", vo.getAttendancemaster().getTeach().getSubject().getSubjectDesc())
						.addField("Teacher", vo.getAttendancemaster().getTeach().getTeacher().getTeacherName())
						.build();
				influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
			}
		}
		
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
}
