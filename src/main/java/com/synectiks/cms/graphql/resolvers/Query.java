/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synectiks.cms.graphql.resolvers;

import java.util.ArrayList;
import java.util.List;

import com.synectiks.cms.business.service.*;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.filter.employee.EmployeeFilterProcessor;
import com.synectiks.cms.filter.vehicle.VehicleFilterProcessor;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.business.service.exam.AcExamSetting;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.enumeration.Gender;
import com.synectiks.cms.domain.enumeration.StudentTypeEnum;
import com.synectiks.cms.filter.Book.BookfilterProcessor;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectProcessor;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectQueryPayload;
import com.synectiks.cms.filter.admissionapplication.AdmissionApplicationProcessor;
import com.synectiks.cms.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.cms.filter.common.CommonGraphiqlFilter;
import com.synectiks.cms.filter.invoice.InvoiceFilterProcessor;
import com.synectiks.cms.filter.student.StudentFilterProcessor;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.summary.SummaryFilter;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

/**
 * Query Resolver for CMS Queries
 *
 */
@Component
public class Query implements GraphQLQueryResolver {

	private final static Logger logger = LoggerFactory.getLogger(Query.class);
    private final AcademicYearRepository academicYearRepository;
    private final AttendanceMasterRepository attendanceMasterRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
    private final AcademicHistoryRepository academicHistoryRepository;
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    private final AcademicExamSettingRepository academicExamSettingRepository;
    private final BankAccountsRepository bankAccountsRepository;
    private final BatchRepository batchRepository;
    private final BranchRepository branchRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final HolidayRepository holidayRepository;
    private final LectureRepository lectureRepository;
    //  private final InstituteRepository instituteRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final SectionRepository sectionRepository;
    //    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    //    private final StudentYearRepository studentYearRepository;
    private final SubjectRepository subjectRepository;
    private final TeachRepository teachRepository;
    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;
    private final CountryRepository countryRepository;
    private final FeeCategoryRepository feeCategoryRepository;
    private final FacilityRepository facilityRepository;
    private final TransportRouteRepository transportRouteRepository;
    private final FeeDetailsRepository feeDetailsRepository;
    private final DueDateRepository dueDateRepository;
    private final LateFeeRepository lateFeeRepository;
    private final PaymentRemainderRepository paymentRemainderRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompetitiveExamRepository competitiveExamRepository;
    private final DocumentsRepository documentsRepository;
    private final AdminAttendanceRepository adminAttendanceRepository;
    private final TypeOfGradingRepository typeOfGradingRepository;
    private final StudentExamReportRepository studentExamReportRepository;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final ContractRepository contractRepository;
    private final EmployeeRepository employeeRepository;
    private final InsuranceRepository insuranceRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    private AcademicSubjectProcessor academicSubjectProcessor;

    @Autowired
    LegalEntitySelectRepository legalEntitySelectRepository;

    @Autowired
    private InvoiceFilterProcessor invoiceFilterProcessor;

    @Autowired
    private AdmissionEnquiryProcessor admissionEnquiryProcessor;

    @Autowired
    private StudentFilterProcessor studentFilterProcessor;

    @Autowired
    private EmployeeFilterProcessor employeeFilterProcessor;

    @Autowired
    private VehicleFilterProcessor vehicleFilterProcessor;

    @Autowired
    private BookfilterProcessor bookfilterProcessor;

    @Autowired
    private CommonGraphiqlFilter commonGraphiqlFilter;

    @Autowired
    private CmsInvoiceService cmsInvoiceService;

    @Autowired
    private CmsAdmissionEnquiryService cmsAdmissionEnquiryService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private ContractService contractService;

    @Autowired
    private TransportService transportService;

    @Autowired
    private SummaryFilter summaryFilter;

    @Autowired
    private AdmissionApplicationProcessor admissionApplicationProcessor;

    @Autowired
    private CmsAdmissionApplicationService cmsAdmissionApplicationService;

    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    public List<CmsVehicleVo> getVehicleList() throws Exception {
        logger.debug("Query - getVehicleList :");
        return this.vehicleService.getVehicleList();
      }
    public List<CmsInsuranceVo> getInsuranceList() throws Exception {
        logger.debug("Query - getInsuranceList :");
        return this.insuranceService.getInsuranceList();
    }
    public List<CmsContractVo> getContractList() throws Exception {
        logger.debug("Query - getContractList :");
        return this.contractService.getContractList();
    }

    public List<CmsTransportVo> getTransportRouteList() throws Exception {
        logger.debug("Query - getTransportRouteList :");
        return this.transportService.getTransportRouteList();
    }

    public Query(LibraryRepository libraryRepository, SummaryFilter summaryFilter, BookRepository bookRepository, AcademicExamSettingRepository academicExamSettingRepository, AdminAttendanceRepository adminAttendanceRepository, AcademicHistoryRepository academicHistoryRepository, AdmissionEnquiryRepository admissionEnquiryRepository, LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, AdmissionApplicationRepository admissionApplicationRepository, HolidayRepository holidayRepository, TermRepository termRepository, CityRepository cityRepository, StateRepository stateRepository, CountryRepository countryRepository, FeeCategoryRepository feeCategoryRepository, FacilityRepository facilityRepository, TransportRouteRepository transportRouteRepository, FeeDetailsRepository feeDetailsRepository, DueDateRepository dueDateRepository, LateFeeRepository lateFeeRepository, PaymentRemainderRepository paymentRemainderRepository, InvoiceRepository invoiceRepository, CompetitiveExamRepository competitiveExamRepository, DocumentsRepository documentsRepository, TypeOfGradingRepository typeOfGradingRepository, StudentExamReportRepository studentExamReportRepository, LibraryRepository LibraryRepository, ContractRepository contractRepository, EmployeeRepository employeeRepository, InsuranceRepository insuranceRepository, VehicleRepository vehicleRepository) {
        this.libraryRepository = libraryRepository;
        this.bookRepository=bookRepository;
        this.summaryFilter = summaryFilter;
        this.academicExamSettingRepository = academicExamSettingRepository;
        this.academicHistoryRepository = academicHistoryRepository;
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
//        this.instituteRepository=instituteRepository;
        this.collegeRepository = collegeRepository;
        this.branchRepository = branchRepository;
//        this.studentYearRepository = studentYearRepository;
//        this.semesterRepository = semesterRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentRepository = departmentRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicYearRepository = academicYearRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.holidayRepository = holidayRepository;
        this.termRepository = termRepository;
        this.teachRepository = teachRepository;
        this.attendanceMasterRepository = attendanceMasterRepository;
        this.lectureRepository = lectureRepository;
        this.cityRepository = cityRepository;
        this.stateRepository = stateRepository;
        this.countryRepository = countryRepository;
        this.feeCategoryRepository = feeCategoryRepository;
        this.facilityRepository = facilityRepository;
        this.transportRouteRepository = transportRouteRepository;
        this.feeDetailsRepository = feeDetailsRepository;
        this.dueDateRepository = dueDateRepository;
        this.lateFeeRepository = lateFeeRepository;
        this.paymentRemainderRepository = paymentRemainderRepository;
        this.invoiceRepository = invoiceRepository;
        this.competitiveExamRepository = competitiveExamRepository;
        this.documentsRepository = documentsRepository;
        this.adminAttendanceRepository = adminAttendanceRepository;
        this.typeOfGradingRepository = typeOfGradingRepository;
        this.studentExamReportRepository = studentExamReportRepository;
        this.contractRepository = contractRepository;
        this.employeeRepository = employeeRepository;

        this.insuranceRepository = insuranceRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public CmsStudentVo student(long id) {
        Student s = studentRepository.findById(id).get();
        CmsStudentVo vo = CommonUtil.createCopyProperties(s, CmsStudentVo.class);
        vo.setStrDateOfBirth(DateFormatUtil.changeLocalDateFormat(s.getDateOfBirth(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
        vo.setDateOfBirth(null);
        return vo;
    }

    public List<Student> students() {
        return Lists.newArrayList(studentRepository.findAll());
    }


   /* public List<Institute> institutes() {
        return Lists.newArrayList(instituteRepository.findAll());
    }

    public Institute institute(int id) {
        return instituteRepository.findById(id);
    }*/

    public College college(long id) {
        return collegeRepository.findById(id).get();
    }

    public List<College> colleges() {
        return Lists.newArrayList(collegeRepository.findAll());
    }

    public Contract contract(long id){
        return contractRepository.findById(id).get();
    }

    public List<Contract>  contracts (){
        return Lists.newArrayList(contractRepository.findAll());
    }

    public Employee employee(long id){
        return employeeRepository.findById(id).get();
    }

    public List<Employee> employees(){
        return Lists.newArrayList(employeeRepository.findAll());
    }

    public Branch branch(long id) {
        return branchRepository.findById(id).get();
    }

    public List<Branch> branches() {
        return Lists.newArrayList(branchRepository.findAll());
    }

    public Batch batch(long id) {
        return batchRepository.findById(id).get();
    }

    public List<Batch> batches() {
        return Lists.newArrayList(batchRepository.findAll());
    }

    public CompetitiveExam competitiveExam(long id) {
        return competitiveExamRepository.findById(id).get();
    }

    public List<CompetitiveExam> competitiveExams() {
        return Lists.newArrayList(competitiveExamRepository.findAll());
    }
    public Documents document(long id) {
        return documentsRepository.findById(id).get();
    }

    public List<Documents> documents() {
        return Lists.newArrayList(documentsRepository.findAll());
    }

    public AdmissionApplication admissionApplication(long id) {
        return admissionApplicationRepository.findById(id).get();
    }

    public List<AdmissionApplication> admissionApplications() {
        return Lists.newArrayList(admissionApplicationRepository.findAll());
    }

    public StudentExamReport studentExamReport(long id) {
        return studentExamReportRepository.findById(id).get();
    }

    public List<StudentExamReport> studentExamReports() {
        return Lists.newArrayList(studentExamReportRepository.findAll());
    }
    public AcademicHistory academicHistory(long id) {
        return academicHistoryRepository.findById(id).get();
    }

    public List<AcademicHistory> academicHistories() {
        return Lists.newArrayList(academicHistoryRepository.findAll());
    }

    /*public StudentYear studentYear(long id)
    {
        return studentYearRepository.findById(id).get();
    }

    public List<StudentYear> studentYears()
    {
        return Lists.newArrayList(studentYearRepository.findAll());
    }*/

   /* public Semester semester(long id)
    {
        return semesterRepository.findById(id).get();
    }

    public List<Semester> semesters()
    {
        return Lists.newArrayList(semesterRepository.findAll());
    }*/

    public AcademicExamSetting academicExamSetting (long id) {
        return academicExamSettingRepository.findById(id).get();
    }

    public List<AcademicExamSetting> academicExamSettings() {
        return Lists.newArrayList(academicExamSettingRepository.findAll());
    }

   public AcExamSetting acExamSetting(Long id){
        return summaryFilter.acExamSetting(id);
   }
   public List<AcExamSetting> acExamSettings(){

        return  Lists.newArrayList(summaryFilter.acExamSettings());
    }
    public List<TypeOfGrading> findTypeOfGradingOnNextId(Long groupvalue){
        return summaryFilter.findTypeOfGradingOnNextId(groupvalue);
    }
    public List<AcademicExamSetting> findExamValuesOnGroupvalue(Long countvalue){
        return summaryFilter.findExamValuesOnGroupvalue(countvalue);}




    public List<TypeOfGrading> typeOfGradings() {
        return Lists.newArrayList(typeOfGradingRepository.findAll());
    }

    public TypeOfGrading typeOfGrading(long id) {
        return typeOfGradingRepository.findById(id).get();
    }
    public Section section(long id) {
        return sectionRepository.findById(id).get();
    }

    public List<Section> sections() {
        return Lists.newArrayList(sectionRepository.findAll());
    }

    public Subject subject(long id) {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> subjects() {
        return Lists.newArrayList(subjectRepository.findAll());
    }

    public Teacher teacher(long id) {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> teachers() {
        return Lists.newArrayList(teacherRepository.findAll());
    }

    public LegalEntityAuthSignatoryLink legalEntity(long id) {
        return legalEntitySelectRepository.findById(id).get();
    }

    public List<LegalEntityAuthSignatoryLink> legalEntities() {
        return Lists.newArrayList(legalEntitySelectRepository.findAll());
    }

    public AuthorizedSignatory authorizedSignatory(long id) {
        return authorizedSignatoryRepository.findById(id).get();
    }

    public List<AuthorizedSignatory> authorizedSignatories() {
        return Lists.newArrayList(authorizedSignatoryRepository.findAll());
    }

    public BankAccounts bankAccount(long id) {
        return bankAccountsRepository.findById(id).get();
    }

    public List<BankAccounts> bankAccounts() {
        return Lists.newArrayList(bankAccountsRepository.findAll());
    }

    public Department department(long id) {
        return departmentRepository.findById(id).get();
    }

    public List<Department> departments() {
        return Lists.newArrayList(departmentRepository.findAll());
    }


    public StudentAttendance studentAttendance(long id) {
        return studentAttendanceRepository.findById(id).get();
    }

    public List<StudentAttendance> studentAttendances() {
        return Lists.newArrayList(studentAttendanceRepository.findAll());
    }

    public AdmissionEnquiry admissionEnquiry(long id) {
        return admissionEnquiryRepository.findById(id).get();
    }

    public List<AdmissionEnquiry> admissionEnquiries() {
        return Lists.newArrayList(admissionEnquiryRepository.findAll());
    }


    public List<AcademicYear> academicYears() {
        return Lists.newArrayList(academicYearRepository.findAll());
    }

    public AcademicYear academicYear(long id) {
        return academicYearRepository.findById(id).get();
    }

    public List<Holiday> holidays() {
        return Lists.newArrayList(holidayRepository.findAll());
    }

    public Holiday holiday(long id) {
        return holidayRepository.findById(id).get();
    }

    public List<Term> terms() {
        return Lists.newArrayList(termRepository.findAll());
    }

    public Term term(long id) {
        return termRepository.findById(id).get();
    }

    public Teach teach(long id) {
        return teachRepository.findById(id).get();
    }

    public List<Teach> teaches() {
        return Lists.newArrayList(teachRepository.findAll());
    }

    public AttendanceMaster attendanceMaster(long id) {
        return attendanceMasterRepository.findById(id).get();
    }

    public List<AttendanceMaster> attendanceMasters() {
        return Lists.newArrayList(attendanceMasterRepository.findAll());
    }

    public Lecture lecture(long id) {
        return lectureRepository.findById(id).get();
    }

    public List<Lecture> lectures() {
        return Lists.newArrayList(lectureRepository.findAll());
    }

    public List<Subject> getAcademicSubjects(AcademicSubjectQueryPayload academicSubjectQueryPayload) {
        return Lists.newArrayList(this.academicSubjectProcessor.getAcademicSubjects(academicSubjectQueryPayload));
    }
    public City city(long id) {
        return cityRepository.findById(id).get();
    }

    public List<City> cities() {
        return Lists.newArrayList(cityRepository.findAll());
    }

    public State state(long id) {
        return stateRepository.findById(id).get();
    }

    public List<State> states() {
        return Lists.newArrayList(stateRepository.findAll());
    }

    public Country country(long id){
        return countryRepository.findById(id).get();
    }
    public List<Country> countries(){
        return Lists.newArrayList(countryRepository.findAll());
    }

    public FeeCategory feeCategory(long id){
        return feeCategoryRepository.findById(id).get();
    }

    public List<FeeCategory> feeCategories() {
        return Lists.newArrayList(feeCategoryRepository.findAll());
    }

    public Facility facility(long id){
        return facilityRepository.findById(id).get();
    }

    public List<Facility> facilities() {
        return Lists.newArrayList(facilityRepository.findAll());
    }

    public FeeDetails feeDetail(long id){
        return feeDetailsRepository.findById(id).get();
    }

    public List<FeeDetails> feeDetails(){
        return Lists.newArrayList(feeDetailsRepository.findAll());
    }

    public DueDate dueDate(long id) {return dueDateRepository.findById(id).get();}
    public List<DueDate> dueDates() {return  Lists.newArrayList (dueDateRepository.findAll());}
    public LateFee lateFee(long id ){return lateFeeRepository.findById(id).get();}
    public List<LateFee> lateFees(){return Lists.newArrayList(lateFeeRepository.findAll());}

    public AdminAttendance adminAttendance(Long id){return adminAttendanceRepository.findById(id).get();}
    public List<AdminAttendance> adminAttendances(){return Lists.newArrayList(adminAttendanceRepository.findAll());}

    public PaymentRemainder paymentRemainder(Long id){return paymentRemainderRepository.findById(id).get();}
    public List<PaymentRemainder>paymentRemainders(){return Lists.newArrayList(paymentRemainderRepository.findAll());}

    public Invoice invoice(long id){
        return invoiceRepository.findById(id).get();
    }

    public List<Invoice>  invoices(){
        return  Lists.newArrayList(invoiceRepository.findAll());
    }

    public Insurance insurance(long id){
        return insuranceRepository.findById(id).get();
    }

    public List<Insurance>  insurances(){
        return  Lists.newArrayList(insuranceRepository.findAll());
    }


//    public List<Invoice> searchInvoice(String invoiceNumber, long studentId){
//        return Lists.newArrayList(invoiceFilterProcessor.searchInvoice(invoiceNumber, studentId));
//    }

    public List<CmsInvoice> searchInvoiceOnType(String invoiceType, Long branchId, Long academicYearId) throws Exception{
        return Lists.newArrayList(invoiceFilterProcessor.searchInvoiceOnType(invoiceType, branchId, academicYearId));
    }

    public Long getTotalInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalPaidInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalPaidInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalUnPaidInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalUnPaidInvoice(collegeId, branchId, academicYearId);
    }

    public Long getTotalCanceledInvoice(long collegeId, long branchId, long academicYearId) {
        return invoiceFilterProcessor.getTotalCanceledInvoice(collegeId, branchId, academicYearId);
    }

    public CmsInvoice getInvoiceData(String collegeId, String branchId, String academicYearId) {
        return invoiceFilterProcessor.getInvoiceData(Long.valueOf(collegeId), Long.valueOf(branchId), Long.valueOf(academicYearId));
    }


    public Long getTotalAdmissions( long branchId) {
        return admissionEnquiryProcessor.getTotalAdmissions( branchId);
    }

    public Long getTotalFollowup( long branchId) {
        return admissionEnquiryProcessor.getTotalFollowup( branchId);
    }
    public Long getTotalDeclined(long branchId) {
        return admissionEnquiryProcessor.getTotalDeclined( branchId);
    }

    public Long getTotalConverted( long branchId) {
        return admissionEnquiryProcessor.getTotalConverted(branchId);
    }

    public CmsAdmissionEnquiryVo getAdmissionData( String branchId) {
    	if(branchId == null || "null".equalsIgnoreCase(branchId) || "undefined".equalsIgnoreCase(branchId)) {
    		return new CmsAdmissionEnquiryVo();
    	}
        return admissionEnquiryProcessor.getAdmissionData( Long.parseLong(branchId));
    }

    public Long getTotalReceived( long academicyearId) {
        return admissionApplicationProcessor.getTotalReceived( academicyearId);
    }

    public Long getTotalInprocess( long academicyearId) {
        return admissionApplicationProcessor.getTotalInprocess( academicyearId);
    }
    public Long getTotalDecline(long academicyearId) {
        return admissionApplicationProcessor.getTotalDeclined( academicyearId);
    }

    public Long getTotalAccepted( long academicyearId) {
        return admissionApplicationProcessor.getTotalAccepted(academicyearId);
    }

    public CmsAdmissionApplicationVo getAdmissionApplicationData( String academicYearId) {
    	if(academicYearId == null || "null".equalsIgnoreCase(academicYearId) || "undefined".equalsIgnoreCase(academicYearId)) {
    		return new CmsAdmissionApplicationVo();
    	}
        return admissionApplicationProcessor.getAdmissionApplicationData( Long.parseLong(academicYearId));
    }


    public List<Student> searchStudent(Long departmentId, Long batchId, Long sectionId, Long branchId, Gender gender, StudentTypeEnum studentType, String studentName){
        return Lists.newArrayList(studentFilterProcessor.searchStudent(departmentId, batchId, sectionId, branchId, gender, studentType, studentName));
    }

    public List<CmsEmployeeVo> searchEmployee(Long vehicleId, Long employeeId ,String employeeName) throws Exception {
        return Lists.newArrayList(employeeFilterProcessor.searchEmployee(vehicleId, employeeId,employeeName));
    }

    public List<CmsVehicleVo> searchVehicle(Long vehicleId, Long transportRouteId ,Long employeeId,String vehicleNumber) throws Exception {
        return Lists.newArrayList(vehicleFilterProcessor.searchVehicle(vehicleId, transportRouteId,employeeId,vehicleNumber));
    }


    public List<Library>searchBook(String bookTitle,String author,Long batchId,Long subjectId){
        return (List<Library>) Lists.newArrayList(bookfilterProcessor.searchBook(bookTitle,author,batchId,subjectId));

    }

    public List<Branch> getAllBranches(String branchName, Long collegeId){
        return  Lists.newArrayList(commonGraphiqlFilter.getAllBranches(branchName, collegeId));
    }
    public Branch getBranchById(Long branchId){
        return commonGraphiqlFilter.getBranchById(branchId);
    }
    public College getCollegeById(Long collegeId){
        return commonGraphiqlFilter.getCollegeById(collegeId);
    }
    public College getCollegeByName(String  shortName){
        return commonGraphiqlFilter.getCollegeByName(shortName);
    }
    public Department getDepartmentById(Long departmentId){return commonGraphiqlFilter.getDepartmentById(departmentId);}
    public Section getSectionById(Long sectionId){return commonGraphiqlFilter.getSectionById(sectionId);}
    public Batch getBatchById(Long batchId){return commonGraphiqlFilter.getBatchById(batchId);}


    public StudentAttendanceCache createStudentAttendanceCache(String branchId, String academicYearId, String teacherId, String lectureDate) throws Exception{
    	StudentAttendanceCache cache = new StudentAttendanceCache();
    	if(branchId == null || "null".equalsIgnoreCase(branchId) || "undefined".equalsIgnoreCase(branchId)
    			|| academicYearId == null || "null".equalsIgnoreCase(academicYearId) || "undefined".equalsIgnoreCase(academicYearId)
    			|| teacherId == null || "null".equalsIgnoreCase(teacherId) || "undefined".equalsIgnoreCase(teacherId)) {

    		logger.warn("Either branch/academic year or teacher id is null. Return empty cache");
//    		cache.setDepartments(new ArrayList<Department>());
        	cache.setBatches(new ArrayList<Batch>());
        	cache.setSubjects(new ArrayList<Subject>());
        	cache.setSections(new ArrayList<Section>());
        	cache.setLectures(new ArrayList<CmsLectureVo>());
        	cache.setSemesters(new ArrayList<CmsSemesterVo>());
        	cache.setTerms(new ArrayList<CmsTermVo>());
        	cache.setAttendanceMasters(new ArrayList<AttendanceMaster>());
    		return cache;
    	}

//    	Teacher thr = new Teacher();
//        thr.setTeacherEmailAddress(teacherId);
//    	Optional<Teacher> oth = this.teacherRepository.findOne(Example.of(thr));
//        Long tid = oth.isPresent() ? oth.get().getId() : 0;

        Teacher thr = this.commonService.getTeacherByEmail(teacherId);
        Long tid = (thr != null) ? thr.getId() : 0;

    	if(Long.parseLong(branchId) == 0 || Long.parseLong(academicYearId) == 0 || tid == 0) {
    		logger.warn("Either branch/academic year or teacher id is not provided. Return empty cache");
//    		cache.setDepartments(new ArrayList<Department>());
        	cache.setBatches(new ArrayList<Batch>());
        	cache.setSubjects(new ArrayList<Subject>());
        	cache.setSections(new ArrayList<Section>());
        	cache.setLectures(new ArrayList<CmsLectureVo>());
        	cache.setSemesters(new ArrayList<CmsSemesterVo>());
        	cache.setTerms(new ArrayList<CmsTermVo>());
        	cache.setAttendanceMasters(new ArrayList<AttendanceMaster>());
    		cache.setTeaches(new ArrayList<Teach>());
        	return cache;
    	}

//    	List<Department> dept = this.commonService.getDepartmentsByBranchAndAcademicYear(Long.parseLong(branchId), Long.parseLong(academicYearId));
    	List<Batch> batchList = this.commonService.getAllBatches();// getBatchForCriteria(dept); //batches();
    	List<Subject> subjectList = this.commonService.getAllSubject(); //getSubjectForCriteria(dept, bth); //subjects();
    	List<Section> sectionList = this.commonService.getAllSections(); // getSectionForCriteria(bth); //sections();
    	List<Teach> teachList = this.commonService.getAllTeach(); //getTeachForCriteria(sub, tid); //teaches();
    	List<AttendanceMaster> attendanceMasterList = this.commonService.getAllAttendanceMaster(); //   getAttendanceMasterForCriteria(bth, sec, teach);// attendanceMasters();
    	List<Subject> selectedSubjectList = new ArrayList<>();

    	for(Subject subject: subjectList) {
    		for(Teach th: teachList) {
    			logger.debug("Subject id : "+subject.getId()+", teach-subject id : "+th.getSubject().getId());
    			if(subject.getId().compareTo(th.getSubject().getId()) == 0 ) {
    				logger.debug("Selected subject id : "+subject.toString());
    				selectedSubjectList.add(subject);
    			}

    		}
    	}


//    	List<Lecture> lec =  this.commonService.getLectureForCriteria(attendanceMasterList, lectureDate); //lectures();
//    	List<CmsLectureVo> cmsLec = new ArrayList<>();
//    	for(Lecture lecture : lec) {
//    		CmsLectureVo vo = CommonUtil.createCopyProperties(lecture, CmsLectureVo.class);
//    		vo.setStrLecDate(DateFormatUtil.changeLocalDateFormat(lecture.getLecDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    		vo.setLecDate(null);
//    		cmsLec.add(vo);
//    	}

    	List<CmsLectureVo> cmsLec = this.commonService.getAllCurrentDateCmsLectureForTeacher(tid);

    	List<CmsSemesterVo> sem = this.commonService.getAllCmsSemesters();

//    	cache.setDepartments(dept);
    	cache.setBatches(batchList);
    	cache.setSubjects(selectedSubjectList);
    	cache.setSections(sectionList);
    	cache.setLectures(cmsLec);
    	cache.setSemesters(sem);
    	cache.setTeaches(teachList);
    	cache.setAttendanceMasters(attendanceMasterList);
    	return cache;
    }


    /**
     * Cache for admin attendance
     * @param branchId
     * @param academicYearId
     * @param lectureDate
     * @return
     * @throws Exception
     */
    public StudentAttendanceCache createStudentAttendanceCacheForAdmin(String branchId, String departmentId, String academicYearId, String lectureDate) throws Exception{
    	StudentAttendanceCache cache = new StudentAttendanceCache();
    	if(branchId == null || "null".equalsIgnoreCase(branchId) || "undefined".equalsIgnoreCase(branchId)
    			|| academicYearId == null || "null".equalsIgnoreCase(academicYearId) || "undefined".equalsIgnoreCase(academicYearId)) {
    		logger.warn("Either branch or academic year id is null. Return empty cache");
//    		cache.setDepartments(new ArrayList<Department>());
        	cache.setBatches(new ArrayList<Batch>());
        	cache.setSubjects(new ArrayList<Subject>());
        	cache.setSections(new ArrayList<Section>());
        	cache.setLectures(new ArrayList<CmsLectureVo>());
        	cache.setSemesters(new ArrayList<CmsSemesterVo>());
        	cache.setTerms(new ArrayList<CmsTermVo>());
        	cache.setAttendanceMasters(new ArrayList<AttendanceMaster>());
    		return cache;
    	}
    	if(Long.parseLong(branchId) == 0 || Long.parseLong(academicYearId) == 0) {
    		logger.warn("Either branch or academic year id is not provided. Return empty cache");
//    		cache.setDepartments(new ArrayList<Department>());
        	cache.setBatches(new ArrayList<Batch>());
        	cache.setSubjects(new ArrayList<Subject>());
        	cache.setSections(new ArrayList<Section>());
        	cache.setLectures(new ArrayList<CmsLectureVo>());
        	cache.setSemesters(new ArrayList<CmsSemesterVo>());
        	cache.setTerms(new ArrayList<CmsTermVo>());
        	cache.setAttendanceMasters(new ArrayList<AttendanceMaster>());
    		return cache;
    	}

//        List<Department> dept = this.commonService.getDepartmentsByBranchAndAcademicYear(Long.parseLong(branchId), Long.parseLong(academicYearId));
        List<Batch> bth = this.commonService.getAllBatches(); //getBatchForCriteria(dept);
        List<Subject> sub = this.commonService.getAllSubject(); //getSubjectForCriteria(dept, bth);
        List<Section> sec = this.commonService.getAllSections();// getSectionForCriteria(bth);
        List<AttendanceMaster> attendanceMaster = this.commonService.getAllAttendanceMaster();     //getAttendanceMasterForCriteria(bth, sec);
    	List<CmsTermVo> term = this.commonService.getTermsByAcademicYear(Long.parseLong(academicYearId));

//    	List<Lecture> lec =  this.commonService.getLectureForAdminCriteria(attendanceMaster);
//    	List<CmsLectureVo> cmsLec = new ArrayList<>();
//    	for(Lecture lecture : lec) {
//    		CmsLectureVo vo = CommonUtil.createCopyProperties(lecture, CmsLectureVo.class);
//    		vo.setStrLecDate(DateFormatUtil.changeLocalDateFormat(lecture.getLecDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
//    		//            vo.setStrLecDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(lecture.getLecDate()))));
//    		vo.setLecDate(null);
//    		cmsLec.add(vo);
//    	}

    	List<CmsLectureVo> cmsLec = this.commonService.getAllCmsLectures(Long.parseLong(branchId), Long.parseLong(departmentId), Long.parseLong(academicYearId));


    	List<CmsSemesterVo> sem = this.commonService.getAllCmsSemesters();

//    	cache.setDepartments(dept);
    	cache.setBatches(bth);
    	cache.setSubjects(sub);
    	cache.setSections(sec);
    	cache.setLectures(cmsLec);
    	cache.setSemesters(sem);
//    	cache.setTeaches(teach);
    	cache.setTerms(term);
    	cache.setAttendanceMasters(attendanceMaster);
    	return cache;
    }

        public StudentFilterDataCache createStudentFilterDataCache(Long collegeId, Long academicYearId) throws Exception{

//    	List<Branch> branchList = this.commonService.getBranchForCriteria(Long.valueOf(collegeId));
//    	List<Department> departmentList = this.commonService.getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
    	List<Batch> batchList = this.commonService.findAllBatches();
    	List<Section> sectionList = this.commonService.findAllSections();
    	List<CmsStudentTypeVo> studentTypeList = this.commonService.getAllStudentTypes();
    	List<CmsGenderVo> genderList = this.commonService.getAllGenders();

    	StudentFilterDataCache cache = new StudentFilterDataCache();
//    	cache.setBranches(branchList);
//    	cache.setDepartments(departmentList);
    	cache.setBatches(batchList);
    	cache.setSections(sectionList);
    	cache.setStudentTypes(studentTypeList);
    	cache.setGenders(genderList);
    	return cache;
    }

    public ExamFilterDataCache createExamFilterDataCache(Long branchId, Long academicYearId) throws Exception{
//        List<Branch> branchList = this.commonService.findAllBranch(); //getBranchForCriteria(Long.valueOf(branchId));
//        List<Department> departmentList = this.commonService.findAllDepartment(); //  getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
    	List<Batch> batchList = this.commonService.findAllBatches(); // getBatchForCriteria(departmentList);

    	List<AcademicExamSetting> examsList= this.commonService.getExamsForCriteria(null, null);
        List<Subject> sub = this.commonService.findAllSubject(); // getSubjectForCriteria(null, batchList);

        List<Section> sectionList = this.commonService.findAllSections(); // getSectionForCriteria(batchList);

        List<CmsSemesterVo> sem = this.commonService.getAllSemesters();


        ExamFilterDataCache cache = new ExamFilterDataCache();
//        cache.setBranches(branchList);
//        cache.setDepartments(departmentList);
        cache.setBatches(batchList);
        cache.setAcademicExamSettings(examsList);
        cache.setSubjects(sub);
        cache.setSections(sectionList);
        cache.setSemesters(sem);

        return cache;
    }


    public LibraryFilterDataCache createLibraryFilterDataCache(String collegeId, String academicYearId) throws Exception{
        List<Branch> branchList = this.commonService.getBranchForCriteria(Long.valueOf(collegeId));
        List<Department> departmentList = this.commonService.getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
        List<Batch> batchList = this.commonService.getBatchForCriteria(departmentList);
        List<AcademicExamSetting> examsList= this.commonService.getExamsForCriteria(departmentList, batchList);
        List<Subject> sub = this.commonService.getSubjectForCriteria(departmentList, batchList);
        List<Section> sectionList = this.commonService.getSectionForCriteria(batchList);
        List<CmsSemesterVo> sem = this.commonService.getAllCmsSemesters();
        List<Library> library = this.commonService.getLibraryForCriteria(sub,batchList);
        List<Student> student = this.commonService.getStudentsForCriteria(departmentList, batchList, sectionList);
        List<CmsBook> book = this.commonService.getBookForCriteria(library,student);
        LibraryFilterDataCache cache = new LibraryFilterDataCache();
        cache.setDepartments(departmentList);
        cache.setBatches(batchList);
        cache.setAcademicExamSettings(examsList);
        cache.setBranches(branchList);
        cache.setSections(sectionList);
        cache.setSemesters(sem);
        cache.setSubjects(sub);
        cache.setLibraries(library);
        cache.setStudents(student);
        cache.setBooks(book);
        return cache;


    }


    public FeeDataCache createFeeDataCache() throws Exception{
    	List<College> collegeList = this.collegeRepository.findAll();
    	List<Branch> branchList = this.branchRepository.findAll();
    	FeeDataCache cache = new FeeDataCache();
    	cache.setColleges(collegeList);
    	cache.setBranches(branchList);

    	return cache;
    }

    public EmployeeDataCache createEmployeeDataCache() throws Exception{
        List<Employee> employeeList = this.commonService.findAllEmployee();
        List<Vehicle> vehicleList = this.vehicleRepository.findAll();
        EmployeeDataCache cache = new EmployeeDataCache();
        cache.setEmployees(employeeList);
        cache.setVehicles(vehicleList);
        return cache;
    }


    public AdmissionDataCache createAdmissionDataCache() throws  Exception{
        List<Department> departmentList = this.departmentRepository.findAll();
        List<Branch> branchList = this.branchRepository.findAll();
        List<Batch> batchList = this.batchRepository.findAll();
        List<State> stateList = this.stateRepository.findAll();
        List<City> cityList = this.cityRepository.findAll();
        List<CmsCourseEnumVo> courseEnumList = this.commonService.getAllCourses();
        AdmissionDataCache cache = new AdmissionDataCache();
        cache.setBatches(batchList);
        cache.setBranches(branchList);
        cache.setDepartments(departmentList);
        cache.setCities(cityList);
        cache.setStates(stateList);
        cache.setCourses(courseEnumList);

        return cache;
    }

    public VehicleDataCache createVehicleDataCache() throws Exception{
        List<CmsTransportVo> transportRouteList = this.transportService.getTransportRouteList();
        List<CmsInsuranceVo> insuranceList = this.insuranceService.getInsuranceList();
        List<CmsVehicleVo> vehicleList = this.vehicleService.getVehicleList();
        List<Employee> employeeList = this.commonService.findAllEmployee();
        List<Branch> branchList = this.commonService.findAllBranch();
        VehicleDataCache cache = new VehicleDataCache();
        cache.setTransportRoute(transportRouteList);
        cache.setInsurance(insuranceList);
        cache.setVehicle(vehicleList);
        cache.setEmployee(employeeList);
        cache.setBranches(branchList);
        return cache;
    }
    public InsuranceDataCache createInsuranceDataCache() throws Exception {
        List<CmsVehicleVo> vehicleList = this.vehicleService.getVehicleList();
        InsuranceDataCache cache = new InsuranceDataCache();
        cache.setVehicle(vehicleList);
        return cache;
    }


        public FeeSetupDataCache createFeeSetupDataCache(Long branchId, Long academicYearId) throws Exception{
//    	Branch branch = new Branch();
//    	branch.setId(Long.valueOf(branchId));
//    	List<Branch> branchList = new ArrayList<>();//this.commonService.getBranchForCriteria(Long.valueOf(collegeId));
//    	branchList.add(branch);
//    	List<Department> departmentList = this.commonService.getDepartmentForCriteria(branchList, Long.valueOf(academicYearId));
    	List<Batch> batchList = this.commonService.getAllBatches();
//    	List<Section> sectionList = this.commonService.getSectionForCriteria(batchList);
    	List<CmsStudentTypeVo> studentTypeList = this.commonService.getAllStudentTypes();
    	List<CmsGenderVo> genderList = this.commonService.getAllGenders();

    	FeeCategory f = new FeeCategory();
        f.setBranchId(branchId);
        Example<FeeCategory> example = Example.of(f);
//        List<CmsFeeCategory> feeCategoryList =  this.commonService.getFeeCategoryForCriteria(null);
        List<FeeCategory> list = this.feeCategoryRepository.findAll(example, Sort.by(Sort.Direction.DESC, "id"));
        List<CmsFeeCategory> feeCategoryList = new ArrayList<>();
        for(FeeCategory ff: list) {
        	CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
            if(ff.getStartDate() != null) {
                cfc.setStrStartDate(DateFormatUtil.changeLocalDateFormat(ff.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setStartDate(null);
//                cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate())));
//        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, ff.getStartDate())));
            }
            if(ff.getEndDate() != null) {
                cfc.setStrEndDate(DateFormatUtil.changeLocalDateFormat(ff.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setEndDate(null);
//                cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate())));
//        		ff.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, ff.getEndDate())));
            }
            if(ff.getCreatedOn() != null) {
                cfc.setStrCreatedOn(DateFormatUtil.changeLocalDateFormat(ff.getCreatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setCreatedOn(null);
            }
            if(ff.getUpdatedOn() != null) {
                cfc.setStrUpdatedOn(DateFormatUtil.changeLocalDateFormat(ff.getUpdatedOn(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                cfc.setUpdatedOn(null);
            }
            feeCategoryList.add(cfc);
        }
        List<CmsFeeDetails> feeDetailsList = this.commonService.getFeeDetailsForCriteria(feeCategoryList);
//        List<CmsFacility> facilityList = this.commonService.getFacilityForCriteria(null, Long.valueOf(academicYearId));
    	List<TransportRoute> transportRouteList = this.transportRouteRepository.findAll();

        FeeSetupDataCache cache = new FeeSetupDataCache();
//    	cache.setBranches(branchList);
//    	cache.setDepartments(departmentList);
    	cache.setBatches(batchList);
//    	cache.setSections(sectionList);
    	cache.setStudentTypes(studentTypeList);
    	cache.setGenders(genderList);
    	cache.setFeeCategory(feeCategoryList);
    	cache.setFeeDetails(feeDetailsList);
//    	cache.setFacility(facilityList);
    	cache.setTransportRoute(transportRouteList);
    	return cache;
    }
    public Library library(long id){
        return libraryRepository.findById(id).get();
    }
    public List<Library>libraries(){
        return Lists.newArrayList(libraryRepository.findAll());
    }
    public Book book(long id){
        return bookRepository.findById(id).get();
    }
    public List<Book>books(){
        return Lists.newArrayList(bookRepository.findAll());
    }
}
