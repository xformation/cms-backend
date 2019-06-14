package com.synectiks.cms.business.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.CmsGenderVo;
import com.synectiks.cms.domain.CmsSemesterVo;
import com.synectiks.cms.domain.CmsStudentTypeVo;
import com.synectiks.cms.domain.CmsTermVo;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.graphql.types.Student.Semester;
import com.synectiks.cms.graphql.types.Student.StudentType;
import com.synectiks.cms.graphql.types.gender.Gender;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class CommonService {

	private final static Logger logger = LoggerFactory.getLogger(CommonService.class);
	
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
	
	@PersistenceContext
    private EntityManager entityManager;
	
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
	
	public List<Holiday> getHolidayList(Optional<AcademicYear> oay) throws ParseException  {
//		AcademicYear acd = findAcademicYearByYear(academicYear);
		if(!oay.isPresent()) {
			logger.warn("Academic Year is null. Returning empty holiday list.");
			return Collections.emptyList();
		}
		Holiday hl = new Holiday();
		hl.setHolidayStatus(Status.ACTIVE);
		hl.setAcademicyear(oay.get());
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
			Collections.emptyList();
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
	
	public List<CmsStudentTypeVo> getAllStudentTypes() {
        logger.debug("Retrieving all student types");
        List<CmsStudentTypeVo> ls = new ArrayList<>();
        for(StudentType sm: StudentType.values()) {
        	CmsStudentTypeVo vo = new CmsStudentTypeVo();
        	vo.setId(sm.value());
        	vo.setDescription(sm.getDescription());
        	ls.add(vo);
        }
        return ls;
    }
	
	public CmsStudentTypeVo getStudentType(Long id) {
		StudentType sm = StudentType.valueOf(id.intValue());
		CmsStudentTypeVo vo = new CmsStudentTypeVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
	}
	
	public CmsStudentTypeVo getStudentTypeByDescription(String studentTypeDescription) {
		StudentType sm = StudentType.getStudentTypeOnDescription(studentTypeDescription);
		CmsStudentTypeVo vo = new CmsStudentTypeVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
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
	
	public List<Branch> getBranchForCriteria(Long collegeId){
		if(collegeId == null) {
			logger.warn("College id is null. Returning empty branch list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Branch> query = cb.createQuery(Branch.class);
    	Root<Branch> root = query.from(Branch.class);
    	CriteriaQuery<Branch> select = query.select(root).where(cb.equal(root.get("college"), collegeId));
    	TypedQuery<Branch> typedQuery = this.entityManager.createQuery(select);
    	List<Branch> branchList = typedQuery.getResultList();
    	logger.debug("Returning list of branches from JPA criteria query. Total records : "+branchList.size());
		return branchList;
	}
	
	public List<Department> getDepartmentForCriteria(List<Branch> branchList, Long academicYearId){
		if(branchList.size() == 0 || academicYearId == null) {
			logger.warn("Either branch list is empty or academic year id is null. Returning empty subject list.");
			logger.warn("Total records in branchList list: "+branchList.size());
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Department> query = cb.createQuery(Department.class);
    	Root<Department> root = query.from(Department.class);
    	In<Long> inBranch = cb.in(root.get("branch"));
    	for (Branch bt : branchList) {
    		inBranch.value(bt.getId());
    	}
    	CriteriaQuery<Department> select = query.select(root).where(cb.and(inBranch), cb.and(cb.equal(root.get("academicyear"), academicYearId)));
    	TypedQuery<Department> typedQuery = this.entityManager.createQuery(select);
    	List<Department> departmentList = typedQuery.getResultList();
    	logger.debug("Returning list of departments from JPA criteria query. Total records : "+departmentList.size());
		return departmentList;
	}
	
	public List<Batch> getBatchForCriteria(List<Department> dept) {
		if(dept.size() == 0) {
			logger.warn("Department list is empty. Returning empty batch list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Batch> query = cb.createQuery(Batch.class);
    	Root<Batch> root = query.from(Batch.class);
    	In<Long> inClause = cb.in(root.get("department"));
    	for (Department dt : dept) {
    	    inClause.value(dt.getId());
    	}
    	CriteriaQuery<Batch> select = query.select(root).where(inClause);
    	TypedQuery<Batch> typedQuery = this.entityManager.createQuery(select);
    	List<Batch> bth = typedQuery.getResultList();
    	logger.debug("Returning list of years (batch) from JPA criteria query. Total records : "+bth.size());
		return bth;
	}
	
	public List<Subject> getSubjectForCriteria(List<Department> dept, List<Batch> batch){
		if(dept.size() == 0 || batch.size() == 0) {
			logger.warn("Either department or batch list is empty. Returning empty subject list.");
			logger.warn("Total records in department list: "+dept.size()+", total records in batch list: "+batch.size());
			return Collections.emptyList();
		}

		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Subject> query = cb.createQuery(Subject.class);
    	Root<Subject> root = query.from(Subject.class);
    	In<Long> inDepartment = cb.in(root.get("department"));
    	for (Department dt : dept) {
    	    inDepartment.value(dt.getId());
    	}
    	In<Long> inBatch = cb.in(root.get("batch"));
    	for (Batch bth : batch) {
    		inBatch.value(bth.getId());
    	}
    	
    	CriteriaQuery<Subject> select = query.select(root).where(cb.and(inDepartment), cb.and(inBatch), cb.and(cb.equal(root.get("status"), Status.ACTIVE)));
    	TypedQuery<Subject> typedQuery = this.entityManager.createQuery(select);
    	List<Subject> subList = typedQuery.getResultList();
    	logger.debug("Returning list of subjects from JPA criteria query. Total records : "+subList.size());
		return subList;
	}
	
	public List<Section> getSectionForCriteria(List<Batch> batch){
		if(batch.size() == 0) {
			logger.warn("Batch list is empty. Returning empty section list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Section> query = cb.createQuery(Section.class);
    	Root<Section> root = query.from(Section.class);
    	In<Long> inBatch = cb.in(root.get("batch"));
    	for (Batch bth : batch) {
    		inBatch.value(bth.getId());
    	}
    	CriteriaQuery<Section> select = query.select(root).where(inBatch);
    	TypedQuery<Section> typedQuery = this.entityManager.createQuery(select);
    	List<Section> secList = typedQuery.getResultList();
    	logger.debug("Returning list of sections from JPA criteria query. Total records : "+secList.size());
		return secList;
	}
	
	public List<Teach> getTeachForCriteria(List<Subject> subjectList, Long teacherId){
		if(subjectList.size() == 0) {
			logger.warn("Subject list is empty. Returning empty teach list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Teach> query = cb.createQuery(Teach.class);
    	Root<Teach> root = query.from(Teach.class);
    	In<Long> inSubject = cb.in(root.get("subject"));
    	for (Subject sub : subjectList) {
    		inSubject.value(sub.getId());
    	}
    	CriteriaQuery<Teach> select = query.select(root).where(cb.and(inSubject), cb.and(cb.equal(root.get("teacher"), teacherId)));
    	TypedQuery<Teach> typedQuery = this.entityManager.createQuery(select);
    	List<Teach> teachList = typedQuery.getResultList();
    	logger.debug("Returning list of teach from JPA criteria query. Total records : "+teachList.size());
		return teachList;
	}
	
	public List<Teach> getTeachForCriteria(Long teacherId){
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Teach> query = cb.createQuery(Teach.class);
    	Root<Teach> root = query.from(Teach.class);
    	CriteriaQuery<Teach> select = query.select(root).where(cb.equal(root.get("teacher"), teacherId));
    	TypedQuery<Teach> typedQuery = this.entityManager.createQuery(select);
    	List<Teach> teachList = typedQuery.getResultList();
    	logger.debug("Returning list of teach based on teacher id from JPA criteria query. Total records : "+teachList.size());
		return teachList;
	}
	
	/**
	 * AttendanceMaster for teacher attendance
	 * @param batchList
	 * @param secList
	 * @param teachList
	 * @return
	 */
	public List<AttendanceMaster> getAttendanceMasterForCriteria(List<Batch> batchList, List<Section> secList, List<Teach> teachList){
		if(batchList.size() == 0 || secList.size() == 0 || teachList.size() == 0) {
			logger.warn("Either batch, section or teach list is empty. Returning empty attendance master list.");
			logger.warn("Total records in batch list: "+batchList.size()+", total records in section list: "+secList.size()	+", total records in teach list: "+teachList.size());
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<AttendanceMaster> query = cb.createQuery(AttendanceMaster.class);
    	Root<AttendanceMaster> root = query.from(AttendanceMaster.class);
    	In<Long> inBatch = cb.in(root.get("batch"));
    	for (Batch bth : batchList) {
    		inBatch.value(bth.getId());
    	}
    	In<Long> inSection = cb.in(root.get("section"));
    	for (Section sec : secList) {
    		inSection.value(sec.getId());
    	}
    	In<Long> inTeach = cb.in(root.get("teach"));
    	for (Teach tch : teachList) {
    		inTeach.value(tch.getId());
    	}
    	CriteriaQuery<AttendanceMaster> select = query.select(root).where(cb.and(inBatch),cb.and(inSection), cb.and(inTeach));
    	TypedQuery<AttendanceMaster> typedQuery = this.entityManager.createQuery(select);
    	List<AttendanceMaster> atndMstrList = typedQuery.getResultList();
    	logger.debug("Returning list of attendance master from JPA criteria query. Total records : "+atndMstrList.size());
		return atndMstrList;
	}
	
	/**
	 * AttendanceMaster for admin attendance
	 * @param batchList
	 * @param secList
	 * @return
	 */
	public List<AttendanceMaster> getAttendanceMasterForCriteria(List<Batch> batchList, List<Section> secList){
		if(batchList.size() == 0 || secList.size() == 0 ) {
			logger.warn("Either batch or section. Returning empty attendance master list.");
			logger.warn("Total records in batch list: "+batchList.size()+" and total records in section list: "+secList.size());
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<AttendanceMaster> query = cb.createQuery(AttendanceMaster.class);
    	Root<AttendanceMaster> root = query.from(AttendanceMaster.class);
    	In<Long> inBatch = cb.in(root.get("batch"));
    	for (Batch bth : batchList) {
    		inBatch.value(bth.getId());
    	}
    	In<Long> inSection = cb.in(root.get("section"));
    	for (Section sec : secList) {
    		inSection.value(sec.getId());
    	}
    	
    	CriteriaQuery<AttendanceMaster> select = query.select(root).where(cb.and(inBatch),cb.and(inSection));
    	TypedQuery<AttendanceMaster> typedQuery = this.entityManager.createQuery(select);
    	List<AttendanceMaster> atndMstrList = typedQuery.getResultList();
    	logger.debug("Returning list of attendance master for admin attendance from JPA criteria query. Total records : "+atndMstrList.size());
		return atndMstrList;
	}
	
	public List<Lecture> getLectureForCriteria(List<AttendanceMaster> atndMstrList, String lectureDate) throws Exception{
		if(atndMstrList.size() == 0) {
			logger.warn("Attendance master list is empty. Returning empty lecture list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Lecture> query = cb.createQuery(Lecture.class);
    	Root<Lecture> root = query.from(Lecture.class);
    	In<Long> inAtndMstr = cb.in(root.get("attendancemaster"));
    	for (AttendanceMaster am : atndMstrList) {
    		inAtndMstr.value(am.getId());
    	}
    	Date dt = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lectureDate);
    	CriteriaQuery<Lecture> select = query.select(root).where(cb.and(inAtndMstr), cb.and(cb.equal(root.get("lecDate"), dt)));
    	TypedQuery<Lecture> typedQuery = this.entityManager.createQuery(select);
    	List<Lecture> lectureList = typedQuery.getResultList();
    	logger.debug("Returning list of lectures from JPA criteria query. Total records : "+lectureList.size());
		return lectureList;
	}
	
	public List<Lecture> getLectureForAdminCriteria(List<AttendanceMaster> atndMstrList) throws Exception{
		if(atndMstrList.size() == 0) {
			logger.warn("Attendance master list is empty. Returning empty lecture list.");
			return Collections.emptyList();
		}
		CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
    	CriteriaQuery<Lecture> query = cb.createQuery(Lecture.class);
    	Root<Lecture> root = query.from(Lecture.class);
    	In<Long> inAtndMstr = cb.in(root.get("attendancemaster"));
    	for (AttendanceMaster am : atndMstrList) {
    		inAtndMstr.value(am.getId());
    	}
//    	Date dt = DateFormatUtil.getUtilDate(CmsConstants.DATE_FORMAT_dd_MM_yyyy, lectureDate);
    	CriteriaQuery<Lecture> select = query.select(root).where(inAtndMstr);
    	TypedQuery<Lecture> typedQuery = this.entityManager.createQuery(select);
    	List<Lecture> lectureList = typedQuery.getResultList();
    	logger.debug("Returning list of lectures from JPA criteria query. Total records : "+lectureList.size());
		return lectureList;
	}
	
	public List<CmsGenderVo> getAllGenders() {
        logger.debug("Retrieving all genders types");
        List<CmsGenderVo> ls = new ArrayList<>();
        for(Gender sm: Gender.values()) {
        	CmsGenderVo vo = new CmsGenderVo();
        	vo.setId(sm.value());
        	vo.setDescription(sm.getDescription());
        	ls.add(vo);
        }
        return ls;
    }
	
	public CmsGenderVo getGender(Long id) {
		Gender sm = Gender.valueOf(id.intValue());
		CmsGenderVo vo = new CmsGenderVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
	}
	
	public CmsGenderVo getGenderByDescription(String genderDescription) {
		Gender sm = Gender.getGenderOnDescription(genderDescription);
		CmsGenderVo vo = new CmsGenderVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return vo;
	}
	
	public List<CmsTermVo> getTermsByAcademicYear(Long academicYearId) throws Exception{
		if(academicYearId == null) {
			Collections.emptyList();
		}
		Term term = new Term();
		AcademicYear ay = this.getAcademicYearById(academicYearId);
		term.setAcademicyear(ay);
		term.setTermStatus(Status.ACTIVE);
		Example<Term> example = Example.of(term);
		List<Term> list = this.termRepository.findAll(example);
		List<CmsTermVo> ls = new ArrayList<>();
		for(Term tm: list) {
            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getStartDate());
            String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getEndDate());
            CmsTermVo ctm = CommonUtil.createCopyProperties(tm, CmsTermVo.class);
            ctm.setStrStartDate(stDt);
            ctm.setStrEndDate(enDt);
            ls.add(ctm);
        }
		return ls;
	}
} 
