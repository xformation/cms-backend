package com.synectiks.cms.business.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.TeacherRepository;

@Component
public class CommonService {

	private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
	@Autowired
	private AcademicYearRepository academicYearRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public AcademicYear findAcademicYearByYear(long academicYear) {
		AcademicYear ay = new AcademicYear();
		ay.setYear(academicYear);
		Example<AcademicYear> example = Example.of(ay);
		Optional<AcademicYear> acd = this.academicYearRepository.findOne(example);
		if(acd.isPresent()) {
			return acd.get();
		}
		return null;
	}
	
	public Department getDepartmentById(Long departmentId) {
		Department dept = new Department();
		dept.setId(departmentId);;
		Example<Department> example = Example.of(dept);
		Optional<Department> newDt = this.departmentRepository.findOne(example);
		if(newDt.isPresent()) {
			return newDt.get();
		}
		return null;
	}
	
	public Batch getBatchById(Long batchId) {
		Batch batch = new Batch();
		batch.setId(batchId);;
		Example<Batch> example = Example.of(batch);
		Optional<Batch> newBt = this.batchRepository.findOne(example);
		if(newBt.isPresent()) {
			return newBt.get();
		}
		return null;
	}
	
	public Teacher getTeacherById(Long teacherId) {
		Teacher tchr = new Teacher();
		tchr.setId(teacherId);;
		Example<Teacher> example = Example.of(tchr);
		Optional<Teacher> newTh = this.teacherRepository.findOne(example);
		if(newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}
	
}
