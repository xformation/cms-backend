package com.synectiks.cms.influx;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;

@Component
public class StudentInfluxPush implements InfluxPush {

	private final Logger logger = LoggerFactory.getLogger(StudentInfluxPush.class);
	
	private InfluxDB influxDB;	
	
	@Autowired
	private InfluxDbDataSource influxDbDataSource;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
//	@Autowired
//	private StudentRepository studentRepository;
//	
//	@Autowired
//	private SubjectRepository subjectRepository;
//	
//	@Autowired
//	private StudentAttendanceRepository studentAttendanceRepository;
//	
//	@Autowired
//	private LectureRepository lectureRepository;
	
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public void pushData() throws ParseException, Exception {
		List<Student> list = this.commonService.getAllStudentsOfCurrentAcademicYear();
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;
		for(Student st: list) {
			List<Subject> subList = this.commonService.getAllSubjectsOfStudent(st);
			long totalLecturesScheduled = this.commonService.getTotalLecturesScheduledForStudent(st);
			List<StudentAttendance> lecConductedList = this.commonService.getTotalLecturesConductedForStudent(st, LocalDate.now());
			int totalPresent = 0;
			int totalAbsent = 0;
			for(StudentAttendance sa : lecConductedList ) {
				if(AttendanceStatusEnum.PRESENT.equals(sa.getAttendanceStatus())) {
					totalPresent++;
				}else {
					totalAbsent++;
				}
			}
			long tm = System.currentTimeMillis();
			for(Subject sub: subList) {
				point = Point.measurement("Student")
						.tag("TstudentName", st.getStudentName())
						.tag("TstudentEmail", st.getStudentPrimaryEmailId())
						.tag("Tdepartment", st.getDepartment().getName())
						.tag("Tyear", st.getBatch().getBatch().toString())
						.tag("Tsubject", sub.getSubjectCode())
						.addField("StudentName", st.getStudentName())
						.addField("StudentEmail", st.getStudentPrimaryEmailId())
						.addField("Department", st.getDepartment().getName())
						.addField("Year", st.getBatch().getBatch().toString())
						.addField("Subject", sub.getSubjectCode())
						.addField("TotalLecturesScheduled", totalLecturesScheduled)
						.addField("TotalLecturesConducted", lecConductedList.size())
						.addField("TotalPresent", totalPresent)
						.addField("TotalAbsent", totalAbsent)
						.addField("time", tm)
						
//						.addField("lectureTime", DateFormatUtil.converUtilDateFromLocaDate(sa.getLecture().getLecDate()).getTime())
//						.addField("StudentId", sa.getStudent().getId())
//						
//						.addField("AttendanceDate", DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(sa.getLecture().getLecDate()))))
//						.addField("AttendanceStatus", sa.getAttendanceStatus().toString().equalsIgnoreCase("PRESENT") ? 0 : 1)
//						.addField("Teacher", sa.getLecture().getAttendancemaster().getTeach().getTeacher().getTeacherName())
						.build();
				influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
			}
			
		}
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
	
}
