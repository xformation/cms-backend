package com.synectiks.cms.influx;

import java.text.ParseException;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;
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
	
	@Override
	public void pushData() throws ParseException, Exception {
		List<StudentAttendance> list = studentAttendanceRepository.findAll();
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;
		for(StudentAttendance sa: list) {
			point = Point.measurement("StudentAttendance")
					.addField("StudentId", sa.getStudent().getId())
					.addField("StudentName", sa.getStudent().getStudentName())
					.addField("AttendanceDate", DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(sa.getLecture().getLecDate()))))
					.addField("AttendanceStatus", sa.getAttendanceStatus().toString())
					.addField("Subject", sa.getLecture().getAttendancemaster().getTeach().getSubject().getSubjectDesc())
					.addField("Teacher", sa.getLecture().getAttendancemaster().getTeach().getTeacher().getTeacherName())
					.build();
			influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
		}
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
}
