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
import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.entities.Lecture;
import com.synectiks.cms.entities.Teach;
import com.synectiks.cms.entities.Teacher;

@Component
public class TeacherInfluxPush implements InfluxPush {

	private final Logger logger = LoggerFactory.getLogger(TeacherInfluxPush.class);
	
	private InfluxDB influxDB;	
	
	@Autowired
	private InfluxDbDataSource influxDbDataSource;
	
	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private CommonService commonService;
	
	@Override
	public void pushData() throws ParseException, Exception {
		List<Teacher> list = this.commonService.getAllActiveTeachers();
		influxDB = influxDbDataSource.getInfluxDatabase();
		AcademicYear ay = this.commonService.getActiveAcademicYear();
		Point point = null;
		for(Teacher th: list) {
			List<Teach> subjectList = this.commonService.getAllSubjectsOfTeacher(th);
			long tm = System.currentTimeMillis();
			for(Teach teach: subjectList) {
				long totalLecturesScheduled = this.commonService.getTotalLecturesScheduledForTeacher(th, teach.getSubject());
				List<Lecture> lecConductedList = this.commonService.getTotalLecturesConductedForTeacher(th, teach.getSubject(), LocalDate.now());
//				
				point = Point.measurement("Teacher")
						.tag("TteacherName", th.getTeacherName())
						.tag("TteacherEmail", th.getTeacherEmailAddress())
						.tag("Tdepartment", th.getDepartment().getName())
						.tag("Tsubject", teach.getSubject().getSubjectCode())
						.tag("TsubjectGroup", teach.getSubject().getSubjectCode())
						.addField("TeacherName", th.getTeacherName())
						.addField("TeacherEmail", th.getTeacherEmailAddress())
						.addField("Department", th.getDepartment().getName())
						.addField("Subject", teach.getSubject().getSubjectCode())
						.addField("TotalLecturesScheduled", totalLecturesScheduled)
						.addField("TotalLecturesConducted", lecConductedList.size())
						.addField("time", tm)
						.build();
				influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
			}
			
		}
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
	
}
