package com.synectiks.cms.business.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

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

	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private TeachRepository teachRepository;
	
	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;
	
	@Autowired
	private HolidayRepository holidayRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	
//    @Autowired
//    private StudentRepository studentRepository;
	
	public AcademicYear findAcademicYearByYear(String academicYear) {
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
//		Department dept = new Department();
//		dept.setId(departmentId);
//		Example<Department> example = Example.of(dept);
		Optional<Department> newDt = this.departmentRepository.findById(departmentId);
		if(newDt.isPresent()) {
			return newDt.get();
		}
		return null;
	}
	
	public Batch getBatchById(Long batchId) {
//		Batch batch = new Batch();
//		batch.setId(batchId);
//		Example<Batch> example = Example.of(batch);
		Optional<Batch> newBt = this.batchRepository.findById(batchId);
		if(newBt.isPresent()) {
			return newBt.get();
		}
		return null;
	}
	
	public Teacher getTeacherById(Long teacherId) {
//		Teacher tchr = new Teacher();
//		tchr.setId(teacherId);
//		Example<Teacher> example = Example.of(tchr);
		Optional<Teacher> newTh = this.teacherRepository.findById(teacherId);
		if(newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}

	public Section getSectionById(Long secId) {
//		Section sc = new Section();
//		sc.setId(secId);
//		Example<Section> example = Example.of(sc);
		Optional<Section> newSc = this.sectionRepository.findById(secId);
		if(newSc.isPresent()) {
			return newSc.get();
		}
		return null;
	}
	
	public Subject getSubjectById(Long subId) {
//		Subject sb = new Subject();
//		sb.setId(subId);
//		Example<Subject> example = Example.of(sb);
		Optional<Subject> newSb = this.subjectRepository.findById(subId);
		if(newSb.isPresent()) {
			return newSb.get();
		}
		return null;
	}
	
	public Teach getTeachBySubjectAndTeacherId(Long thrId, Long subId) {
		Teach th = new Teach();
		Subject s = getSubjectById(subId);
		Teacher t = getTeacherById(thrId);
		th.setSubject(s);
		th.setTeacher(t);
		Example<Teach> example = Example.of(th);
		Optional<Teach> newTh = this.teachRepository.findOne(example);
		if(newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}
	
	public AttendanceMaster getAttendanceMasterByBatchSectionTeach(Batch bt, Section sc, Teach th) {
		AttendanceMaster am = new AttendanceMaster();
		am.setBatch(bt);
		am.setSection(sc);
		am.setTeach(th);
		Example<AttendanceMaster> example = Example.of(am);
		Optional<AttendanceMaster> newAm = this.attendanceMasterRepository.findOne(example);
		if(newAm.isPresent()) {
			return newAm.get();
		}
		return null;
	}
	
	public List<AttendanceMaster> getAttendanceMasterByBatchSection(Batch bt, Section sc) {
		AttendanceMaster am = new AttendanceMaster();
		am.setBatch(bt);
		am.setSection(sc);
		Example<AttendanceMaster> example = Example.of(am);
		List<AttendanceMaster> newAm = this.attendanceMasterRepository.findAll(example);
		return newAm;
	}
	
	public AttendanceMaster getAttendanceMasterById(Long id) {
		Optional<AttendanceMaster> newAm = this.attendanceMasterRepository.findById(id);
		if(newAm.isPresent()) {
			return newAm.get();
		}
		return null;
	}
	
	public List<Holiday> getHolidayList(String academicYear) throws ParseException {
		AcademicYear acd = findAcademicYearByYear(academicYear);
		Holiday hl = new Holiday();
		hl.setHolidayStatus(Status.ACTIVE);
		hl.setAcademicyear(acd);
		Example<Holiday> example = Example.of(hl);
		List<Holiday> list = this.holidayRepository.findAll(example);
		return list;
	} 
	
	public Term getTermById(Long termId) {
		Term tm = new Term();
		tm.setTermStatus(Status.ACTIVE);
		tm.setId(termId);
		Example<Term> example = Example.of(tm);
		Optional<Term> term = this.termRepository.findOne(example);
		if(term.isPresent()) {
			return term.get();
		}
		return null;
	}
	
	public College getCollegeById(Long id) {
		Optional<College> clg =  this.collegeRepository.findById(id);
		if(clg.isPresent()) {
			return clg.get();
		}
		return null;
	}
	
	public State getStateById(Long id) {
		Optional<State> st =  this.stateRepository.findById(id);
		if(st.isPresent()) {
			return st.get();
		}
		return null;
	}
	
	public City getCityById(Long id) {
		Optional<City> ct =  this.cityRepository.findById(id);
		if(ct.isPresent()) {
			return ct.get();
		}
		return null;
	}
	public Branch getBranchById(Long id) {
		Optional<Branch> bt =  this.branchRepository.findById(id);
		if(bt.isPresent()) {
			return bt.get();
		}
		return null;
	}
	public Teach getTeachById(Long id) {
		Optional<Teach> th =  this.teachRepository.findById(id);
		if(th.isPresent()) {
			return th.get();
		}
		return null;
	}
} 
