package com.synectiks.cms.business.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.graphql.types.Student.Semester;
import com.synectiks.cms.repository.*;
import com.synectiks.cms.service.util.CommonUtil;

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
		if(CommonUtil.isNullOrEmpty(academicYear)) {
			return null;
		}
		AcademicYear ay = new AcademicYear();
		ay.setYear(academicYear);
		Example<AcademicYear> example = Example.of(ay);
		Optional<AcademicYear> acd = this.academicYearRepository.findOne(example);
		if(acd.isPresent()) {
			return acd.get();
		}
		return null;
	}
	
	public AcademicYear getAcademicYearById(Long academicYearId) {
		if(academicYearId == null) {
			return null;
		}
		Optional<AcademicYear> newAy = this.academicYearRepository.findById(academicYearId);
		if(newAy.isPresent()) {
			return newAy.get();
		}
		return null;
	}
	
	public Department getDepartmentById(Long departmentId) {
		if(departmentId == null) {
			return null;
		}
		Optional<Department> newDt = this.departmentRepository.findById(departmentId);
		if(newDt.isPresent()) {
			return newDt.get();
		}
		return null;
	}
	
	public Batch getBatchById(Long batchId) {
		if(batchId == null) {
			return null;
		}
		Optional<Batch> newBt = this.batchRepository.findById(batchId);
		if(newBt.isPresent()) {
			return newBt.get();
		}
		return null;
	}
	
	public Teacher getTeacherById(Long teacherId) {
		if(teacherId == null) {
			return null;
		}
		Optional<Teacher> newTh = this.teacherRepository.findById(teacherId);
		if(newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}

	public Section getSectionById(Long secId) {
		if(secId == null) {
			return null;
		}
		Optional<Section> newSc = this.sectionRepository.findById(secId);
		if(newSc.isPresent()) {
			return newSc.get();
		}
		return null;
	}
	
	public Subject getSubjectById(Long subId) {
		if(subId == null) {
			return null;
		}
		Optional<Subject> newSb = this.subjectRepository.findById(subId);
		if(newSb.isPresent()) {
			return newSb.get();
		}
		return null;
	}
	
	public Teach getTeachBySubjectAndTeacherId(Long thrId, Long subId) {
		if(thrId == null || subId == null) {
			return null;
		}
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
		if(id == null) {
			return null;
		}
		Optional<AttendanceMaster> newAm = this.attendanceMasterRepository.findById(id);
		if(newAm.isPresent()) {
			return newAm.get();
		}
		return null;
	}
	
	public List<Holiday> getHolidayList(String academicYear) throws ParseException {
		AcademicYear acd = findAcademicYearByYear(academicYear);
		if(acd == null) {
			logger.warn("Academic Year is null. Returning empty holiday list.");
			return Collections.emptyList();
		}
		Holiday hl = new Holiday();
		hl.setHolidayStatus(Status.ACTIVE);
		hl.setAcademicyear(acd);
		Example<Holiday> example = Example.of(hl);
		List<Holiday> list = this.holidayRepository.findAll(example);
		return list;
	} 
	
	public Term getTermById(Long termId) {
		if(termId == null) {
			return null;
		}
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
		if(id == null) {
			return null;
		}
		Optional<College> clg =  this.collegeRepository.findById(id);
		if(clg.isPresent()) {
			return clg.get();
		}
		return null;
	}
	
	public State getStateById(Long id) {
		if(id == null) {
			return null;
		}
		Optional<State> st =  this.stateRepository.findById(id);
		if(st.isPresent()) {
			return st.get();
		}
		return null;
	}
	
	public City getCityById(Long id) {
		if(id == null) {
			return null;
		}
		Optional<City> ct =  this.cityRepository.findById(id);
		if(ct.isPresent()) {
			return ct.get();
		}
		return null;
	}
	public Branch getBranchById(Long id) {
		if(id == null) {
			return null;
		}
		Optional<Branch> bt =  this.branchRepository.findById(id);
		if(bt.isPresent()) {
			return bt.get();
		}
		return null;
	}
	public Teach getTeachById(Long id) {
		if(id == null) {
			return null;
		}
		Optional<Teach> th =  this.teachRepository.findById(id);
		if(th.isPresent()) {
			return th.get();
		}
		return null;
	}
	
	public List<Department> getDepartmentsByBranchAndAcademicYear(Long branchId, Long academicYearId){
		if(branchId == null || academicYearId == null) {
			return null;
		}
		Department department = new Department();
		Branch branch = this.getBranchById(branchId);
		AcademicYear ay = this.getAcademicYearById(academicYearId);
		department.setBranch(branch);
		department.setAcademicyear(ay);
		Example<Department> example = Example.of(department);
		List<Department> list = this.departmentRepository.findAll(example);
		return list;
	}
	
	public List<CmsSemesterVo> getAllSemesters() {
        logger.debug("Retrieving all semesters");
        List<CmsSemesterVo> ls = new ArrayList<>();
        for(Semester sm: Semester.values()) {
        	CmsSemesterVo vo = new CmsSemesterVo();
        	vo.setId(sm.value());
        	vo.setDescription(sm.getDescription());
        	ls.add(vo);
        }
        return ls;
    }
	
	public CmsSemesterVo getSemester(Long id) {
		Semester sm = Semester.valueOf(id.intValue());
        CmsSemesterVo vo = new CmsSemesterVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
	}
} 
