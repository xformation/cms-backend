package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.base64.file.Base64FileProcessor;
import com.synectiks.cms.business.service.exam.ExamReportFilterInput;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Frequency;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.exceptions.BranchIdNotFoundException;
import com.synectiks.cms.exceptions.FileNameNotFoundException;
import com.synectiks.cms.exceptions.FilePathNotFoundException;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectMutationPayload;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectProcessor;
import com.synectiks.cms.filter.admissionapplication.AdmissionApplicationProcessor;
import com.synectiks.cms.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.cms.filter.exam.*;
import com.synectiks.cms.filter.invoice.InvoiceFilterProcessor;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.filter.lecture.LectureScheduleProcessor;
import com.synectiks.cms.filter.library.LibraryFilterProcessor;
import com.synectiks.cms.filter.student.StudentFilterProcessor;
import com.synectiks.cms.filter.student.StudentListFilterInput;
import com.synectiks.cms.filter.studentattendance.DailyAttendanceVo;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterInput;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceUpdateFilter;
import com.synectiks.cms.graphql.types.AcademicExamSetting.*;
import com.synectiks.cms.graphql.types.AcademicHistory.*;
import com.synectiks.cms.graphql.types.AcademicYear.*;
import com.synectiks.cms.graphql.types.AdminAttendance.*;
import com.synectiks.cms.graphql.types.AdmissionApplication.*;
import com.synectiks.cms.graphql.types.AdmissionEnquiry.*;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.AddAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.RemoveAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.RemoveAdmissionPersonalDetailsPayload;
import com.synectiks.cms.graphql.types.AdmissionPersonalDetails.UpdateAdmissionPersonalDetailsInput;
import com.synectiks.cms.graphql.types.AttendanceMaster.*;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.*;
import com.synectiks.cms.graphql.types.BankAccounts.*;
import com.synectiks.cms.graphql.types.Batch.*;
import com.synectiks.cms.graphql.types.Branch.*;
import com.synectiks.cms.graphql.types.City.*;
import com.synectiks.cms.graphql.types.College.*;
import com.synectiks.cms.graphql.types.CompetitiveExam.*;
import com.synectiks.cms.graphql.types.Contract.*;
import com.synectiks.cms.graphql.types.Country.*;
import com.synectiks.cms.graphql.types.Department.*;
import com.synectiks.cms.graphql.types.Documents.*;
import com.synectiks.cms.graphql.types.DueDate.*;
import com.synectiks.cms.graphql.types.Employee.*;
import com.synectiks.cms.graphql.types.Facility.*;
import com.synectiks.cms.graphql.types.FeeCategory.AddFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeCategory.RemoveFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeCategory.RemoveFeeCategoryPayload;
import com.synectiks.cms.graphql.types.FeeCategory.UpdateFeeCategoryInput;
import com.synectiks.cms.graphql.types.FeeDetails.*;
import com.synectiks.cms.graphql.types.Holiday.*;
import com.synectiks.cms.graphql.types.Insurance.*;
import com.synectiks.cms.graphql.types.Invoice.*;
import com.synectiks.cms.graphql.types.LateFee.*;
import com.synectiks.cms.graphql.types.Lecture.*;
import com.synectiks.cms.graphql.types.LegalEntity.*;
import com.synectiks.cms.graphql.types.Library.*;
import com.synectiks.cms.graphql.types.PaymentRemainder.*;
import com.synectiks.cms.graphql.types.Section.*;
import com.synectiks.cms.graphql.types.State.*;
import com.synectiks.cms.graphql.types.Student.*;
import com.synectiks.cms.graphql.types.StudentAttendance.*;
import com.synectiks.cms.graphql.types.StudentExamReport.*;
import com.synectiks.cms.graphql.types.Subject.*;
import com.synectiks.cms.graphql.types.Teach.*;
import com.synectiks.cms.graphql.types.Teacher.*;
import com.synectiks.cms.graphql.types.Term.*;
import com.synectiks.cms.graphql.types.TransportRoute.*;
import com.synectiks.cms.graphql.types.TypeOfGrading.*;
import com.synectiks.cms.graphql.types.Vehicle.*;
import com.synectiks.cms.repository.*;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.File;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AcademicYearRepository academicYearRepository;
    private final AttendanceMasterRepository attendanceMasterRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
    private final AcademicHistoryRepository academicHistoryRepository;
    private final AcademicExamSettingRepository academicExamSettingRepository;
    private final AdmissionApplicationRepository admissionApplicationRepository;
    private final AdmissionEnquiryRepository admissionEnquiryRepository;
    private final BankAccountsRepository bankAccountsRepository;
    private final BatchRepository batchRepository;
    private final BranchRepository branchRepository;
    private final CollegeRepository collegeRepository;
    private final DepartmentRepository departmentRepository;
    private final HolidayRepository holidayRepository;
    private final LectureRepository lectureRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final LocationRepository locationRepository;
    private final SectionRepository sectionRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
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
    private final PaymentRemainderRepository paymentRemainderRepository;
    private final LateFeeRepository lateFeeRepository;
    private final InvoiceRepository invoiceRepository;
    private final CompetitiveExamRepository competitiveExamRepository;
    private final DocumentsRepository documentsRepository;
    private final AdminAttendanceRepository adminAttendanceRepository;
    private final TypeOfGradingRepository typeOfGradingRepository;
    private final StudentExamReportRepository studentExamReportRepository;
    private final LibraryRepository libraryRepository;
    private final VehicleRepository vehicleRepository;
    private final EmployeeRepository employeeRepository;
    private final ContractRepository contractRepository;
    private final InsuranceRepository insuranceRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    @Autowired
    private AcademicExamSettingFilterImpl academicExamSettingFilterImpl;

    @Autowired
    private LectureScheduleProcessor lectureScheduleProcessor;

    @Autowired
    private AcademicSubjectProcessor academicSubjectProcessor;

    @Autowired
    private InvoiceFilterProcessor invoiceFilterProcessor;

    @Autowired
    private AdmissionEnquiryProcessor admissionEnquiryProcessor;

    @Autowired
    private ExamFilterProcessor examFilterProcessor;

	@Autowired
    private StudentFilterProcessor studentFilterProcessor;

    @Autowired
	private Base64FileProcessor base64FileProcessor;

    @Autowired
    private AdmissionApplicationProcessor admissionApplicationProcessor;
    @Autowired
    private LibraryFilterProcessor libraryFilterProcessor;

    public Mutation(AcademicExamSettingRepository academicExamSettingRepository, AdminAttendanceRepository adminAttendanceRepository, AcademicHistoryRepository academicHistoryRepository, AdmissionEnquiryRepository admissionEnquiryRepository, CountryRepository countryRepository, LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, AdmissionApplicationRepository admissionApplicationRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository, CityRepository cityRepository, StateRepository stateRepository, FeeCategoryRepository feeCategoryRepository, FacilityRepository facilityRepository, TransportRouteRepository transportRouteRepository, FeeDetailsRepository feeDetailsRepository, DueDateRepository dueDateRepository, PaymentRemainderRepository paymentRemainderRepository, LateFeeRepository lateFeeRepository, InvoiceRepository invoiceRepository, CompetitiveExamRepository competitiveExamRepository, DocumentsRepository documentsRepository, TypeOfGradingRepository typeOfGradingRepository, StudentExamReportRepository studentExamReportRepository, LibraryRepository libraryRepository, VehicleRepository vehicleRepository, EmployeeRepository employeeRepository, ContractRepository contractRepository, InsuranceRepository insuranceRepository, EntityManager entityManager) {
        this.academicExamSettingRepository = academicExamSettingRepository;
        this.academicHistoryRepository = academicHistoryRepository;
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.batchRepository = batchRepository;
        this.studentRepository = studentRepository;
//        this.instituteRepository = instituteRepository;
        this.collegeRepository = collegeRepository;
//        this.studentYearRepository = studentYearRepository;
//        this.semesterRepository = semesterRepository;
        this.branchRepository = branchRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicYearRepository = academicYearRepository;
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
        this.paymentRemainderRepository = paymentRemainderRepository;
        this.lateFeeRepository = lateFeeRepository;
        this.invoiceRepository = invoiceRepository;
        this.competitiveExamRepository = competitiveExamRepository;
        this.documentsRepository = documentsRepository;
        this.adminAttendanceRepository = adminAttendanceRepository;
        this.typeOfGradingRepository = typeOfGradingRepository;
        this.studentExamReportRepository = studentExamReportRepository;
        this.libraryRepository = libraryRepository;
        this.vehicleRepository = vehicleRepository;
        this.employeeRepository = employeeRepository;
        this.contractRepository = contractRepository;
        this.insuranceRepository = insuranceRepository;
        this.entityManager = entityManager;

    }

    public AddCountryPayload addCountry(AddCountryInput addCountryInput) {
        final Country country = new Country();
        country.setCountryName(addCountryInput.getCountryName());
        country.setCountryCode(addCountryInput.getCountryCode());
        country.setIsdCode(addCountryInput.getIsdCode());
        countryRepository.save(country);
        return new AddCountryPayload(country);
    }

    public UpdateCountryPayload updateCountry(UpdateCountryInput updateCountryInput) {
        Country country = countryRepository.findById(updateCountryInput.getId()).get();
        if (updateCountryInput.getCountryName() != null) {
            country.setCountryName(updateCountryInput.getCountryName());
        }
        if (updateCountryInput.getCountryCode() != null) {
            country.setCountryCode(updateCountryInput.getCountryCode());
        }
        if (updateCountryInput.getIsdCode() != null) {
            country.setIsdCode(updateCountryInput.getIsdCode());
        }

        countryRepository.save(country);

        return new UpdateCountryPayload(country);
    }

    public RemoveCountryPayload removeCountry(RemoveCountryInput removeCountryInput) {
        Country country = countryRepository.findById(removeCountryInput.getCountryId()).get();
        countryRepository.delete(country);
        return new RemoveCountryPayload(Lists.newArrayList(countryRepository.findAll()));
    }

    public AddCityPayload addCity(AddCityInput addCityInput) {
        final State state = stateRepository.findById(addCityInput.getStateId()).get();

        final City city = new City();
        city.setState(state);
        city.setCityName(addCityInput.getCityName());
        city.setCityCode(addCityInput.getCityCode());
        city.setStdCode(addCityInput.getStdCode());
        cityRepository.save(city);
        return new AddCityPayload(city);
    }

    public UpdateCityPayload updateCity(UpdateCityInput updateCityInput) {
        City city = cityRepository.findById(updateCityInput.getId()).get();
        if (updateCityInput.getCityName() != null) {
            city.setCityName(updateCityInput.getCityName());
        }
        if (updateCityInput.getCityCode() != null) {
            city.setCityCode(updateCityInput.getCityCode());
        }
        if (updateCityInput.getStdCode() != null) {
            city.setStdCode(updateCityInput.getStdCode());
        }

        if (updateCityInput.getStateId() != null) {
            final State state = stateRepository.findById(updateCityInput.getStateId()).get();
            city.setState(state);
        }
        cityRepository.save(city);

        return new UpdateCityPayload(city);
    }

    public RemoveCityPayload removeCity(RemoveCityInput removeCityInput) {
        City city = cityRepository.findById(removeCityInput.getCityId()).get();
        cityRepository.delete(city);
        return new RemoveCityPayload(Lists.newArrayList(cityRepository.findAll()));
    }

    public AddStatePayload addState(AddStateInput addStateInput) {
        final Country country = countryRepository.findById(addStateInput.getCountryId()).get();

        final State state = new State();
        state.setCountry(country);
        state.setStateName(addStateInput.getStateName());
        state.setDivisionType(addStateInput.getDivisionType());
        state.setStateCode(addStateInput.getStateCode());
        stateRepository.save(state);
        return new AddStatePayload(state);
    }

    public UpdateStatePayload updateState(UpdateStateInput updateStateInput) {
        State state = stateRepository.findById(updateStateInput.getId()).get();

        if (updateStateInput.getStateName() != null) {
            state.setStateName(updateStateInput.getStateName());
        }
        if (updateStateInput.getDivisionType() != null) {
            state.setDivisionType(updateStateInput.getDivisionType());
        }
        if (updateStateInput.getStateCode() != null) {
            state.setStateCode(updateStateInput.getStateCode());
        }

        if (updateStateInput.getCountryId() != null) {
            final Country country = countryRepository.findById(updateStateInput.getCountryId()).get();
            state.setCountry(country);
        }
        stateRepository.save(state);

        return new UpdateStatePayload(state);
    }

    public RemoveStatePayload removeState(RemoveStateInput removeStateInput) {
        State state = stateRepository.findById(removeStateInput.getStateId()).get();
        stateRepository.delete(state);
        return new RemoveStatePayload(Lists.newArrayList(stateRepository.findAll()));
    }

    public AddAdmissionApplicationPayload addAdmissionApplication(AddAdmissionApplicationInput addAdmissionApplicationInput) {
        AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(addAdmissionApplicationInput.getAdmissionEnquiryId()).get();
        AcademicHistory academicHistory = academicHistoryRepository.findById(addAdmissionApplicationInput.getAcademicHistoryId()).get();
        Documents documents = documentsRepository.findById(addAdmissionApplicationInput.getDocumentsId()).get();
        Branch branch = branchRepository.findById(addAdmissionApplicationInput.getBranchId()).get();
        Batch batch = batchRepository.findById(addAdmissionApplicationInput.getBatchId()).get();
        State state = stateRepository.findById(addAdmissionApplicationInput.getStateId()).get();
        City city = cityRepository.findById(addAdmissionApplicationInput.getCityId()).get();
        Country country = countryRepository.findById(addAdmissionApplicationInput.getCountryId()).get();
        Department department = departmentRepository.findById(addAdmissionApplicationInput.getDepartmentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addAdmissionApplicationInput.getAcademicyearId()).get();

        final AdmissionApplication admissionApplication = new AdmissionApplication();
        admissionApplication.setAdmissionStatus(addAdmissionApplicationInput.getAdmissionStatus());
        admissionApplication.setStudentName(addAdmissionApplicationInput.getStudentName());
        admissionApplication.setStudentMiddleName(addAdmissionApplicationInput.getStudentMiddleName());
        admissionApplication.setStudentLastName(addAdmissionApplicationInput.getStudentLastName());
        admissionApplication.setFatherName(addAdmissionApplicationInput.getFatherName());
        admissionApplication.setFatherMiddleName(addAdmissionApplicationInput.getFatherMiddleName());
        admissionApplication.setFatherLastName(addAdmissionApplicationInput.getFatherLastName());
        admissionApplication.setMotherName(addAdmissionApplicationInput.getMotherName());
        admissionApplication.setMotherMiddleName(addAdmissionApplicationInput.getMotherMiddleName());
        admissionApplication.setMotherLastName(addAdmissionApplicationInput.getMotherLastName());
        admissionApplication.setContactNumber(addAdmissionApplicationInput.getContactNumber());
        admissionApplication.setAlternateMobileNumber(addAdmissionApplicationInput.getAlternateMobileNumber());
        admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionApplicationInput.getDateOfBirth()));
        admissionApplication.setEmail(addAdmissionApplicationInput.getEmail());
        admissionApplication.setSex(addAdmissionApplicationInput.getSex());
        admissionApplication.setCourse(addAdmissionApplicationInput.getCourse());
        admissionApplication.setComments(addAdmissionApplicationInput.getComments());
        admissionApplication.setAdmissionDate(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionApplicationInput.getAdmissionDate()));
        admissionApplication.setApplicationId(addAdmissionApplicationInput.getApplicationId());
        admissionApplication.setUploadPhoto(addAdmissionApplicationInput.getUploadPhoto());
        admissionApplication.setAcademicyear(academicYear);
        admissionApplication.setAdmissionEnquiry(admissionEnquiry);
        admissionApplication.setAcademicHistory(academicHistory);
        admissionApplication.setDocuments(documents);
        admissionApplication.setBranch(branch);
        admissionApplication.setBatch(batch);
        admissionApplication.setState(state);
        admissionApplication.setCity(city);
        admissionApplication.setCountry(country);
        admissionApplication.setDepartment(department);
        admissionApplicationRepository.save(admissionApplication);
        return new AddAdmissionApplicationPayload(admissionApplication);
    }

    public UpdateAdmissionApplicationPayload updateAdmissionApplication(UpdateAdmissionApplicationInput updateAdmissionApplicationInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(updateAdmissionApplicationInput.getId()).get();


        if (updateAdmissionApplicationInput.getAdmissionStatus() != null) {
            admissionApplication.setAdmissionStatus(updateAdmissionApplicationInput.getAdmissionStatus());
        }
        if (updateAdmissionApplicationInput.getStudentName() != null) {
            admissionApplication.setStudentName(updateAdmissionApplicationInput.getStudentName());
        }
        if (updateAdmissionApplicationInput.getStudentMiddleName() != null) {
            admissionApplication.setStudentMiddleName(updateAdmissionApplicationInput.getStudentMiddleName());
        }
        if (updateAdmissionApplicationInput.getStudentLastName() != null) {
            admissionApplication.setStudentLastName(updateAdmissionApplicationInput.getStudentLastName());
        }
        if (updateAdmissionApplicationInput.getFatherName() != null) {
            admissionApplication.setFatherName(updateAdmissionApplicationInput.getFatherName());
        }
        if (updateAdmissionApplicationInput.getFatherMiddleName() != null) {
            admissionApplication.setFatherMiddleName(updateAdmissionApplicationInput.getFatherMiddleName());
        }
        if (updateAdmissionApplicationInput.getFatherLastName() != null) {
            admissionApplication.setFatherLastName(updateAdmissionApplicationInput.getFatherLastName());
        }
        if (updateAdmissionApplicationInput.getMotherName() != null) {
            admissionApplication.setMotherName(updateAdmissionApplicationInput.getMotherName());
        }
        if (updateAdmissionApplicationInput.getMotherMiddleName() != null) {
            admissionApplication.setMotherMiddleName(updateAdmissionApplicationInput.getMotherMiddleName());
        }
        if (updateAdmissionApplicationInput.getMotherLastName() != null) {
            admissionApplication.setMotherLastName(updateAdmissionApplicationInput.getMotherLastName());
        }
        if (updateAdmissionApplicationInput.getContactNumber() != null) {
            admissionApplication.setContactNumber(updateAdmissionApplicationInput.getContactNumber());
        }
        if (updateAdmissionApplicationInput.getAlternateMobileNumber() != null) {
            admissionApplication.setAlternateMobileNumber(updateAdmissionApplicationInput.getAlternateMobileNumber());
        }
        if (updateAdmissionApplicationInput.getDateOfBirth() != null) {
            admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionApplicationInput.getDateOfBirth()));
        }
        if (updateAdmissionApplicationInput.getEmail() != null) {
            admissionApplication.setEmail(updateAdmissionApplicationInput.getEmail());
        }
        if (updateAdmissionApplicationInput.getSex() != null) {
            admissionApplication.setSex(updateAdmissionApplicationInput.getSex());
        }
        if (updateAdmissionApplicationInput.getComments() != null) {
            admissionApplication.setComments(updateAdmissionApplicationInput.getComments());
        }
        if (updateAdmissionApplicationInput.getApplicationId() != null) {
            admissionApplication.setApplicationId(updateAdmissionApplicationInput.getApplicationId());
        }
        if (updateAdmissionApplicationInput.getUploadPhoto() != null) {
            admissionApplication.setUploadPhoto(updateAdmissionApplicationInput.getUploadPhoto());
        }

        if (updateAdmissionApplicationInput.getCourse() != null) {
            admissionApplication.setCourse(updateAdmissionApplicationInput.getCourse());
        }
        if (updateAdmissionApplicationInput.getAdmissionDate() != null) {
            admissionApplication.setAdmissionDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionApplicationInput.getAdmissionDate()));
        }
        if (updateAdmissionApplicationInput.getAdmissionEnquiryId() != null) {
            AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(updateAdmissionApplicationInput.getAdmissionEnquiryId()).get();
            admissionApplication.setAdmissionEnquiry(admissionEnquiry);
        }
        if (updateAdmissionApplicationInput.getDocumentsId() != null) {
            Documents documents = documentsRepository.findById(updateAdmissionApplicationInput.getDocumentsId()).get();
            admissionApplication.setDocuments(documents);
        }
        if (updateAdmissionApplicationInput.getAcademicHistoryId() != null) {
            AcademicHistory academicHistory = academicHistoryRepository.findById(updateAdmissionApplicationInput.getAcademicHistoryId()).get();
            admissionApplication.setAcademicHistory(academicHistory);
        }
        if (updateAdmissionApplicationInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateAdmissionApplicationInput.getBranchId()).get();
            admissionApplication.setBranch(branch);
        }
        if (updateAdmissionApplicationInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateAdmissionApplicationInput.getBatchId()).get();
            admissionApplication.setBatch(batch);
        }
        if (updateAdmissionApplicationInput.getStateId() != null) {
            State state = stateRepository.findById(updateAdmissionApplicationInput.getStateId()).get();
            admissionApplication.setState(state);
        }
        if (updateAdmissionApplicationInput.getCityId() != null) {
            City city = cityRepository.findById(updateAdmissionApplicationInput.getCityId()).get();
            admissionApplication.setCity(city);
        }
        if (updateAdmissionApplicationInput.getCountryId() != null) {
            Country country = countryRepository.findById(updateAdmissionApplicationInput.getCountryId()).get();
            admissionApplication.setCountry(country);
        }
        if (updateAdmissionApplicationInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateAdmissionApplicationInput.getDepartmentId()).get();
            admissionApplication.setDepartment(department);
        }
        if (updateAdmissionApplicationInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateAdmissionApplicationInput.getAcademicyearId()).get();
            admissionApplication.setAcademicyear(academicYear);
        }

        admissionApplicationRepository.save(admissionApplication);

        return new UpdateAdmissionApplicationPayload(admissionApplication);
    }

    public RemoveAdmissionApplicationPayload removeAdmissionApplication(RemoveAdmissionApplicationInput removeAdmissionApplicationsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(removeAdmissionApplicationsInput.getAdmissionApplicationId()).get();
        admissionApplicationRepository.delete(admissionApplication);
        return new RemoveAdmissionApplicationPayload(Lists.newArrayList(admissionApplicationRepository.findAll()));
    }
//    public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
//        TypeOfGrading typeOfGrading = null;
//        // get the max id from database.
//        int groupvalue = getNextGradeId()+1;
//        for (AddTypeOfGradingInput input : list) {
//            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
//            typeOfGrading.setGroupvalue(new Long(groupvalue));
//            this.typeOfGradingRepository.save(typeOfGrading);
//        }
//        return new AddTypeOfGradingPayload(typeOfGrading);
//    }
//
//    private int getNextGradeId(){
//        String sql = "select max(groupvalue_id) from type_of_grading";
//        Query query = this.entityManager.createNativeQuery(sql);
//        Object groupValue = query.getSingleResult();
//        return ((BigInteger)groupValue).intValue() ;
//    }
//public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
//        TypeOfGrading typeOfGrading = null;
//        // get the max id from database.
//        int groupvalue = getNextGradeId()+1;
//        for (AddTypeOfGradingInput input : list) {
//            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
//            typeOfGrading.setGroupvalue(new Long(groupvalue));
//            this.typeOfGradingRepository.save(typeOfGrading);
//        }
//        return new AddTypeOfGradingPayload(typeOfGrading);
//    }
    public AddAcademicExamSettingPayload addAcademicExamSetting(List<AddAcademicExamSettingInput> list) {
        AcademicExamSetting academicExamSetting = null;
        int countvalue = getCountvalueId()+1;
        for(AddAcademicExamSettingInput input: list ) {
            Branch branch = branchRepository.findById(input.getBranchId()).get();
            Subject subject = subjectRepository.findById(input.getSubjectId()).get();
            AcademicYear academicYear = academicYearRepository.findById(input.getAcademicyearId()).get();
            Batch batch = batchRepository.findById(input.getBatchId()).get();
            Section section = sectionRepository.findById(input.getSectionId()).get();
            Department department = departmentRepository.findById(input.getDepartmentId()).get();
            TypeOfGrading typeOfGrading = typeOfGradingRepository.findById((input.getTypeOfGradingId())).get();

            academicExamSetting = CommonUtil.createCopyProperties(input, AcademicExamSetting.class);
            academicExamSetting.setCountvalue(new Long(countvalue));
            academicExamSetting.setBranch(branch);
            academicExamSetting.setSubject(subject);
            academicExamSetting.setBatch(batch);
            academicExamSetting.setAcademicyear(academicYear);
            academicExamSetting.setSection(section);
            academicExamSetting.setDepartment(department);
            academicExamSetting.setTypeOfGrading(typeOfGrading);
            academicExamSetting.setExamDate(DateFormatUtil.convertLocalDateFromUtilDate(input.getExamDate()));
            this.academicExamSettingRepository.save(academicExamSetting);

        }
        return  new AddAcademicExamSettingPayload(academicExamSetting);
    }
    private int getCountvalueId(){
        String sql = "select max(countvalue) from academic_exam_setting";
        Query query = this.entityManager.createNativeQuery(sql);
        Object countvalue = query.getSingleResult();
        return ((BigInteger)countvalue).intValue() ;
    }

    public UpdateAcademicExamSettingPayload updateAcademicExamSetting(UpdateAcademicExamSettingInput updateAcademicExamSettingInput) {
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(updateAcademicExamSettingInput.getId()).get();


        if (updateAcademicExamSettingInput.getExamName() != null) {
            academicExamSetting.setExamName(updateAcademicExamSettingInput.getExamName());
        }
        if (updateAcademicExamSettingInput.getSemester() != null) {
            academicExamSetting.setSemester(updateAcademicExamSettingInput.getSemester());
        }
        if (updateAcademicExamSettingInput.getExamDate() != null) {
            academicExamSetting.setExamDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicExamSettingInput.getExamDate()));
        }
        if (updateAcademicExamSettingInput.getStartTime() != null) {
            academicExamSetting.setStartTime(updateAcademicExamSettingInput.getStartTime());
        }
        if (updateAcademicExamSettingInput.getEndTime() != null) {
            academicExamSetting.setEndTime(updateAcademicExamSettingInput.getEndTime());
        }
        if (updateAcademicExamSettingInput.getGradeType() != null) {
            academicExamSetting.setGradeType(updateAcademicExamSettingInput.getGradeType());
        }
        if (updateAcademicExamSettingInput.getTotal() != null) {
            academicExamSetting.setTotal(updateAcademicExamSettingInput.getTotal());
        }
        if (updateAcademicExamSettingInput.getPassing() != null) {
            academicExamSetting.setPassing(updateAcademicExamSettingInput.getPassing());
        }
        if (updateAcademicExamSettingInput.getActions() != null) {
            academicExamSetting.setActions(updateAcademicExamSettingInput.getActions());
        }
        if (updateAcademicExamSettingInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateAcademicExamSettingInput.getDepartmentId()).get();
            academicExamSetting.setDepartment(department);
        }
        if (updateAcademicExamSettingInput.getAcademicyearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateAcademicExamSettingInput.getAcademicyearId()).get();
            academicExamSetting.setAcademicyear(academicYear);
        }
        if (updateAcademicExamSettingInput.getSubjectId() != null) {
            final Subject subject = subjectRepository.findById(updateAcademicExamSettingInput.getSubjectId()).get();
            academicExamSetting.setSubject(subject);
        }
        if (updateAcademicExamSettingInput.getSectionId() != null) {
            final Section section = sectionRepository.findById(updateAcademicExamSettingInput.getSectionId()).get();
            academicExamSetting.setSection(section);
        }
        if (updateAcademicExamSettingInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateAcademicExamSettingInput.getBatchId()).get();
            academicExamSetting.setBatch(batch);
        }
        if (updateAcademicExamSettingInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateAcademicExamSettingInput.getBranchId()).get();
            academicExamSetting.setBranch(branch);
        }
        if (updateAcademicExamSettingInput.getTypeOfGradingId() != null) {
            final TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateAcademicExamSettingInput.getTypeOfGradingId()).get();
            academicExamSetting.setTypeOfGrading(typeOfGrading);
        }

//        if (updateAcademicExamSettingInput.getCountvalue() != null) {
//            academicExamSetting.setCountvalue(updateAcademicExamSettingInput.getCountvalue());
//        }


        academicExamSettingRepository.save(academicExamSetting);

        return new UpdateAcademicExamSettingPayload(academicExamSetting);
    }

    public RemoveAcademicExamSettingPayload removeAcademicExamSetting(RemoveAcademicExamSettingInput removeAcademicExamSettingsInput) {
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(removeAcademicExamSettingsInput.getAcademicExamSettingId()).get();
        academicExamSettingRepository.delete(academicExamSetting);
        return new RemoveAcademicExamSettingPayload(Lists.newArrayList(academicExamSettingRepository.findAll()));
    }

    public AddAcademicHistoryPayload addAcademicHistory(AddAcademicHistoryInput addAcademicHistoryInput) {
        final Student student = studentRepository.findById(addAcademicHistoryInput.getStudentId()).get();

        final AcademicHistory academicHistory = new AcademicHistory();
        academicHistory.setStudent(student);
        academicHistory.setQualification(addAcademicHistoryInput.getQualification());
        academicHistory.setYearOfPassing(addAcademicHistoryInput.getYearOfPassing());
        academicHistory.setInstitution(addAcademicHistoryInput.getInstitution());
        academicHistory.setUniversity(addAcademicHistoryInput.getUniversity());
        academicHistory.setEnrollmentNo(addAcademicHistoryInput.getEnrollmentNo());
        academicHistory.setScore(addAcademicHistoryInput.getScore());
        academicHistory.setPercentage(addAcademicHistoryInput.getPercentage());
        academicHistoryRepository.save(academicHistory);
        return new AddAcademicHistoryPayload(academicHistory);
    }

    public UpdateAcademicHistoryPayload updateAcademicHistory(UpdateAcademicHistoryInput updateAcademicHistoryInput) {
        AcademicHistory academicHistory = academicHistoryRepository.findById(updateAcademicHistoryInput.getId()).get();
        if (updateAcademicHistoryInput.getQualification() != null) {
            academicHistory.setQualification(updateAcademicHistoryInput.getQualification());
        }
        if (updateAcademicHistoryInput.getYearOfPassing() != null) {
            academicHistory.setYearOfPassing(updateAcademicHistoryInput.getYearOfPassing());
        }
        if (updateAcademicHistoryInput.getInstitution() != null) {
            academicHistory.setInstitution(updateAcademicHistoryInput.getInstitution());
        }
        if (updateAcademicHistoryInput.getUniversity() != null) {
            academicHistory.setUniversity(updateAcademicHistoryInput.getUniversity());
        }
        if (updateAcademicHistoryInput.getEnrollmentNo() != null) {
            academicHistory.setEnrollmentNo(updateAcademicHistoryInput.getEnrollmentNo());
        }
        if (updateAcademicHistoryInput.getScore() != null) {
            academicHistory.setScore(updateAcademicHistoryInput.getScore());
        }
        if (updateAcademicHistoryInput.getPercentage() != null) {
            academicHistory.setPercentage(updateAcademicHistoryInput.getPercentage());
        }

        if (updateAcademicHistoryInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateAcademicHistoryInput.getStudentId()).get();
            academicHistory.setStudent(student);
        }
        academicHistoryRepository.save(academicHistory);

        return new UpdateAcademicHistoryPayload(academicHistory);
    }

    public RemoveAcademicHistoryPayload removeAcademicHistory(RemoveAcademicHistoryInput removeAcademicHistoryInput) {
        AcademicHistory academicHistory = academicHistoryRepository.findById(removeAcademicHistoryInput.getAcademicHistoryId()).get();
        academicHistoryRepository.delete(academicHistory);
        return new RemoveAcademicHistoryPayload(Lists.newArrayList(academicHistoryRepository.findAll()));
    }

    public AddTypeOfGradingPayload addTypeOfGrading(List<AddTypeOfGradingInput> list) {
        TypeOfGrading typeOfGrading = null;
        // get the max id from database.
        int groupvalue = getNextGradeId()+1;
        for (AddTypeOfGradingInput input : list) {
            typeOfGrading = CommonUtil.createCopyProperties(input, TypeOfGrading.class);
            typeOfGrading.setGroupvalue(new Long(groupvalue));
            this.typeOfGradingRepository.save(typeOfGrading);
        }
            return new AddTypeOfGradingPayload(typeOfGrading);
    }

    private int getNextGradeId(){
        String sql = "select max(groupvalue) from type_of_grading";
        Query query = this.entityManager.createNativeQuery(sql);
        Object groupValue = query.getSingleResult();
        return ((BigInteger)groupValue).intValue() ;
    }

    public UpdateTypeOfGradingPayload updateTypeOfGrading(UpdateTypeOfGradingInput updateTypeOfGradingInput) {
        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateTypeOfGradingInput.getId()).get();
        if (updateTypeOfGradingInput.getMinMarks() != null) {
            typeOfGrading.setMinMarks(updateTypeOfGradingInput.getMinMarks());
        }
        if (updateTypeOfGradingInput.getMaxMarks() != null) {
            typeOfGrading.setMaxMarks(updateTypeOfGradingInput.getMaxMarks());
        }
        if (updateTypeOfGradingInput.getGrades() != null) {
            typeOfGrading.setGrades(updateTypeOfGradingInput.getGrades());
        }
        typeOfGradingRepository.save(typeOfGrading);

        return new UpdateTypeOfGradingPayload(typeOfGrading);

    }

    public RemoveTypeOfGradingPayload removeTypeOfGrading(RemoveTypeOfGradingInput removeTypeOfGradingInput) {
        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(removeTypeOfGradingInput.getTypeOfGradingId()).get();
        typeOfGradingRepository.delete(typeOfGrading);
        return new RemoveTypeOfGradingPayload(Lists.newArrayList(typeOfGradingRepository.findAll()));
    }


    public AddAdmissionEnquiryPayload addAdmissionEnquiry(AddAdmissionEnquiryInput addAdmissionEnquiryInput) {
        final AdmissionEnquiry admissionEnquiry = new AdmissionEnquiry();
        admissionEnquiry.setStudentName(addAdmissionEnquiryInput.getStudentName());
        admissionEnquiry.setStudentMiddleName(addAdmissionEnquiryInput.getStudentMiddleName());
        admissionEnquiry.setStudentLastName(addAdmissionEnquiryInput.getStudentLastName());
        admissionEnquiry.setFatherName(addAdmissionEnquiryInput.getFatherName());
        admissionEnquiry.setFatherMiddleName(addAdmissionEnquiryInput.getFatherMiddleName());
        admissionEnquiry.setFatherLastName(addAdmissionEnquiryInput.getFatherLastName());
        admissionEnquiry.setMotherName(addAdmissionEnquiryInput.getMotherName());
        admissionEnquiry.setMotherMiddleName(addAdmissionEnquiryInput.getMotherMiddleName());
        admissionEnquiry.setMotherLastName(addAdmissionEnquiryInput.getMotherLastName());
        admissionEnquiry.setContactNumber(addAdmissionEnquiryInput.getContactNumber());
        admissionEnquiry.setAlternateMobileNumber(addAdmissionEnquiryInput.getAlternateMobileNumber());
        admissionEnquiry.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getDateOfBirth()));
        admissionEnquiry.setEmail(addAdmissionEnquiryInput.getEmail());
        admissionEnquiry.setSex(addAdmissionEnquiryInput.getSex());
        admissionEnquiry.setComments(addAdmissionEnquiryInput.getComments());
        admissionEnquiry.setCourseApplyingFor(addAdmissionEnquiryInput.getCourseApplyingFor());
        admissionEnquiry.setHighestQualification(addAdmissionEnquiryInput.getHighestQualification());
        admissionEnquiry.setModeOfEnquiry(addAdmissionEnquiryInput.getModeOfEnquiry());
        admissionEnquiry.setStatus(addAdmissionEnquiryInput.getStatus());
        admissionEnquiry.setDescription(addAdmissionEnquiryInput.getDescription());
        admissionEnquiry.setEnquiryDate(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getEnquiryDate()));
        admissionEnquiry.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionEnquiryInput.getUpdatedOn()));
        admissionEnquiry.setUpdatedBy(addAdmissionEnquiryInput.getUpdatedBy());
        Branch branch = branchRepository.findById(addAdmissionEnquiryInput.getBranchId()).get();
        Department department = departmentRepository.findById(addAdmissionEnquiryInput.getDepartmentId()).get();
        State state = stateRepository.findById(addAdmissionEnquiryInput.getStateId()).get();
        City city = cityRepository.findById(addAdmissionEnquiryInput.getCityId()).get();
        Country country = countryRepository.findById(addAdmissionEnquiryInput.getCountryId()).get();
        Batch batch = batchRepository.findById(addAdmissionEnquiryInput.getBatchId()).get();
        admissionEnquiry.setBranch(branch);
        admissionEnquiry.setDepartment(department);
        admissionEnquiry.setBatch(batch);
        admissionEnquiry.setState(state);
        admissionEnquiry.setCity(city);
        admissionEnquiry.setCountry(country);
        admissionEnquiryRepository.save(admissionEnquiry);
        return new AddAdmissionEnquiryPayload(admissionEnquiry);
    }


    public UpdateAdmissionEnquiryPayload updateAdmissionEnquiry(UpdateAdmissionEnquiryInput updateAdmissionEnquiryInput) {
        AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(updateAdmissionEnquiryInput.getId()).get();

        if (updateAdmissionEnquiryInput.getStudentName() != null) {
            admissionEnquiry.setStudentName(updateAdmissionEnquiryInput.getStudentName());
        }
        if (updateAdmissionEnquiryInput.getStudentMiddleName() != null) {
            admissionEnquiry.setStudentMiddleName(updateAdmissionEnquiryInput.getStudentMiddleName());
        }
        if (updateAdmissionEnquiryInput.getStudentLastName() != null) {
            admissionEnquiry.setStudentLastName(updateAdmissionEnquiryInput.getStudentLastName());
        }
        if (updateAdmissionEnquiryInput.getFatherName() != null) {
            admissionEnquiry.setFatherName(updateAdmissionEnquiryInput.getFatherName());
        }
        if (updateAdmissionEnquiryInput.getFatherMiddleName() != null) {
            admissionEnquiry.setFatherMiddleName(updateAdmissionEnquiryInput.getFatherMiddleName());
        }
        if (updateAdmissionEnquiryInput.getFatherLastName() != null) {
            admissionEnquiry.setFatherLastName(updateAdmissionEnquiryInput.getFatherLastName());
        }
        if (updateAdmissionEnquiryInput.getMotherName() != null) {
            admissionEnquiry.setMotherName(updateAdmissionEnquiryInput.getMotherName());
        }
        if (updateAdmissionEnquiryInput.getMotherMiddleName() != null) {
            admissionEnquiry.setMotherMiddleName(updateAdmissionEnquiryInput.getMotherMiddleName());
        }
        if (updateAdmissionEnquiryInput.getMotherLastName() != null) {
            admissionEnquiry.setMotherLastName(updateAdmissionEnquiryInput.getMotherLastName());
        }
        if (updateAdmissionEnquiryInput.getContactNumber() != null) {
            admissionEnquiry.setContactNumber(updateAdmissionEnquiryInput.getContactNumber());
        }
        if (updateAdmissionEnquiryInput.getAlternateMobileNumber() != null) {
            admissionEnquiry.setAlternateMobileNumber(updateAdmissionEnquiryInput.getAlternateMobileNumber());
        }
        if (updateAdmissionEnquiryInput.getDateOfBirth() != null) {
            admissionEnquiry.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getDateOfBirth()));
        }
        if (updateAdmissionEnquiryInput.getEmail() != null) {
            admissionEnquiry.setEmail(updateAdmissionEnquiryInput.getEmail());
        }
        if (updateAdmissionEnquiryInput.getSex() != null) {
            admissionEnquiry.setSex(updateAdmissionEnquiryInput.getSex());
        }
        if (updateAdmissionEnquiryInput.getComments() != null) {
            admissionEnquiry.setComments(updateAdmissionEnquiryInput.getComments());
        }
        if (updateAdmissionEnquiryInput.getCourseApplyingFor() != null) {
            admissionEnquiry.setCourseApplyingFor(updateAdmissionEnquiryInput.getCourseApplyingFor());
        }
        if (updateAdmissionEnquiryInput.getHighestQualification() != null) {
            admissionEnquiry.setHighestQualification(updateAdmissionEnquiryInput.getHighestQualification());
        }
        if (updateAdmissionEnquiryInput.getModeOfEnquiry() != null) {
            admissionEnquiry.setModeOfEnquiry(updateAdmissionEnquiryInput.getModeOfEnquiry());
        }
        if (updateAdmissionEnquiryInput.getStatus() != null) {
            admissionEnquiry.setStatus(updateAdmissionEnquiryInput.getStatus());
        }
        if (updateAdmissionEnquiryInput.getDescription() != null) {
            admissionEnquiry.setDescription(updateAdmissionEnquiryInput.getDescription());
        }
        if (updateAdmissionEnquiryInput.getEnquiryDate() != null) {
            admissionEnquiry.setEnquiryDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getEnquiryDate()));
        }
        if (updateAdmissionEnquiryInput.getUpdatedOn() != null) {
            admissionEnquiry.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionEnquiryInput.getUpdatedOn()));
        }
        if (updateAdmissionEnquiryInput.getUpdatedBy() != null) {
            admissionEnquiry.setUpdatedBy(updateAdmissionEnquiryInput.getUpdatedBy());
        }
        if (updateAdmissionEnquiryInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateAdmissionEnquiryInput.getBranchId()).get();
            admissionEnquiry.setBranch(branch);
        }
        if (updateAdmissionEnquiryInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateAdmissionEnquiryInput.getBatchId()).get();
            admissionEnquiry.setBatch(batch);
        }
        if (updateAdmissionEnquiryInput.getStateId() != null) {
            State state = stateRepository.findById(updateAdmissionEnquiryInput.getStateId()).get();
            admissionEnquiry.setState(state);
        }
        if (updateAdmissionEnquiryInput.getCityId() != null) {
            City city = cityRepository.findById(updateAdmissionEnquiryInput.getCityId()).get();
            admissionEnquiry.setCity(city);
        }
        if (updateAdmissionEnquiryInput.getCountryId() != null) {
            Country country = countryRepository.findById(updateAdmissionEnquiryInput.getCountryId()).get();
            admissionEnquiry.setCountry(country);
        }
        if (updateAdmissionEnquiryInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateAdmissionEnquiryInput.getDepartmentId()).get();
            admissionEnquiry.setDepartment(department);
        }
        admissionEnquiryRepository.save(admissionEnquiry);
        return new UpdateAdmissionEnquiryPayload(admissionEnquiry);
    }

    public RemoveAdmissionEnquiryPayload removeAdmissionEnquiry(RemoveAdmissionEnquiryInput removeAdmissionEnquiryInput) {
        AdmissionEnquiry admissionEnquiry = admissionEnquiryRepository.findById(removeAdmissionEnquiryInput.getAdmissionEnquiryId()).get();
        admissionEnquiryRepository.delete(admissionEnquiry);
        return new RemoveAdmissionEnquiryPayload(Lists.newArrayList(admissionEnquiryRepository.findAll()));
    }

    public AddStudentPayload addStudent(AddStudentInput addStudentInput) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
        final Section section = sectionRepository.findById(addStudentInput.getSectionId()).get();
        final Branch branch = branchRepository.findById(addStudentInput.getBranchId()).get();
        final Department department = departmentRepository.findById(addStudentInput.getDepartmentId()).get();
        final Batch batch = batchRepository.findById(addStudentInput.getBatchId()).get();
        Student student = CommonUtil.createCopyProperties(addStudentInput, Student.class);
        student.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addStudentInput.getDateOfBirth()));
        student.setUploadPhoto("");
        student.setBatch(batch);
        student.setSection(section);
        student.setBranch(branch);
        student.setDepartment(department);
        logger.info("Saving student record.");
        student = studentRepository.save(student);
        saveStudentImage(addStudentInput, student, branch);
        return new AddStudentPayload(student);
    }

    private void saveStudentImage(AbstractStudentInput input, Student student, Branch branch) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
    	String temp = CmsConstants.STUDENT_IMAGE_FILE_PATH.replaceAll("COLLEGE_ID", CmsConstants.COLLEGE_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getCollege().getId()));
    	String filePath = Paths.get("", temp).toString();
    	String fileName = String.valueOf(student.getId());
    	String ext = this.base64FileProcessor.getFileExtensionFromBase64Srting(input.getFileName().split(",")[0]);
    	String absFilePath = filePath+ File.separator+CmsConstants.BRANCH_ID_PLACEHOLDER_REPLACER+String.valueOf(branch.getId())+File.separator + fileName+"."+ext;
    	student.setUploadPhoto(absFilePath);
    	logger.info("Saving student image. File path: "+absFilePath);
    	this.base64FileProcessor.createFileFromBase64String(input.getFileName(), filePath, fileName, String.valueOf(branch.getId()), null);
    	logger.info("Updating student record with image file path: "+absFilePath);
    	this.studentRepository.save(student);
    }


    public UpdateStudentPayload updateStudent(UpdateStudentInput updateStudentInput) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
        Student student = studentRepository.findById(updateStudentInput.getId()).get();
        if (updateStudentInput.getStudentName() != null) {
            student.setStudentName(updateStudentInput.getStudentName());
        }
        if (updateStudentInput.getStudentMiddleName() != null) {
            student.setStudentMiddleName(updateStudentInput.getStudentMiddleName());
        }
        if (updateStudentInput.getStudentLastName() != null) {
            student.setStudentLastName(updateStudentInput.getStudentLastName());
        }
        if (updateStudentInput.getFatherName() != null) {
            student.setFatherName(updateStudentInput.getFatherName());
        }
        if (updateStudentInput.getFatherMiddleName() != null) {
            student.setFatherMiddleName(updateStudentInput.getFatherMiddleName());
        }
        if (updateStudentInput.getFatherLastName() != null) {
            student.setFatherLastName(updateStudentInput.getFatherLastName());
        }
        if (updateStudentInput.getMotherName() != null) {
            student.setMotherName(updateStudentInput.getMotherName());
        }
        if (updateStudentInput.getMotherMiddleName() != null) {
            student.setMotherMiddleName(updateStudentInput.getMotherMiddleName());
        }
        if (updateStudentInput.getMotherLastName() != null) {
            student.setMotherLastName(updateStudentInput.getMotherLastName());
        }
        if (updateStudentInput.getAadharNo() != null) {
            student.setAadharNo(updateStudentInput.getAadharNo());
        }
        if (updateStudentInput.getDateOfBirth() != null) {
            student.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateStudentInput.getDateOfBirth()));
        }
        if (updateStudentInput.getPlaceOfBirth() != null) {
            student.setPlaceOfBirth(updateStudentInput.getPlaceOfBirth());
        }
        if (updateStudentInput.getReligion() != null) {
            student.setReligion(updateStudentInput.getReligion());
        }
        if (updateStudentInput.getCaste() != null) {
            student.setCaste(updateStudentInput.getCaste());
        }
        if (updateStudentInput.getSubCaste() != null) {
            student.setSubCaste(updateStudentInput.getSubCaste());
        }
        if (updateStudentInput.getAge() != null) {
            student.setAge(updateStudentInput.getAge());
        }
        if (updateStudentInput.getSex() != null) {
            student.setSex(updateStudentInput.getSex());
        }
        if (updateStudentInput.getBloodGroup() != null) {
            student.setBloodGroup(updateStudentInput.getBloodGroup());
        }
        if (updateStudentInput.getAddressLineOne() != null) {
            student.setAddressLineOne(updateStudentInput.getAddressLineOne());
        }
        if (updateStudentInput.getAddressLineTwo() != null) {
            student.setAddressLineTwo(updateStudentInput.getAddressLineTwo());
        }
        if (updateStudentInput.getAddressLineThree() != null) {
            student.setAddressLineThree(updateStudentInput.getAddressLineThree());
        }
        if (updateStudentInput.getTown() != null) {
            student.setTown(updateStudentInput.getTown());
        }
        if (updateStudentInput.getState() != null) {
            student.setState(updateStudentInput.getState());
        }
        if (updateStudentInput.getCountry() != null) {
            student.setCountry(updateStudentInput.getCountry());
        }
        if (updateStudentInput.getPincode() != null) {
            student.setPincode(updateStudentInput.getPincode());
        }
        if (updateStudentInput.getStudentContactNumber() != null) {
            student.setStudentContactNumber(updateStudentInput.getStudentContactNumber());
        }
        if (updateStudentInput.getAlternateContactNumber() != null) {
            student.setAlternateContactNumber(updateStudentInput.getAlternateContactNumber());
        }
        if (updateStudentInput.getStudentEmailAddress() != null) {
            student.setStudentEmailAddress(updateStudentInput.getStudentEmailAddress());
        }
        if (updateStudentInput.getAlternateEmailAddress() != null) {
            student.setAlternateEmailAddress(updateStudentInput.getAlternateEmailAddress());
        }
        if (updateStudentInput.getRelationWithStudent() != null) {
            student.setRelationWithStudent(updateStudentInput.getRelationWithStudent());
        }
        if (updateStudentInput.getEmergencyContactName() != null) {
            student.setEmergencyContactName(updateStudentInput.getEmergencyContactName());
        }
        if (updateStudentInput.getEmergencyContactMiddleName() != null) {
            student.setEmergencyContactMiddleName(updateStudentInput.getEmergencyContactMiddleName());
        }
        if (updateStudentInput.getEmergencyContactLastName() != null) {
            student.setEmergencyContactLastName(updateStudentInput.getEmergencyContactLastName());
        }
        if (updateStudentInput.getEmergencyContactNo() != null) {
            student.setEmergencyContactNo(updateStudentInput.getEmergencyContactNo());
        }
        if (updateStudentInput.getEmergencyContactEmailAddress() != null) {
            student.setEmergencyContactEmailAddress(updateStudentInput.getEmergencyContactEmailAddress());
        }

//        if (updateStudentInput.getUploadPhoto() != null) {
//            student.setUploadPhoto(updateStudentInput.getUploadPhoto());
//        }

        if (updateStudentInput.getAdmissionNo() != null) {
            student.setAdmissionNo(updateStudentInput.getAdmissionNo());
        }
        if (updateStudentInput.getRollNo() != null) {
            student.setRollNo(updateStudentInput.getRollNo());
        }
        if (updateStudentInput.getStudentType() != null) {
            student.setStudentType(updateStudentInput.getStudentType());
        }
        if (updateStudentInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateStudentInput.getBatchId()).get();
            student.setBatch(batch);
        }
        if (updateStudentInput.getSectionId() != null) {
            final Section section = sectionRepository.findById(updateStudentInput.getSectionId()).get();
            student.setSection(section);
        }
        if (updateStudentInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateStudentInput.getBranchId()).get();
            student.setBranch(branch);
        }
        if (updateStudentInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateStudentInput.getDepartmentId()).get();
            student.setDepartment(department);
        }

        saveStudentImage(updateStudentInput, student, student.getBranch());

        studentRepository.save(student);

        return new UpdateStudentPayload(student);
    }

    public RemoveStudentPayload removeStudent(RemoveStudentInput removeStudentInput) {
        Student student = studentRepository.findById(removeStudentInput.getStudentId()).get();
        studentRepository.delete(student);
        return new RemoveStudentPayload(Lists.newArrayList(studentRepository.findAll()));
    }


//    public AddInstitutePayload addInstitute(AddInstituteInput addInstituteInput) {
//        final Institute institute = new Institute();
//        institute.setCode(addInstituteInput.getCode());
//        institute.setName(addInstituteInput.getName());
//        institute.setYear(addInstituteInput.getYear());
//
//        instituteRepository.save(institute);
//
//        return new AddInstitutePayload(institute);
//    }

    /*public AddStudentYearPayload addStudentYear(AddStudentYearInput addStudentYearInput) {
        final StudentYear studentYear = new StudentYear();
        studentYear.setYearDesc(addStudentYearInput.getYearDesc());
        studentYearRepository.save(studentYear);

        return new AddStudentYearPayload(studentYear);
    }

    public UpdateStudentYearPayload updateStudentYear(UpdateStudentYearInput updateStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(updateStudentYearInput.getId()).get();
        if (updateStudentYearInput.getYearDesc() != null) {
            studentYear.setYearDesc(updateStudentYearInput.getYearDesc());
        }

        studentYearRepository.save(studentYear);

        return new UpdateStudentYearPayload(studentYear);
    }

    public RemoveStudentYearPayload removeStudentYear(RemoveStudentYearInput removeStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(removeStudentYearInput.getStudentYearId()).get();
        studentYearRepository.delete(studentYear);

        return new RemoveStudentYearPayload(Lists.newArrayList(studentYearRepository.findAll()));
    }*/

    public AddBankAccountsPayload addBankAccounts(AddBankAccountsInput addBankAccountsInput) {
        final BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setNameOfBank(addBankAccountsInput.getNameOfBank());
        bankAccounts.setAccountNumber(addBankAccountsInput.getAccountNumber());
        bankAccounts.setTypeOfAccount(addBankAccountsInput.getTypeOfAccount());
        bankAccounts.setIfscCode(addBankAccountsInput.getIfscCode());
        bankAccounts.setBranchAddress(addBankAccountsInput.getBranchAddress());
        bankAccounts.setCorporateId(addBankAccountsInput.getCorporateId());
        Branch branch = branchRepository.findById(addBankAccountsInput.getBranchId()).get();
        College college = collegeRepository.findById(addBankAccountsInput.getCollegeId()).get();
        bankAccounts.setBranch(branch);
        bankAccounts.setCollege(college);
        bankAccountsRepository.save(bankAccounts);

        return new AddBankAccountsPayload(bankAccounts);
    }

    public UpdateBankAccountsPayload updateBankAccounts(UpdateBankAccountsInput updateBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(updateBankAccountsInput.getId()).get();
        if (updateBankAccountsInput.getNameOfBank() != null) {
            bankAccounts.setNameOfBank(updateBankAccountsInput.getNameOfBank());
        }
        if (updateBankAccountsInput.getAccountNumber() != null) {
            bankAccounts.setAccountNumber(updateBankAccountsInput.getAccountNumber());
        }
        if (updateBankAccountsInput.getTypeOfAccount() != null) {
            bankAccounts.setTypeOfAccount(updateBankAccountsInput.getTypeOfAccount());
        }
        if (updateBankAccountsInput.getIfscCode() != null) {
            bankAccounts.setIfscCode(updateBankAccountsInput.getIfscCode());
        }
        if (updateBankAccountsInput.getBranchAddress() != null) {
            bankAccounts.setBranchAddress(updateBankAccountsInput.getBranchAddress());
        }
        if (updateBankAccountsInput.getCorporateId() != null) {
            bankAccounts.setCorporateId(updateBankAccountsInput.getCorporateId());
        }
        if (updateBankAccountsInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateBankAccountsInput.getBranchId()).get();
            bankAccounts.setBranch(branch);
        }
        if (updateBankAccountsInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateBankAccountsInput.getCollegeId()).get();
            bankAccounts.setCollege(college);
        }

        bankAccountsRepository.save(bankAccounts);

        return new UpdateBankAccountsPayload(bankAccounts);
    }

    public RemoveBankAccountsPayload removeBankAccounts(RemoveBankAccountsInput removeBankAccountsInput) {
        BankAccounts bankAccounts = bankAccountsRepository.findById(removeBankAccountsInput.getBankAccountsId()).get();
        bankAccountsRepository.delete(bankAccounts);
        return new RemoveBankAccountsPayload(Lists.newArrayList(bankAccountsRepository.findAll()));
    }

    public AddDepartmentPayload addDepartment(AddDepartmentInput addDepartmentsInput) {
//    	Student student = studentRepository.findById(addDepartmentsInput.getStudentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addDepartmentsInput.getAcademicyearId()).get();
        Branch branch = branchRepository.findById(addDepartmentsInput.getBranchId()).get();
        final Department departments = new Department();
        departments.setName(addDepartmentsInput.getName());
        departments.setDescription(addDepartmentsInput.getDescription());
        departments.setDeptHead(addDepartmentsInput.getDeptHead());
//        departments.setStudent(student);
        departments.setBranch(branch);
        departments.setAcademicyear(academicYear);
        departmentRepository.save(departments);

        return new AddDepartmentPayload(departments);
    }

    public UpdateDepartmentPayload updateDepartment(UpdateDepartmentInput updateDepartmentsInput) {
        Department departments = departmentRepository.findById(updateDepartmentsInput.getId()).get();
        if (updateDepartmentsInput.getName() != null) {
            departments.setName(updateDepartmentsInput.getName());
        }
        if (updateDepartmentsInput.getDescription() != null) {
            departments.setDescription(updateDepartmentsInput.getDescription());
        }
        if (updateDepartmentsInput.getDeptHead() != null) {
            departments.setDeptHead(updateDepartmentsInput.getDeptHead());
        }
//        if(updateDepartmentsInput.getStudentId() != null) {
//        	Student student = studentRepository.findById(updateDepartmentsInput.getStudentId()).get();
//        	departments.setStudent(student);
//        }
        if (updateDepartmentsInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateDepartmentsInput.getBranchId()).get();
            departments.setBranch(branch);
        }
        if (updateDepartmentsInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateDepartmentsInput.getAcademicyearId()).get();
            departments.setAcademicyear(academicYear);
        }
        departmentRepository.save(departments);

        return new UpdateDepartmentPayload(departments);
    }

    public RemoveDepartmentPayload removeDepartment(RemoveDepartmentInput removeDepartmentsInput) {
        Department departments = departmentRepository.findById(removeDepartmentsInput.getDepartmentId()).get();
        departmentRepository.delete(departments);
        return new RemoveDepartmentPayload(Lists.newArrayList(departmentRepository.findAll()));
    }
    public AddCompetitiveExamPayload addCompetitiveExam(AddCompetitiveExamInput addCompetitiveExamInput) {
        final Student student = studentRepository.findById(addCompetitiveExamInput.getStudentId()).get();

        final CompetitiveExam competitiveExam = new CompetitiveExam();
        competitiveExam.setStudent(student);
        competitiveExam.setTestName(addCompetitiveExamInput.getTestName());
        competitiveExam.setTestScore(addCompetitiveExamInput.getTestScore());
        competitiveExam.setEnrollmentNo(addCompetitiveExamInput.getEnrollmentNo());
        competitiveExam.setRank(addCompetitiveExamInput.getRank());
        competitiveExamRepository.save(competitiveExam);
        return new AddCompetitiveExamPayload(competitiveExam);
    }

    public UpdateCompetitiveExamPayload updateCompetitiveExam(UpdateCompetitiveExamInput updateCompetitiveExamInput) {
        final CompetitiveExam competitiveExam = competitiveExamRepository.findById(updateCompetitiveExamInput.getId()).get();

        if (updateCompetitiveExamInput.getTestName() != null){
            competitiveExam.setTestName(updateCompetitiveExamInput.getTestName());
        }
        if(updateCompetitiveExamInput.getTestScore() != null){
            competitiveExam.setTestScore(updateCompetitiveExamInput.getTestScore());
        }
        if(updateCompetitiveExamInput.getEnrollmentNo() != null){
            competitiveExam.setEnrollmentNo(updateCompetitiveExamInput.getEnrollmentNo());
        }

        if(updateCompetitiveExamInput.getRank() != null){
            competitiveExam.setRank(updateCompetitiveExamInput.getRank());
        }

        if (updateCompetitiveExamInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateCompetitiveExamInput.getStudentId()).get();
            competitiveExam.setStudent(student);
        }
        competitiveExamRepository.save(competitiveExam);

        return new UpdateCompetitiveExamPayload(competitiveExam);
    }


    public RemoveCompetitiveExamPayload removeCompetitiveExam(RemoveCompetitiveExamInput removeCompetitiveExamInput) {
        CompetitiveExam CompetitiveExam = competitiveExamRepository.findById(removeCompetitiveExamInput.getCompetitiveExamId()).get();
        competitiveExamRepository.delete(CompetitiveExam);
        return new RemoveCompetitiveExamPayload(Lists.newArrayList(competitiveExamRepository.findAll()));
    }


    public AddDocumentsPayload addDocuments(AddDocumentsInput addDocumentsInput) {
        final Student student = studentRepository.findById(addDocumentsInput.getStudentId()).get();
        final Vehicle vehicle = vehicleRepository.findById(addDocumentsInput.getVehicleId()).get();
        final Employee employee = employeeRepository.findById(addDocumentsInput.getEmployeeId()).get();
        final Contract contract = contractRepository.findById(addDocumentsInput.getContractId()).get();
        final Documents documents = new Documents();
        documents.setStudent(student);
        documents.setVehicle(vehicle);
        documents.setContract(contract);
        documents.setEmployee(employee);
        documents.setDocumentFilePath(addDocumentsInput.getDocumentFilePath());
        documents.setDocumentName(addDocumentsInput.getDocumentName());
        documentsRepository.save(documents);
        return new AddDocumentsPayload(documents);
    }


    public UpdateDocumentsPayload updateDocuments(UpdateDocumentsInput updateDocumentsInput) {
        final Documents documents = documentsRepository.findById(updateDocumentsInput.getId()).get();

        if (updateDocumentsInput.getDocumentFilePath() != null){
            documents.setDocumentFilePath(updateDocumentsInput.getDocumentFilePath());
        }
        if(updateDocumentsInput.getDocumentName() != null){
            documents.setDocumentName(updateDocumentsInput.getDocumentName());
        }

        if (updateDocumentsInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateDocumentsInput.getStudentId()).get();
            documents.setStudent(student);
        }

        if (updateDocumentsInput.getEmployeeId() != null) {
            final Employee employee = employeeRepository.findById(updateDocumentsInput.getEmployeeId()).get();
            documents.setEmployee(employee);
        }
        if (updateDocumentsInput.getVehicleId() != null) {
            final Vehicle vehicle = vehicleRepository.findById(updateDocumentsInput.getVehicleId()).get();
            documents.setVehicle(vehicle);
        }

        if (updateDocumentsInput.getContractId() != null) {
            final Contract contract = contractRepository.findById(updateDocumentsInput.getContractId()).get();
            documents.setContract(contract);
        }

        documentsRepository.save(documents);

        return new UpdateDocumentsPayload(documents);
    }

    public RemoveDocumentsPayload removeDocuments(RemoveDocumentsInput removeDocumentsInput) {
        Documents documents = documentsRepository.findById(removeDocumentsInput.getDocumentsId()).get();
        documentsRepository.delete(documents);
        return new RemoveDocumentsPayload(Lists.newArrayList(documentsRepository.findAll()));
    }


    /*public AddSemesterPayload addSemester(AddSemesterInput addSemesterInput) {
        final Semester semester = new Semester();
        semester.setSem(addSemesterInput.getSem());
        semesterRepository.save(semester);

        return new AddSemesterPayload(semester);
    }

    public UpdateSemesterPayload updateSemester(UpdateSemesterInput updateSemesterInput) {
        Semester semester = semesterRepository.findById(updateSemesterInput.getId()).get();
        if (updateSemesterInput.getSem() != null) {
            semester.setSem(updateSemesterInput.getSem());
        }

        semesterRepository.save(semester);

        return new UpdateSemesterPayload(semester);
    }

    public RemoveSemesterPayload removeSemester(RemoveSemesterInput removeSemesterInput) {
        Semester semester = semesterRepository.findById(removeSemesterInput.getSemesterId()).get();
        semesterRepository.delete(semester);
        return new RemoveSemesterPayload(Lists.newArrayList(semesterRepository.findAll()));
    }
*/

//    public UpdateInstitutePayload updateInstitute(UpdateInstituteInput updateInstituteInput) {
//        Institute institute = instituteRepository.findById(updateInstituteInput.getInstituteId());
//        if (updateInstituteInput.getCode() != null) {
//            institute.setCode(updateInstituteInput.getCode());
//        }
//
//        if (updateInstituteInput.getName() != null) {
//            institute.setName(updateInstituteInput.getName());
//        }
//
//        if (updateInstituteInput.getYear() != null) {
//            institute.setYear(updateInstituteInput.getYear());
//        }
//
//        instituteRepository.save(institute);
//
//        return new UpdateInstitutePayload(institute);
//    }

//    public RemoveInstitutePayload removeInstitute(RemoveInstituteInput removeInstituteInput) {
//        Institute institute = instituteRepository.findById(removeInstituteInput.getInstituteId());
//        instituteRepository.delete(institute);
//
//        return new RemoveInstitutePayload(Lists.newArrayList(instituteRepository.findAll()));
//    }

    public AddCollegePayload addCollege(AddCollegeInput addCollegeInput) {
        final College college = new College();
        college.setShortName(addCollegeInput.getShortName());
        college.setLogoPath(addCollegeInput.getLogoPath());
        college.setBackgroundImagePath(addCollegeInput.getBackgroundImagePath());
        college.setInstructionInformation(addCollegeInput.getInstructionInformation());

        collegeRepository.save(college);

        return new AddCollegePayload(college);
    }

    public UpdateCollegePayload updateCollege(UpdateCollegeInput updateCollegeInput) {
        College college = collegeRepository.findById(updateCollegeInput.getId()).get();
        if (updateCollegeInput.getShortName() != null) {
            college.setShortName(updateCollegeInput.getShortName());
        }

        if (updateCollegeInput.getLogoPath() != null) {
            college.setLogoPath(updateCollegeInput.getLogoPath());
        }

        if (updateCollegeInput.getBackgroundImagePath() != null) {
            college.setBackgroundImagePath(updateCollegeInput.getBackgroundImagePath());
        }

        if (updateCollegeInput.getInstructionInformation() != null) {
            college.setInstructionInformation(updateCollegeInput.getInstructionInformation());
        }

        collegeRepository.save(college);

        return new UpdateCollegePayload(college);
    }

    public RemoveCollegePayload removeCollege(RemoveCollegeInput removeCollegeInput) {
        College college = collegeRepository.getOne(removeCollegeInput.getCollegeId());
        collegeRepository.delete(college);

        return new RemoveCollegePayload(Lists.newArrayList(collegeRepository.findAll()));
    }

    public AddContractPayload addContract(AddContractInput addContractInput) {
        final Contract contract = new Contract();
        contract.setVendorName(addContractInput.getVendorName());
        contract.setTypeOfOwnerShip(addContractInput.getTypeOfOwnerShip());
        contract.setDurationOfContract(addContractInput.getDurationOfContract());
        contract.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addContractInput.getStartDate()));
        contract.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addContractInput.getEndDate()));

        contractRepository.save(contract);

        return new AddContractPayload(contract);
    }

    public UpdateContractPayload updateContract(UpdateContractInput updateContractInput) {
        Contract contract = contractRepository.findById(updateContractInput.getId()).get();
        if (updateContractInput.getVendorName() != null) {
            contract.setVendorName(updateContractInput.getVendorName());
        }

        if (updateContractInput.getTypeOfOwnerShip() != null) {
            contract.setTypeOfOwnerShip(updateContractInput.getTypeOfOwnerShip());
        }

        if (updateContractInput.getDurationOfContract() != null) {
            contract.setDurationOfContract(updateContractInput.getDurationOfContract());
        }

        if (updateContractInput.getStartDate() != null) {
            contract.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateContractInput.getStartDate()));
        }

        if (updateContractInput.getEndDate() != null) {
            contract.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateContractInput.getEndDate()));
        }


        contractRepository.save(contract);

        return new UpdateContractPayload(contract);
    }

    public RemoveContractPayload removeContract(RemoveContractInput removeContractInput) {
        Contract contract = contractRepository.getOne(removeContractInput.getContractId());
        contractRepository.delete(contract);

        return new RemoveContractPayload(Lists.newArrayList(contractRepository.findAll()));
    }

    public AddEmployeePayload addEmployee(AddEmployeeInput addEmployeeInput) {
        final Employee employee = new Employee();
        employee.setEmployeeName(addEmployeeInput.getEmployeeName());
        employee.setDesignation(addEmployeeInput.getDesignation());
        employee.setJoiningDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getJoiningDate()));
        employee.setJobEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getJobEndDate()));
        employee.setResignationDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getResignationDate()));
        employee.setResignationAcceptanceDate(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getResignationAcceptanceDate()));
        employee.setAadharNo(addEmployeeInput.getAadharNo());
        employee.setPanNo(addEmployeeInput.getPanNo());
        employee.setPassportNo(addEmployeeInput.getPassportNo());
        employee.setPrimaryContactNo(addEmployeeInput.getPrimaryContactNo());
        employee.setSecondaryContactNo(addEmployeeInput.getSecondaryContactNo());
        employee.setEmployeeFatherName(addEmployeeInput.getEmployeeFatherName());
        employee.setEmployeeMotherName(addEmployeeInput.getEmployeeMotherName());
        employee.setPrimaryAddress(addEmployeeInput.getPrimaryAddress());
        employee.setSecondaryAddress(addEmployeeInput.getSecondaryAddress());
        employee.setEmployeeAddress(addEmployeeInput.getEmployeeAddress());
        employee.setPersonalMailId(addEmployeeInput.getPersonalMailId());
        employee.setOfficialMailId(addEmployeeInput.getOfficialMailId());
        employee.setDisability(addEmployeeInput.getDisability());
        employee.setDrivingLicenceNo(addEmployeeInput.getDrivingLicenceNo());
        employee.setDrivingLicenceValidity(DateFormatUtil.convertLocalDateFromUtilDate(addEmployeeInput.getDrivingLicenceValidity()));
        employee.setGender(addEmployeeInput.getGender());


        employeeRepository.save(employee);

        return new AddEmployeePayload(employee);

    }

    public UpdateEmployeePayload updateEmployee(UpdateEmployeeInput updateEmployeeInput) {
        Employee employee = employeeRepository.findById(updateEmployeeInput.getId()).get();
        if (updateEmployeeInput.getEmployeeName() != null) {
            employee.setEmployeeName(updateEmployeeInput.getEmployeeName());
        }

        if (updateEmployeeInput.getDesignation() != null) {
            employee.setDesignation(updateEmployeeInput.getDesignation());
        }

        if (updateEmployeeInput.getJoiningDate() != null) {
            employee.setJoiningDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getJoiningDate()));
        }

        if (updateEmployeeInput.getJobEndDate() != null) {
            employee.setJobEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getJobEndDate()));
        }

        if (updateEmployeeInput.getResignationDate() != null) {
            employee.setResignationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getResignationDate()));
        }

        if (updateEmployeeInput.getResignationAcceptanceDate() != null) {
            employee.setResignationAcceptanceDate(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getResignationAcceptanceDate()));
        }
        if (updateEmployeeInput.getAadharNo() != null) {
            employee.setAadharNo(updateEmployeeInput.getAadharNo());
        }
        if (updateEmployeeInput.getPanNo() != null) {
            employee.setPanNo(updateEmployeeInput.getPanNo());
        }
        if (updateEmployeeInput.getPassportNo() != null) {
            employee.setPassportNo(updateEmployeeInput.getPassportNo());
        }
        if (updateEmployeeInput.getPrimaryContactNo() != null) {
            employee.setPrimaryContactNo(updateEmployeeInput.getPrimaryContactNo());
        }
        if (updateEmployeeInput.getSecondaryContactNo() != null) {
            employee.setSecondaryContactNo(updateEmployeeInput.getSecondaryContactNo());
        }
        if (updateEmployeeInput.getEmployeeFatherName() != null) {
            employee.setEmployeeFatherName(updateEmployeeInput.getEmployeeFatherName());
        }
        if (updateEmployeeInput.getEmployeeMotherName() != null) {
            employee.setEmployeeMotherName(updateEmployeeInput.getEmployeeMotherName());
        }
        if (updateEmployeeInput.getPrimaryAddress() != null) {
            employee.setPrimaryAddress(updateEmployeeInput.getPrimaryAddress());
        }
        if (updateEmployeeInput.getSecondaryAddress() != null) {
            employee.setSecondaryAddress(updateEmployeeInput.getSecondaryAddress());
        }
        if (updateEmployeeInput.getEmployeeAddress() != null) {
            employee.setEmployeeAddress(updateEmployeeInput.getEmployeeAddress());
        }
        if (updateEmployeeInput.getPersonalMailId() != null) {
            employee.setPersonalMailId(updateEmployeeInput.getPersonalMailId());
        }
        if (updateEmployeeInput.getDisability() != null) {
            employee.setDisability(updateEmployeeInput.getDisability());
        }
        if (updateEmployeeInput.getDrivingLicenceNo() != null) {
            employee.setDrivingLicenceNo(updateEmployeeInput.getDrivingLicenceNo());
        }
        if (updateEmployeeInput.getDrivingLicenceValidity() != null) {
            employee.setDrivingLicenceValidity(DateFormatUtil.convertLocalDateFromUtilDate(updateEmployeeInput.getDrivingLicenceValidity()));
        }
        if (updateEmployeeInput.getGender() != null) {
            employee.setGender(updateEmployeeInput.getGender());
        }



        employeeRepository.save(employee);

        return new UpdateEmployeePayload(employee);
    }

    public RemoveEmployeePayload removeEmployee(RemoveEmployeeInput removeEmployeeInput) {
        Employee employee = employeeRepository.getOne(removeEmployeeInput.getEmployeeId());
        employeeRepository.delete(employee);

        return new RemoveEmployeePayload(Lists.newArrayList(employeeRepository.findAll()));
    }

    public AddBranchPayload addBranch(AddBranchInput addBranchInput) {
        College college = collegeRepository.findById(addBranchInput.getCollegeId()).get();
        City city = cityRepository.findById(addBranchInput.getCityId()).get();
        State state = stateRepository.findById(addBranchInput.getStateId()).get();
        final Branch branch = new Branch();
        branch.setBranchName(addBranchInput.getBranchName());
        branch.setAddress1(addBranchInput.getAddress1());
        branch.setAddress2(addBranchInput.getAddress2());
        branch.setBranchHead(addBranchInput.getBranchHead());
        branch.setCollege(college);
        branch.setCity(city);
        branch.setState(state);
        branchRepository.save(branch);

        return new AddBranchPayload(branch);
    }

    public UpdateBranchPayload updateBranch(UpdateBranchInput updateBranchInput) {
        Branch branch = branchRepository.findById(updateBranchInput.getId()).get();
        if (updateBranchInput.getBranchName() != null) {
            branch.setBranchName(updateBranchInput.getBranchName());
        }

        if (updateBranchInput.getAddress1() != null) {
            branch.setAddress1(updateBranchInput.getAddress1());
        }

        if (updateBranchInput.getAddress2() != null) {
            branch.setAddress2(updateBranchInput.getAddress2());
        }

        if (updateBranchInput.getBranchHead() != null) {
            branch.setBranchHead(updateBranchInput.getBranchHead());
        }
        if (updateBranchInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateBranchInput.getCollegeId()).get();
            branch.setCollege(college);
        }
        if (updateBranchInput.getStateId() != null) {
            State state = stateRepository.findById(updateBranchInput.getStateId()).get();
            branch.setState(state);
        }
        if (updateBranchInput.getCityId() != null) {
            City city = cityRepository.findById(updateBranchInput.getCityId()).get();
            branch.setCity(city);
        }
        branchRepository.save(branch);
        return new UpdateBranchPayload(branch);
    }

    public RemoveBranchPayload removeBranch(RemoveBranchInput removeBranchsInput) {
        Branch branch = branchRepository.findById(removeBranchsInput.getBranchId()).get();
        branchRepository.delete(branch);
        return new RemoveBranchPayload(Lists.newArrayList(branchRepository.findAll()));
    }

    public AddBatchPayload addBatch(AddBatchInput addBatchInput) {
        Department department = departmentRepository.findById(addBatchInput.getDepartmentId()).get();
        final Batch batch = new Batch();
        batch.setBatch(addBatchInput.getBatch());
        batch.setDepartment(department);
        batchRepository.save(batch);
        return new AddBatchPayload(batch);
    }

    public UpdateBatchPayload updateBatch(UpdateBatchInput updateBatchInput) {
        Batch batch = batchRepository.findById(updateBatchInput.getId()).get();
        if (updateBatchInput.getBatch() != null) {
            batch.setBatch(updateBatchInput.getBatch());
        }
        if (updateBatchInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateBatchInput.getDepartmentId()).get();
            batch.setDepartment(department);
        }
        batchRepository.save(batch);
        return new UpdateBatchPayload(batch);
    }

    public RemoveBatchPayload removeBatch(RemoveBatchInput removeBatchInput) {
        Batch batch = batchRepository.findById(removeBatchInput.getBatchId()).get();
        batchRepository.delete(batch);
        return new RemoveBatchPayload(Lists.newArrayList(batchRepository.findAll()));
    }

    public AddSectionPayload addSection(AddSectionInput addSectionInput) {
        final Batch batch = batchRepository.findById(addSectionInput.getBatchId()).get();
        final Section section = new Section();
        section.setSection(addSectionInput.getSection());
        section.setBatch(batch);
        sectionRepository.save(section);
        return new AddSectionPayload(section);
    }

    public UpdateSectionPayload updateSection(UpdateSectionInput updateSectionInput) {
        Section section = sectionRepository.findById(updateSectionInput.getId()).get();
        if (updateSectionInput.getSection() != null) {
            section.setSection(updateSectionInput.getSection());
        }
        if (updateSectionInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateSectionInput.getBatchId()).get();
            section.setBatch(batch);
        }
        sectionRepository.save(section);

        return new UpdateSectionPayload(section);
    }

    public RemoveSectionPayload removeSection(RemoveSectionInput removeSectionInput) {
        Section section = sectionRepository.findById(removeSectionInput.getSectionId()).get();
        sectionRepository.delete(section);
        return new RemoveSectionPayload(Lists.newArrayList(sectionRepository.findAll()));
    }

    public AddSubjectPayload addSubject(AddSubjectInput addSubjectInput) {
        final Department department = departmentRepository.findById(addSubjectInput.getDepartmentId()).get();
        final Batch batch = batchRepository.findById(addSubjectInput.getBatchId()).get();

        final Subject subject = new Subject();
        subject.setDepartment(department);
        subject.setBatch(batch);
        subject.setSubjectType(addSubjectInput.getSubjectType());
        subject.setSubjectCode(addSubjectInput.getSubjectCode());
        subject.setSubjectDesc(addSubjectInput.getSubjectDesc());
        subject.setStatus(addSubjectInput.getStatus());
        subjectRepository.save(subject);
        return new AddSubjectPayload(subject);
    }

    public UpdateSubjectPayload updateSubject(UpdateSubjectInput updateSubjectInput) {
        Subject subject = subjectRepository.findById(updateSubjectInput.getId()).get();
        if (updateSubjectInput.getSubjectCode() != null) {
            subject.setSubjectCode(updateSubjectInput.getSubjectCode());
        }
        if (updateSubjectInput.getSubjectType() != null) {
            subject.setSubjectType(updateSubjectInput.getSubjectType());
        }
        if (updateSubjectInput.getSubjectDesc() != null) {
            subject.setSubjectDesc(updateSubjectInput.getSubjectDesc());
        }
        if (updateSubjectInput.getStatus() != null) {
            subject.setStatus(updateSubjectInput.getStatus());
        }
        if (updateSubjectInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateSubjectInput.getDepartmentId()).get();
            subject.setDepartment(department);
        }
        if (updateSubjectInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateSubjectInput.getBatchId()).get();
            subject.setBatch(batch);
        }
        subjectRepository.save(subject);

        return new UpdateSubjectPayload(subject);
    }

    public RemoveSubjectPayload removeSubject(RemoveSubjectInput removeSubjectInput) {
        Subject subject = subjectRepository.findById(removeSubjectInput.getSubjectId()).get();
        subjectRepository.delete(subject);
        return new RemoveSubjectPayload(Lists.newArrayList(subjectRepository.findAll()));
    }

    public AddStudentAttendancePayload addStudentAttendance(AddStudentAttendanceInput addStudentAttendanceInput) {
        final Student student = studentRepository.findById(addStudentAttendanceInput.getStudentId()).get();
        final Lecture lecture = lectureRepository.findById(addStudentAttendanceInput.getLectureId()).get();
        final StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setAttendanceStatus(addStudentAttendanceInput.getAttendanceStatus());
        studentAttendance.setComments(addStudentAttendanceInput.getComments());
        studentAttendance.setStudent(student);
        studentAttendance.setLecture(lecture);
//        studentAttendance.setAttendanceDate(addStudentAttendanceInput.getAttendanceDate());
        studentAttendanceRepository.save(studentAttendance);
        return new AddStudentAttendancePayload(studentAttendance);
    }

    public UpdateStudentAttendancePayload updateStudentAttendance(UpdateStudentAttendanceInput updateStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(updateStudentAttendanceInput.getId()).get();


        if (updateStudentAttendanceInput.getAttendanceStatus() != null) {
            studentAttendance.setAttendanceStatus(updateStudentAttendanceInput.getAttendanceStatus());
        }
        if (updateStudentAttendanceInput.getComments() != null) {
            studentAttendance.setComments(updateStudentAttendanceInput.getComments());
        }
//        if (updateStudentAttendanceInput.getAttendanceDate() != null) {
//            studentAttendance.setAttendanceDate(updateStudentAttendanceInput.getAttendanceDate());
//        }
        if (updateStudentAttendanceInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateStudentAttendanceInput.getStudentId()).get();
            studentAttendance.setStudent(student);
        }
        if (updateStudentAttendanceInput.getLectureId() != null) {
            final Lecture lecture = lectureRepository.findById(updateStudentAttendanceInput.getLectureId()).get();
            studentAttendance.setLecture(lecture);
        }
        studentAttendanceRepository.save(studentAttendance);

        return new UpdateStudentAttendancePayload(studentAttendance);
    }

    public RemoveStudentAttendancePayload removeStudentAttendance(RemoveStudentAttendanceInput removeStudentAttendanceInput) {
        StudentAttendance studentAttendance = studentAttendanceRepository.findById(removeStudentAttendanceInput.getStudentAttendanceId()).get();
        studentAttendanceRepository.delete(studentAttendance);
        return new RemoveStudentAttendancePayload(Lists.newArrayList(studentAttendanceRepository.findAll()));
    }


    public AddTeacherPayload addTeacher(AddTeacherInput addTeacherInput) {
        final Branch branch = branchRepository.findById(addTeacherInput.getBranchId()).get();
        final Department department = departmentRepository.findById(addTeacherInput.getDepartmentId()).get();
        final Teacher teacher = new Teacher();
        teacher.setTeacherName(addTeacherInput.getTeacherName());
        teacher.setTeacherMiddleName(addTeacherInput.getTeacherMiddleName());
        teacher.setTeacherLastName(addTeacherInput.getTeacherLastName());
        teacher.setFatherName(addTeacherInput.getFatherName());
        teacher.setFatherMiddleName(addTeacherInput.getFatherMiddleName());
        teacher.setFatherLastName(addTeacherInput.getFatherLastName());
        teacher.setSpouseName(addTeacherInput.getSpouseName());
        teacher.setSpouseMiddleName(addTeacherInput.getSpouseMiddleName());
        teacher.setSpouseLastName(addTeacherInput.getSpouseLastName());
        teacher.setMotherName(addTeacherInput.getMotherName());
        teacher.setMotherMiddleName(addTeacherInput.getMotherMiddleName());
        teacher.setMotherLastName(addTeacherInput.getMotherLastName());
        teacher.setAadharNo(addTeacherInput.getAadharNo());
        teacher.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addTeacherInput.getDateOfBirth()));
        teacher.setPlaceOfBirth(addTeacherInput.getPlaceOfBirth());
        teacher.setReligion(addTeacherInput.getReligion());
        teacher.setCaste(addTeacherInput.getCaste());
        teacher.setSubCaste(addTeacherInput.getSubCaste());
        teacher.setAge(addTeacherInput.getAge());
        teacher.setSex(addTeacherInput.getSex());
        teacher.setBloodGroup(addTeacherInput.getBloodGroup());
        teacher.setAddressLineOne(addTeacherInput.getAddressLineOne());
        teacher.setAddressLineTwo(addTeacherInput.getAddressLineTwo());
        teacher.setAddressLineThree(addTeacherInput.getAddressLineThree());
        teacher.setTown(addTeacherInput.getTown());
        teacher.setState(addTeacherInput.getState());
        teacher.setCountry(addTeacherInput.getCountry());
        teacher.setPincode(addTeacherInput.getPincode());
        teacher.setTeacherContactNumber(addTeacherInput.getTeacherContactNumber());
        teacher.setAlternateContactNumber(addTeacherInput.getAlternateContactNumber());
        teacher.setTeacherEmailAddress(addTeacherInput.getTeacherEmailAddress());
        teacher.setAlternateEmailAddress(addTeacherInput.getAlternateEmailAddress());
        teacher.setRelationWithStaff(addTeacherInput.getRelationWithStaff());
        teacher.setEmergencyContactName(addTeacherInput.getEmergencyContactName());
        teacher.setEmergencyContactMiddleName(addTeacherInput.getEmergencyContactMiddleName());
        teacher.setEmergencyContactLastName(addTeacherInput.getEmergencyContactLastName());
        teacher.setEmergencyContactNo(addTeacherInput.getEmergencyContactNo());
        teacher.setEmergencyContactEmailAddress(addTeacherInput.getEmergencyContactEmailAddress());
        teacher.setUploadPhoto(addTeacherInput.getUploadPhoto());
        teacher.setEmployeeId(addTeacherInput.getEmployeeId());
        teacher.setDesignation(addTeacherInput.getDesignation());
        teacher.setStaffType(addTeacherInput.getStaffType());
        teacher.setStatus(addTeacherInput.getStatus());
        teacher.setBranch(branch);
        teacher.setDepartment(department);

        teacherRepository.save(teacher);

        return new AddTeacherPayload(teacher);
    }

    public UpdateTeacherPayload updateTeacher(UpdateTeacherInput updateTeacherInput) {
        Teacher teacher = teacherRepository.findById(updateTeacherInput.getId()).get();
        if (updateTeacherInput.getTeacherName() != null) {
            teacher.setTeacherName(updateTeacherInput.getTeacherName());
        }
        if (updateTeacherInput.getTeacherMiddleName() != null) {
            teacher.setTeacherMiddleName(updateTeacherInput.getTeacherMiddleName());
        }
        if (updateTeacherInput.getTeacherLastName() != null) {
            teacher.setTeacherLastName(updateTeacherInput.getTeacherLastName());
        }
        if (updateTeacherInput.getFatherName() != null) {
            teacher.setFatherName(updateTeacherInput.getFatherName());
        }
        if (updateTeacherInput.getFatherMiddleName() != null) {
            teacher.setFatherMiddleName(updateTeacherInput.getFatherMiddleName());
        }
        if (updateTeacherInput.getFatherLastName() != null) {
            teacher.setFatherLastName(updateTeacherInput.getFatherLastName());
        }

        if (updateTeacherInput.getSpouseName() != null) {
            teacher.setSpouseName(updateTeacherInput.getSpouseName());
        }
        if (updateTeacherInput.getSpouseMiddleName() != null) {
            teacher.setSpouseMiddleName(updateTeacherInput.getSpouseMiddleName());
        }
        if (updateTeacherInput.getSpouseLastName() != null) {
            teacher.setSpouseLastName(updateTeacherInput.getSpouseLastName());
        }

        if (updateTeacherInput.getMotherName() != null) {
            teacher.setMotherName(updateTeacherInput.getMotherName());
        }
        if (updateTeacherInput.getMotherMiddleName() != null) {
            teacher.setMotherMiddleName(updateTeacherInput.getMotherMiddleName());
        }
        if (updateTeacherInput.getMotherLastName() != null) {
            teacher.setMotherLastName(updateTeacherInput.getMotherLastName());
        }
        if (updateTeacherInput.getAadharNo() != null) {
            teacher.setAadharNo(updateTeacherInput.getAadharNo());
        }
        if (updateTeacherInput.getDateOfBirth() != null) {
            teacher.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateTeacherInput.getDateOfBirth()));
        }
        if (updateTeacherInput.getPlaceOfBirth() != null) {
            teacher.setPlaceOfBirth(updateTeacherInput.getPlaceOfBirth());
        }
        if (updateTeacherInput.getReligion() != null) {
            teacher.setReligion(updateTeacherInput.getReligion());
        }
        if (updateTeacherInput.getCaste() != null) {
            teacher.setCaste(updateTeacherInput.getCaste());
        }
        if (updateTeacherInput.getSubCaste() != null) {
            teacher.setSubCaste(updateTeacherInput.getSubCaste());
        }
        if (updateTeacherInput.getAge() != null) {
            teacher.setAge(updateTeacherInput.getAge());
        }
        if (updateTeacherInput.getSex() != null) {
            teacher.setSex(updateTeacherInput.getSex());
        }
        if (updateTeacherInput.getBloodGroup() != null) {
            teacher.setBloodGroup(updateTeacherInput.getBloodGroup());
        }
        if (updateTeacherInput.getAddressLineOne() != null) {
            teacher.setAddressLineOne(updateTeacherInput.getAddressLineOne());
        }
        if (updateTeacherInput.getAddressLineTwo() != null) {
            teacher.setAddressLineTwo(updateTeacherInput.getAddressLineTwo());
        }
        if (updateTeacherInput.getAddressLineThree() != null) {
            teacher.setAddressLineThree(updateTeacherInput.getAddressLineThree());
        }
        if (updateTeacherInput.getTown() != null) {
            teacher.setTown(updateTeacherInput.getTown());
        }
        if (updateTeacherInput.getState() != null) {
            teacher.setState(updateTeacherInput.getState());
        }
        if (updateTeacherInput.getCountry() != null) {
            teacher.setCountry(updateTeacherInput.getCountry());
        }
        if (updateTeacherInput.getPincode() != null) {
            teacher.setPincode(updateTeacherInput.getPincode());
        }
        if (updateTeacherInput.getTeacherContactNumber() != null) {
            teacher.setTeacherContactNumber(updateTeacherInput.getTeacherContactNumber());
        }
        if (updateTeacherInput.getAlternateContactNumber() != null) {
            teacher.setAlternateContactNumber(updateTeacherInput.getAlternateContactNumber());
        }
        if (updateTeacherInput.getTeacherEmailAddress() != null) {
            teacher.setTeacherEmailAddress(updateTeacherInput.getTeacherEmailAddress());
        }
        if (updateTeacherInput.getAlternateEmailAddress() != null) {
            teacher.setAlternateEmailAddress(updateTeacherInput.getAlternateEmailAddress());
        }
        if (updateTeacherInput.getRelationWithStaff() != null) {
            teacher.setRelationWithStaff(updateTeacherInput.getRelationWithStaff());
        }
        if (updateTeacherInput.getEmergencyContactName() != null) {
            teacher.setEmergencyContactName(updateTeacherInput.getEmergencyContactName());
        }
        if (updateTeacherInput.getEmergencyContactMiddleName() != null) {
            teacher.setEmergencyContactMiddleName(updateTeacherInput.getEmergencyContactMiddleName());
        }
        if (updateTeacherInput.getEmergencyContactLastName() != null) {
            teacher.setEmergencyContactLastName(updateTeacherInput.getEmergencyContactLastName());
        }
        if (updateTeacherInput.getEmergencyContactNo() != null) {
            teacher.setEmergencyContactNo(updateTeacherInput.getEmergencyContactNo());
        }
        if (updateTeacherInput.getEmergencyContactEmailAddress() != null) {
            teacher.setEmergencyContactEmailAddress(updateTeacherInput.getEmergencyContactEmailAddress());
        }
        if (updateTeacherInput.getUploadPhoto() != null) {
            teacher.setUploadPhoto(updateTeacherInput.getUploadPhoto());
        }
        if (updateTeacherInput.getEmployeeId() != null) {
            teacher.setEmployeeId(updateTeacherInput.getEmployeeId());
        }
        if (updateTeacherInput.getDesignation() != null) {
            teacher.setDesignation(updateTeacherInput.getDesignation());
        }
        if (updateTeacherInput.getStaffType() != null) {
            teacher.setStaffType(updateTeacherInput.getStaffType());
        }

        if (updateTeacherInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateTeacherInput.getBranchId()).get();
            teacher.setBranch(branch);
        }

        if (updateTeacherInput.getBranchId() != null) {
            final Department department = departmentRepository.findById(updateTeacherInput.getDepartmentId()).get();
            teacher.setDepartment(department);
        }
        teacherRepository.save(teacher);

        return new UpdateTeacherPayload(teacher);
    }

    public RemoveTeacherPayload removeTeacher(RemoveTeacherInput removeTeacherInput) {
        Teacher teacher = teacherRepository.findById(removeTeacherInput.getTeacherId()).get();
        teacherRepository.delete(teacher);
        return new RemoveTeacherPayload(Lists.newArrayList(teacherRepository.findAll()));
    }

    public AddAuthorizedSignatoryPayload addAuthorizedSignatory(AddAuthorizedSignatoryInput addAuthorizedSignatoryInput) {
        final Branch branch = branchRepository.findById(addAuthorizedSignatoryInput.getBranchId()).get();
        final College college = collegeRepository.findById(addAuthorizedSignatoryInput.getCollegeId()).get();
        final AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setSignatoryName(addAuthorizedSignatoryInput.getSignatoryName());
        authorizedSignatory.setSignatoryFatherName(addAuthorizedSignatoryInput.getSignatoryFatherName());
        authorizedSignatory.setSignatoryDesignation(addAuthorizedSignatoryInput.getSignatoryDesignation());
        authorizedSignatory.setAddress1(addAuthorizedSignatoryInput.getAddress1());
        authorizedSignatory.setAddress2(addAuthorizedSignatoryInput.getAddress2());
        authorizedSignatory.setAddress3(addAuthorizedSignatoryInput.getAddress3());
        authorizedSignatory.setAddress4(addAuthorizedSignatoryInput.getAddress4());
        authorizedSignatory.setAddress5(addAuthorizedSignatoryInput.getAddress5());
        authorizedSignatory.setEmail(addAuthorizedSignatoryInput.getEmail());
        authorizedSignatory.setPanCardNumber(addAuthorizedSignatoryInput.getPanCardNumber());
        authorizedSignatory.setBranch(branch);
        authorizedSignatory.setCollege(college);
        authorizedSignatoryRepository.save(authorizedSignatory);

        return new AddAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public UpdateAuthorizedSignatoryPayload updateAuthorizedSignatory(UpdateAuthorizedSignatoryInput updateAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(updateAuthorizedSignatoryInput.getId()).get();
        if (updateAuthorizedSignatoryInput.getSignatoryName() != null) {
            authorizedSignatory.setSignatoryName(updateAuthorizedSignatoryInput.getSignatoryName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryFatherName() != null) {
            authorizedSignatory.setSignatoryFatherName(updateAuthorizedSignatoryInput.getSignatoryFatherName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryDesignation() != null) {
            authorizedSignatory.setSignatoryDesignation(updateAuthorizedSignatoryInput.getSignatoryDesignation());
        }

        if (updateAuthorizedSignatoryInput.getAddress1() != null) {
            authorizedSignatory.setAddress1(updateAuthorizedSignatoryInput.getAddress1());
        }

        if (updateAuthorizedSignatoryInput.getAddress2() != null) {
            authorizedSignatory.setAddress2(updateAuthorizedSignatoryInput.getAddress2());
        }
        if (updateAuthorizedSignatoryInput.getAddress3() != null) {
            authorizedSignatory.setAddress3(updateAuthorizedSignatoryInput.getAddress3());
        }
        if (updateAuthorizedSignatoryInput.getAddress4() != null) {
            authorizedSignatory.setAddress4(updateAuthorizedSignatoryInput.getAddress4());
        }
        if (updateAuthorizedSignatoryInput.getAddress5() != null) {
            authorizedSignatory.setAddress5(updateAuthorizedSignatoryInput.getAddress5());
        }

        if (updateAuthorizedSignatoryInput.getEmail() != null) {
            authorizedSignatory.setEmail(updateAuthorizedSignatoryInput.getEmail());
        }

        if (updateAuthorizedSignatoryInput.getPanCardNumber() != null) {
            authorizedSignatory.setPanCardNumber(updateAuthorizedSignatoryInput.getPanCardNumber());
        }
        if (updateAuthorizedSignatoryInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateAuthorizedSignatoryInput.getBranchId()).get();
            authorizedSignatory.setBranch(branch);
        }
        if (updateAuthorizedSignatoryInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateAuthorizedSignatoryInput.getCollegeId()).get();
            authorizedSignatory.setCollege(college);
        }

        authorizedSignatoryRepository.save(authorizedSignatory);

        return new UpdateAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public RemoveAuthorizedSignatoryPayload removeAuthorizedSignatory(RemoveAuthorizedSignatoryInput removeAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(removeAuthorizedSignatoryInput.getAuthorizedSignatoryId()).get();
        authorizedSignatoryRepository.delete(authorizedSignatory);
        return new RemoveAuthorizedSignatoryPayload(Lists.newArrayList(authorizedSignatoryRepository.findAll()));
    }

    public AddLegalEntityPayload addLegalEntity(AddLegalEntityInput addLegalEntityInput) {
        final LegalEntity legalEntity = new LegalEntity();
        legalEntity.setLogoFileName(addLegalEntityInput.getLogoFileName());
        legalEntity.setLogoPath(addLegalEntityInput.getLogoPath());
        legalEntity.setLegalNameOfTheCollege(addLegalEntityInput.getLegalNameOfTheCollege());
        legalEntity.setTypeOfCollege(addLegalEntityInput.getTypeOfCollege());
        legalEntity.setRegisteredOfficeAddress1(addLegalEntityInput.getRegisteredOfficeAddress1());
        legalEntity.setRegisteredOfficeAddress2(addLegalEntityInput.getRegisteredOfficeAddress2());
        legalEntity.setRegisteredOfficeAddress3(addLegalEntityInput.getRegisteredOfficeAddress3());
        legalEntity.setRegisteredOfficeAddress4(addLegalEntityInput.getRegisteredOfficeAddress4());
        legalEntity.setRegisteredOfficeAddress5(addLegalEntityInput.getRegisteredOfficeAddress5());
        legalEntity.setDateOfIncorporation(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getDateOfIncorporation()));
        legalEntity.setCollegeIdentificationNumber(addLegalEntityInput.getCollegeIdentificationNumber());
        legalEntity.setPan(addLegalEntityInput.getPan());
        legalEntity.setTan(addLegalEntityInput.getTan());
        legalEntity.setTanCircleNumber(addLegalEntityInput.getTanCircleNumber());
        legalEntity.setCitTdsLocation(addLegalEntityInput.getCitTdsLocation());
        legalEntity.setFormSignatory(addLegalEntityInput.getFormSignatory());
        legalEntity.setPfNumber(addLegalEntityInput.getPfNumber());
        legalEntity.setPfRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getPfRegistrationDate()));
        legalEntity.setPfSignatory(addLegalEntityInput.getPfSignatory());
        legalEntity.setEsiNumber(addLegalEntityInput.getEsiNumber());
        legalEntity.setEsiRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getEsiRegistrationDate()));
        legalEntity.setEsiSignatory(addLegalEntityInput.getEsiSignatory());
        legalEntity.setPtNumber(addLegalEntityInput.getPtNumber());
        legalEntity.setPtRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(addLegalEntityInput.getPtRegistrationDate()));
        legalEntity.setPtSignatory(addLegalEntityInput.getPtSignatory());
        Branch branch = branchRepository.findById(addLegalEntityInput.getBranchId()).get();
        College college = collegeRepository.findById(addLegalEntityInput.getCollegeId()).get();
        City city = cityRepository.findById(addLegalEntityInput.getCityId()).get();
        State state = stateRepository.findById(addLegalEntityInput.getStateId()).get();
        legalEntity.setBranch(branch);
        legalEntity.setCollege(college);
        legalEntity.setCity(city);
        legalEntity.setState(state);
        legalEntityRepository.save(legalEntity);

        return new AddLegalEntityPayload(legalEntity);
    }

    public UpdateLegalEntityPayload updateLegalEntity(UpdateLegalEntityInput updateLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(updateLegalEntityInput.getId()).get();
        if (updateLegalEntityInput.getLogoFileName() != null) {
            legalEntity.setLogoFileName(updateLegalEntityInput.getLogoFileName());
        }
        if (updateLegalEntityInput.getLogoPath() != null) {
            legalEntity.setLogoPath(updateLegalEntityInput.getLogoPath());
        }
        if (updateLegalEntityInput.getLegalNameOfTheCollege() != null) {
            legalEntity.setLegalNameOfTheCollege(updateLegalEntityInput.getLegalNameOfTheCollege());
        }
        if (updateLegalEntityInput.getTypeOfCollege() != null) {
            legalEntity.setTypeOfCollege(updateLegalEntityInput.getTypeOfCollege());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress1() != null) {
            legalEntity.setRegisteredOfficeAddress1(updateLegalEntityInput.getRegisteredOfficeAddress1());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress2() != null) {
            legalEntity.setRegisteredOfficeAddress2(updateLegalEntityInput.getRegisteredOfficeAddress2());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress3() != null) {
            legalEntity.setRegisteredOfficeAddress3(updateLegalEntityInput.getRegisteredOfficeAddress3());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress4() != null) {
            legalEntity.setRegisteredOfficeAddress4(updateLegalEntityInput.getRegisteredOfficeAddress4());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress5() != null) {
            legalEntity.setRegisteredOfficeAddress5(updateLegalEntityInput.getRegisteredOfficeAddress5());
        }
        if (updateLegalEntityInput.getDateOfIncorporation() != null) {
            legalEntity.setDateOfIncorporation(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getDateOfIncorporation()));
        }

        if (updateLegalEntityInput.getCollegeIdentificationNumber() != null) {
            legalEntity.setCollegeIdentificationNumber(updateLegalEntityInput.getCollegeIdentificationNumber());
        }
        if (updateLegalEntityInput.getPan() != null) {
            legalEntity.setPan(updateLegalEntityInput.getPan());
        }
        if (updateLegalEntityInput.getTan() != null) {
            legalEntity.setTan(updateLegalEntityInput.getTan());
        }
        if (updateLegalEntityInput.getTanCircleNumber() != null) {
            legalEntity.setTanCircleNumber(updateLegalEntityInput.getTanCircleNumber());
        }
        if (updateLegalEntityInput.getCitTdsLocation() != null) {
            legalEntity.setCitTdsLocation(updateLegalEntityInput.getCitTdsLocation());
        }
        if (updateLegalEntityInput.getFormSignatory() != null) {
            legalEntity.setFormSignatory(updateLegalEntityInput.getFormSignatory());
        }
        if (updateLegalEntityInput.getPfNumber() != null) {
            legalEntity.setPfNumber(updateLegalEntityInput.getPfNumber());
        }
        if (updateLegalEntityInput.getPfRegistrationDate() != null) {
            legalEntity.setPfRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getPfRegistrationDate()));
        }
        if (updateLegalEntityInput.getPfSignatory() != null) {
            legalEntity.setPfSignatory(updateLegalEntityInput.getPfSignatory());
        }

        if (updateLegalEntityInput.getEsiNumber() != null) {
            legalEntity.setEsiNumber(updateLegalEntityInput.getEsiNumber());
        }
        if (updateLegalEntityInput.getEsiRegistrationDate() != null) {
            legalEntity.setEsiRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getEsiRegistrationDate()));
        }
        if (updateLegalEntityInput.getEsiSignatory() != null) {
            legalEntity.setEsiSignatory(updateLegalEntityInput.getEsiSignatory());
        }

        if (updateLegalEntityInput.getPtRegistrationDate() != null) {
            legalEntity.setPtRegistrationDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLegalEntityInput.getPtRegistrationDate()));
        }
        if (updateLegalEntityInput.getPtSignatory() != null) {
            legalEntity.setPtSignatory(updateLegalEntityInput.getPtSignatory());
        }
        if (updateLegalEntityInput.getPtNumber() != null) {
            legalEntity.setPtNumber(updateLegalEntityInput.getPtNumber());
        }

        if (updateLegalEntityInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateLegalEntityInput.getBranchId()).get();
            legalEntity.setBranch(branch);
        }
        if (updateLegalEntityInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateLegalEntityInput.getCollegeId()).get();
            legalEntity.setCollege(college);
        }
        if (updateLegalEntityInput.getCityId() != null) {
            City city = cityRepository.findById(updateLegalEntityInput.getCityId()).get();
            legalEntity.setCity(city);
        }

        if (updateLegalEntityInput.getStateId() != null) {
            State state = stateRepository.findById(updateLegalEntityInput.getStateId()).get();
            legalEntity.setState(state);
        }
        legalEntityRepository.save(legalEntity);

        return new UpdateLegalEntityPayload(legalEntity);
    }


    public AddStudentExamReportPayload addStudentExamReport(AddStudentExamReportInput addStudentExamReportInput) {
        final StudentExamReport studentExamReport = new StudentExamReport();

        TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(addStudentExamReportInput.getTypeOfGradingId()).get();
        AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(addStudentExamReportInput.getAcademicExamSettingId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addStudentExamReportInput.getAcademicyearId()).get();
        Student student = studentRepository.findById(addStudentExamReportInput.getStudentId()).get();
        Batch batch = batchRepository.findById(addStudentExamReportInput.getBatchId()).get();
        Department department = departmentRepository.findById(addStudentExamReportInput.getDepartmentId()).get();
        Subject subject = subjectRepository.findById(addStudentExamReportInput.getSubjectId()).get();
        Section section =sectionRepository.findById(addStudentExamReportInput.getSectionId()).get();
        studentExamReport.setTypeOfGrading(typeOfGrading);
        studentExamReport.setAcademicyear(academicYear);
        studentExamReport.setAcademicExamSetting(academicExamSetting);
        studentExamReport.setBatch(batch);
        studentExamReport.setStudent(student);
        studentExamReport.setDepartment(department);
        studentExamReport.setSection(section);
        studentExamReport.setSubject(subject);
        studentExamReport.setMarksObtained(addStudentExamReportInput.getMarksObtained());
        studentExamReport.setComments(addStudentExamReportInput.getComments());
        studentExamReport.setCreatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addStudentExamReportInput.getCreatedOn()));
        studentExamReport.setCreatedBy(addStudentExamReportInput.getCreatedBy());
        studentExamReport.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addStudentExamReportInput.getUpdatedOn()));
        studentExamReport.setUpdatedBy(addStudentExamReportInput.getUpdatedBy());

        studentExamReportRepository.save(studentExamReport);
        return new AddStudentExamReportPayload(studentExamReport);
    }

    public UpdateStudentExamReportPayload updateStudentExamReport(UpdateStudentExamReportInput updateStudentExamReportInput) {
        StudentExamReport studentExamReport = studentExamReportRepository.findById(updateStudentExamReportInput.getId()).get();


        if (updateStudentExamReportInput.getMarksObtained() != null) {
            studentExamReport.setMarksObtained(updateStudentExamReportInput.getMarksObtained());
        }
        if (updateStudentExamReportInput.getComments() != null) {
            studentExamReport.setComments(updateStudentExamReportInput.getComments());
        }
        if (updateStudentExamReportInput.getCreatedOn() != null) {
            studentExamReport.setCreatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateStudentExamReportInput.getCreatedOn()));
        }
        if (updateStudentExamReportInput.getCreatedBy() != null) {
            studentExamReport.setCreatedBy(updateStudentExamReportInput.getCreatedBy());
        }
        if (updateStudentExamReportInput.getUpdatedOn() != null) {
            studentExamReport.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateStudentExamReportInput.getUpdatedOn()));
        }
        if (updateStudentExamReportInput.getUpdatedBy() != null) {
            studentExamReport.setUpdatedBy(updateStudentExamReportInput.getUpdatedBy());
        }

        if (updateStudentExamReportInput.getAcademicExamSettingId() != null) {
            final AcademicExamSetting academicExamSetting = academicExamSettingRepository.findById(updateStudentExamReportInput.getAcademicExamSettingId()).get();
            studentExamReport.setAcademicExamSetting(academicExamSetting);
        }
        if (updateStudentExamReportInput.getAcademicyearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateStudentExamReportInput.getAcademicyearId()).get();
            studentExamReport.setAcademicyear(academicYear);
        }
        if (updateStudentExamReportInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateStudentExamReportInput.getStudentId()).get();
            studentExamReport.setStudent(student);
        }
        if (updateStudentExamReportInput.getBatchId() != null) {
            final Batch batch = batchRepository.findById(updateStudentExamReportInput.getBatchId()).get();
            studentExamReport.setBatch(batch);
        }
        if (updateStudentExamReportInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateStudentExamReportInput.getDepartmentId()).get();
            studentExamReport.setDepartment(department);
        }
        if (updateStudentExamReportInput.getTypeOfGradingId() != null) {
            final TypeOfGrading typeOfGrading = typeOfGradingRepository.findById(updateStudentExamReportInput.getTypeOfGradingId()).get();
            studentExamReport.setTypeOfGrading(typeOfGrading);
        }
        if (updateStudentExamReportInput.getSectionId() != null) {
            final Section section = sectionRepository.findById(updateStudentExamReportInput.getSectionId()).get();
            studentExamReport.setSection(section);
        }
        if (updateStudentExamReportInput.getSubjectId() != null) {
            final Subject subject = subjectRepository.findById(updateStudentExamReportInput.getSubjectId()).get();
            studentExamReport.setSubject(subject);
        }
        studentExamReportRepository.save(studentExamReport);

        return new UpdateStudentExamReportPayload(studentExamReport);
    }

    public RemoveStudentExamReportPayload removeStudentExamReport(RemoveStudentExamReportInput removeStudentExamReportsInput) {
        StudentExamReport studentExamReport = studentExamReportRepository.findById(removeStudentExamReportsInput.getStudentExamReportId()).get();
        studentExamReportRepository.delete(studentExamReport);
        return new RemoveStudentExamReportPayload(Lists.newArrayList(studentExamReportRepository.findAll()));
    }

    public AddAcademicYearPayload addAcademicYear(AddAcademicYearInput addAcademicYearInput) {
        final AcademicYear academicYear = new AcademicYear();
        academicYear.setYear(addAcademicYearInput.getYear());
        academicYear.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addAcademicYearInput.getStartDate()));
        academicYear.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addAcademicYearInput.getEndDate()));
        academicYear.setStatus(addAcademicYearInput.getStatus());
        academicYearRepository.save(academicYear);
        return new AddAcademicYearPayload(academicYear);
    }

    public AddAdminAttendancePayLoad addAdminAttendance(AddAdminAttendanceInput addAdminAttendanceInput) {
        final AdminAttendance adminAttendance = new AdminAttendance();
        adminAttendance.setUpdatedBy(addAdminAttendanceInput.getUpdatedBy());
        adminAttendance.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addAdminAttendanceInput.getUpdatedOn()));
        Branch branch = branchRepository.findById(addAdminAttendanceInput.getBranchId()).get();
        final College college = collegeRepository.findById(addAdminAttendanceInput.getCollegeId()).get();
        final Lecture lecture = lectureRepository.findById(addAdminAttendanceInput.getLectureId()).get();
        final Department department = departmentRepository.findById(addAdminAttendanceInput.getDepartmentId()).get();
        final AcademicYear academicYear = academicYearRepository.findById(addAdminAttendanceInput.getAcademicyearId()).get();
        final Section section = sectionRepository.findById(addAdminAttendanceInput.getSectionId()).get();
        final Student student = studentRepository.findById(addAdminAttendanceInput.getStudentId()).get();
        adminAttendance.setBranch(branch);
        adminAttendance.setCollege(college);
        adminAttendance.setLecture(lecture);
        adminAttendance.setDepartment(department);
        adminAttendance.setAcademicyear(academicYear);
        adminAttendance.setSection(section);
        adminAttendance.setStudent(student);
        adminAttendanceRepository.save(adminAttendance);
        return new AddAdminAttendancePayLoad(adminAttendance);
    }

    public UpdateAdminAttendancePayLoad updateAdminAttendance(UpdateAdminAttendanceInput updateAdminAttendanceInput) {
        AdminAttendance adminAttendance = adminAttendanceRepository.findById(updateAdminAttendanceInput.getId()).get();
        if (updateAdminAttendanceInput.getUpdatedBy() != null) {
            adminAttendance.setUpdatedBy(updateAdminAttendanceInput.getUpdatedBy());
        }
        if (updateAdminAttendanceInput.getUpdatedOn() != null) {
            adminAttendance.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateAdminAttendanceInput.getUpdatedOn()));
        }
        if (updateAdminAttendanceInput.getSectionId() != null) {
            final Section section = sectionRepository.findById(updateAdminAttendanceInput.getSectionId()).get();
            adminAttendance.setSection(section);
        }
        if (updateAdminAttendanceInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateAdminAttendanceInput.getBranchId()).get();
            adminAttendance.setBranch(branch);
        }
        if (updateAdminAttendanceInput.getDepartmentId() != null) {
            final Department department = departmentRepository.findById(updateAdminAttendanceInput.getDepartmentId()).get();
            adminAttendance.setDepartment(department);
        }
        if (updateAdminAttendanceInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateAdminAttendanceInput.getAcademicyearId()).get();
            adminAttendance.setAcademicyear(academicYear);
        }
        if (updateAdminAttendanceInput.getLectureId() != null) {
            final Lecture lecture = lectureRepository.findById(updateAdminAttendanceInput.getLectureId()).get();
            adminAttendance.setLecture(lecture);
        }
        if (updateAdminAttendanceInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateAdminAttendanceInput.getStudentId()).get();
            adminAttendance.setStudent(student);
        }
        if (updateAdminAttendanceInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateAdminAttendanceInput.getCollegeId()).get();
            adminAttendance.setCollege(college);
        }
        adminAttendanceRepository.save(adminAttendance);

        return new UpdateAdminAttendancePayLoad(adminAttendance);
    }

    public RemoveAdminAttendancePayLoad removeAdminAttendance(RemoveAdminAttendanceInput removeAdminAttendanceInput) {
        AdminAttendance adminAttendance = adminAttendanceRepository.findById(removeAdminAttendanceInput.getAdminAttendanceId()).get();
        adminAttendanceRepository.delete(adminAttendance);
        return new RemoveAdminAttendancePayLoad(Lists.newArrayList(adminAttendanceRepository.findAll()));
    }

    public RemoveLegalEntityPayload removeLegalEntity(RemoveLegalEntityInput removeLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(removeLegalEntityInput.getLegalEntityId()).get();
        legalEntityRepository.delete(legalEntity);
        return new RemoveLegalEntityPayload(Lists.newArrayList(legalEntityRepository.findAll()));
    }

    public UpdateAcademicYearPayload updateAcademicYear(UpdateAcademicYearInput updateAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(updateAcademicYearInput.getId()).get();
        if (updateAcademicYearInput.getYear() != null) {
            academicYear.setYear(updateAcademicYearInput.getYear());
        }
        if (updateAcademicYearInput.getStartDate() != null) {
            academicYear.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicYearInput.getStartDate()));
        }
        if (updateAcademicYearInput.getEndDate() != null) {
            academicYear.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateAcademicYearInput.getEndDate()));
        }

        if (updateAcademicYearInput.getStatus() != null){
            academicYear.setStatus(updateAcademicYearInput.getStatus());
        }

        academicYearRepository.save(academicYear);

        return new UpdateAcademicYearPayload(academicYear);
    }

    public RemoveAcademicYearPayload removeAcademicYear(RemoveAcademicYearInput removeAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(removeAcademicYearInput.getAcademicYearId()).get();
        academicYearRepository.delete(academicYear);
        return new RemoveAcademicYearPayload(Lists.newArrayList(academicYearRepository.findAll()));
    }

    public AddHolidayPayload addHoliday(AddHolidayInput addHolidayInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addHolidayInput.getAcademicYearId()).get();
        final Holiday holiday = new Holiday();
        holiday.setHolidayDesc(addHolidayInput.getHolidayDesc());
        holiday.setHolidayDate(DateFormatUtil.convertLocalDateFromUtilDate(addHolidayInput.getHolidayDate()));
        holiday.setHolidayStatus(addHolidayInput.getHolidayStatus());
        holiday.setAcademicyear(academicYear);
        holidayRepository.save(holiday);

        return new AddHolidayPayload(holiday);
    }
    public UpdateHolidayPayload updateHoliday(UpdateHolidayInput updateHolidayInput) {
        Holiday holiday = holidayRepository.findById(updateHolidayInput.getId()).get();
        if (updateHolidayInput.getHolidayDesc() != null) {
            holiday.setHolidayDesc(updateHolidayInput.getHolidayDesc());
        }
        if (updateHolidayInput.getHolidayDate() != null) {
            holiday.setHolidayDate(DateFormatUtil.convertLocalDateFromUtilDate(updateHolidayInput.getHolidayDate()));
        }

        if (updateHolidayInput.getHolidayStatus() != null) {
            holiday.setHolidayStatus(updateHolidayInput.getHolidayStatus());
        }
        if (updateHolidayInput.getAcademicYearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateHolidayInput.getAcademicYearId()).get();
            holiday.setAcademicyear(academicYear);
        }
        holidayRepository.save(holiday);

        return new UpdateHolidayPayload(holiday);
    }

    public RemoveHolidayPayload removeHoliday(RemoveHolidayInput removeHolidayInput) {
        Holiday holiday = holidayRepository.findById(removeHolidayInput.getHolidayId()).get();
        holidayRepository.delete(holiday);
        return new RemoveHolidayPayload(Lists.newArrayList(holidayRepository.findAll()));
    }

    public AddTermPayload addTerm(AddTermInput addTermInput) {
        final AcademicYear academicYear = academicYearRepository.findById(addTermInput.getAcademicYearId()).get();
        final Term term = new Term();
        term.setTermsDesc(addTermInput.getTermsDesc());
        term.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addTermInput.getStartDate()));
        term.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addTermInput.getEndDate()));
        term.setTermStatus(addTermInput.getTermStatus());
        term.setAcademicyear(academicYear);
        termRepository.save(term);

        return new AddTermPayload(term);
    }

    public UpdateTermPayload updateTerm(UpdateTermInput updateTermInput) {
        Term term = termRepository.findById(updateTermInput.getId()).get();

        if (updateTermInput.getTermsDesc() != null) {
            term.setTermsDesc(updateTermInput.getTermsDesc());
        }
        if (updateTermInput.getStartDate() != null) {
            term.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateTermInput.getStartDate()));
        }
        if (updateTermInput.getEndDate() != null) {
            term.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateTermInput.getEndDate()));
        }
        if (updateTermInput.getTermStatus() != null) {
            term.setTermStatus(updateTermInput.getTermStatus());
        }
        if (updateTermInput.getAcademicYearId() != null) {
            final AcademicYear academicYear = academicYearRepository.findById(updateTermInput.getAcademicYearId()).get();
            term.setAcademicyear(academicYear);
        }
        termRepository.save(term);

        return new UpdateTermPayload(term);
    }

    public RemoveTermPayload removeTerm(RemoveTermInput removeTermInput) {
        Term term = termRepository.findById(removeTermInput.getTermId()).get();
        termRepository.delete(term);
        return new RemoveTermPayload(Lists.newArrayList(termRepository.findAll()));
    }

    public AddTeachPayload addTeach(AddTeachInput addTeachInput) {
        final Teacher teacher = teacherRepository.findById(addTeachInput.getTeacherId()).get();
        final Subject subject = subjectRepository.findById(addTeachInput.getSubjectId()).get();
        final Teach teach = new Teach();
        teach.setDesc(addTeachInput.getDesc());
        teach.setTeacher(teacher);
        teach.setSubject(subject);
        teachRepository.save(teach);
        return new AddTeachPayload(teach);
    }

    public UpdateTeachPayload updateTeach(UpdateTeachInput updateTeachInput) {
        Teach teach = teachRepository.findById(updateTeachInput.getId()).get();

        if (updateTeachInput.getDesc() != null) {
            teach.setDesc(updateTeachInput.getDesc());
        }
        if (updateTeachInput.getTeacherId() != null) {
            final Teacher teacher = teacherRepository.findById(updateTeachInput.getTeacherId()).get();
            teach.setTeacher(teacher);
        }
        if (updateTeachInput.getSubjectId() != null) {
            final Subject subject = subjectRepository.findById(updateTeachInput.getSubjectId()).get();
            teach.setSubject(subject);
        }
        teachRepository.save(teach);
        return new UpdateTeachPayload(teach);
    }

    public RemoveTeachPayload removeTeach(RemoveTeachInput removeTeachInput) {
        Teach teach = teachRepository.findById(removeTeachInput.getTeachId()).get();
        teachRepository.delete(teach);
        return new RemoveTeachPayload(Lists.newArrayList(teachRepository.findAll()));
    }

    public AddAttendanceMasterPayload addAttendanceMaster(AddAttendanceMasterInput addAttendanceMasterInput) {
        final Teach teach = teachRepository.findById(addAttendanceMasterInput.getTeachId()).get();
        final Section section = sectionRepository.findById(addAttendanceMasterInput.getSectionId()).get();
        final Batch batch = batchRepository.findById(addAttendanceMasterInput.getBatchId()).get();
        final AttendanceMaster attendanceMaster = new AttendanceMaster();

        attendanceMaster.setDesc(addAttendanceMasterInput.getDesc());
        attendanceMaster.setTeach(teach);
        attendanceMaster.setSection(section);
        attendanceMaster.setBatch(batch);
        attendanceMasterRepository.save(attendanceMaster);
        return new AddAttendanceMasterPayload(attendanceMaster);
    }

    public UpdateAttendanceMasterPayload updateAttendanceMaster(UpdateAttendanceMasterInput updateAttendanceMasterInput) {
        AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateAttendanceMasterInput.getId()).get();

        if (updateAttendanceMasterInput.getDesc() != null) {
            attendanceMaster.setDesc(updateAttendanceMasterInput.getDesc());
        }
        if (updateAttendanceMasterInput.getTeachId() != null) {
            Teach teach = teachRepository.findById(updateAttendanceMasterInput.getTeachId()).get();
            attendanceMaster.setTeach(teach);
        }
        if (updateAttendanceMasterInput.getSectionId() != null) {
            Section section = sectionRepository.findById(updateAttendanceMasterInput.getSectionId()).get();
            attendanceMaster.setSection(section);
        }
        if (updateAttendanceMasterInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateAttendanceMasterInput.getBatchId()).get();
            attendanceMaster.setBatch(batch);
        }
        attendanceMasterRepository.save(attendanceMaster);
        return new UpdateAttendanceMasterPayload(attendanceMaster);
    }

    public RemoveAttendanceMasterPayload removeAttendanceMaster(RemoveAttendanceMasterInput removeAttendanceMasterInput) {
        AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(removeAttendanceMasterInput.getAttendanceMasterId()).get();
        attendanceMasterRepository.delete(attendanceMaster);
        return new RemoveAttendanceMasterPayload(Lists.newArrayList(attendanceMasterRepository.findAll()));
    }


    public AddLecturePayload addLecture(AddLectureInput addLectureInput) {
        final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(addLectureInput.getAttendanceMasterId()).get();
        final Lecture lecture = new Lecture();
        lecture.setLecDate(DateFormatUtil.convertLocalDateFromUtilDate(addLectureInput.getLecDate()));
        lecture.setLastUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addLectureInput.getLastUpdatedOn()));
        lecture.setLastUpdatedBy(addLectureInput.getLastUpdatedBy());
        lecture.setStartTime(addLectureInput.getStartTime());
        lecture.setEndTime(addLectureInput.getEndTime());
        lecture.setAttendancemaster(attendanceMaster);
        lectureRepository.save(lecture);
        return new AddLecturePayload(lecture);
    }

    public UpdateLecturePayload updateLecture(UpdateLectureInput updateLectureInput) {
        Lecture lecture = lectureRepository.findById(updateLectureInput.getId()).get();

        if (updateLectureInput.getLecDate() != null) {
            lecture.setLecDate(DateFormatUtil.convertLocalDateFromUtilDate(updateLectureInput.getLecDate()));
        }
        if (updateLectureInput.getLastUpdatedBy() != null) {
            lecture.setLastUpdatedBy(updateLectureInput.getLastUpdatedBy());
        }
        if (updateLectureInput.getLastUpdatedOn() != null) {
            lecture.setLastUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateLectureInput.getLastUpdatedOn()));
        }
        if (updateLectureInput.getStartTime() != null) {
            lecture.setStartTime(updateLectureInput.getStartTime());
        }
        if (updateLectureInput.getEndTime() != null) {
            lecture.setEndTime(updateLectureInput.getEndTime());
        }
        if (updateLectureInput.getAttendanceMasterId() != null) {
            final AttendanceMaster attendanceMaster = attendanceMasterRepository.findById(updateLectureInput.getAttendanceMasterId()).get();
            lecture.setAttendancemaster(attendanceMaster);
        }
        lectureRepository.save(lecture);
        return new UpdateLecturePayload(lecture);
    }

    public RemoveLecturePayload removeLecture(RemoveLectureInput removeLectureInput) {
        Lecture lecture = lectureRepository.findById(removeLectureInput.getLectureId()).get();
        lectureRepository.delete(lecture);
        return new RemoveLecturePayload(Lists.newArrayList(lectureRepository.findAll()));
    }

    public List<CmsFeeCategory> addFeeCategory(AddFeeCategoryInput addFeeCategoryInput) throws Exception {
        FeeCategory fc = CommonUtil.createCopyProperties(addFeeCategoryInput, FeeCategory.class);
        fc.setCreatedOn(LocalDate.now());
        fc.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addFeeCategoryInput.getStartDate()));
        fc.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(addFeeCategoryInput.getEndDate()));
        Branch branch = new Branch();
        branch.setId(addFeeCategoryInput.getBranchId());
        fc.setBranch(branch);
        fc = feeCategoryRepository.save(fc);

        FeeCategory f = new FeeCategory();
        f.setBranch(branch);
        Example<FeeCategory> example = Example.of(f);
        List<FeeCategory> list = this.feeCategoryRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory ff: list) {
        	CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
        	if(ff.getStartDate() != null) {
        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate()))));
        	}
        	if(ff.getEndDate() != null) {
        		cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate()))));
        	}
            ls.add(cfc);
        }
        return ls;
    }

    public List<CmsFeeCategory> updateFeeCategory(UpdateFeeCategoryInput updateFeeCategoryInput) throws ParseException, Exception {
//        FeeCategory feeCategory = feeCategoryRepository.findById(updateFeeCategoryInput.getId()).get();
        FeeCategory fc = CommonUtil.createCopyProperties(updateFeeCategoryInput, FeeCategory.class);
        fc.setUpdatedOn(LocalDate.now());
        fc.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFeeCategoryInput.getStartDate()));
        fc.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFeeCategoryInput.getEndDate()));
        Branch branch = new Branch();
        branch.setId(updateFeeCategoryInput.getBranchId());
        fc.setBranch(branch);
        fc = feeCategoryRepository.save(fc);

        FeeCategory f = new FeeCategory();
        f.setBranch(branch);
        Example<FeeCategory> example = Example.of(f);
        List<FeeCategory> list = this.feeCategoryRepository.findAll(example, Sort.by(Direction.DESC, "id"));
        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory ff: list) {
        	CmsFeeCategory cfc = CommonUtil.createCopyProperties(ff, CmsFeeCategory.class);
        	if(ff.getStartDate() != null) {
        		cfc.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getStartDate()))));
        	}
        	if(ff.getEndDate() != null) {
        		cfc.setStrEndDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ff.getEndDate()))));
        	}
            ls.add(cfc);
        }
        return ls;

    }

    public RemoveFeeCategoryPayload removeFeeCategory(RemoveFeeCategoryInput removeFeeCategoryInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(removeFeeCategoryInput.getFeeCategoryId()).get();
        feeCategoryRepository.delete(feeCategory);
        return new RemoveFeeCategoryPayload(Lists.newArrayList(feeCategoryRepository.findAll()));
    }

    public CmsFeeDetails addFeeDetails(AddFeeDetailsInput addFeeDetailsInput) throws ParseException, Exception {

    	FeeCategory feeCategory = feeCategoryRepository.findById(addFeeDetailsInput.getFeeCategoryId()).get();

        Batch batch = null;
        if(addFeeDetailsInput.getBatchId() != null) {
        	batch = batchRepository.findById(addFeeDetailsInput.getBatchId()).get();
        }

        Facility facility = null;
        if(addFeeDetailsInput.getFacilityId() != null) {
        	facility = facilityRepository.findById(addFeeDetailsInput.getFacilityId()).get();
        }

        TransportRoute transportRoute = null;
        if(addFeeDetailsInput.getTransportRouteId() != null) {
        	transportRoute = transportRouteRepository.findById(addFeeDetailsInput.getTransportRouteId()).get();
        }

        Department department = null;
        if(addFeeDetailsInput.getDepartmentId() != null) {
        	department = departmentRepository.findById(addFeeDetailsInput.getDepartmentId()).get();
        }

        FeeDetails feeDetails = CommonUtil.createCopyProperties(addFeeDetailsInput, FeeDetails.class);
        feeDetails.setFeeCategory(feeCategory);
        feeDetails.setBatch(batch);
        feeDetails.setFacility(facility);
        feeDetails.setTransportRoute(transportRoute);
        feeDetails.setDepartment(department);
        feeDetails.createdOn(LocalDate.now());
        feeDetails.startDate(LocalDate.now());
        feeDetails = feeDetailsRepository.save(feeDetails);
        CmsFeeDetails cfd = CommonUtil.createCopyProperties(feeDetails, CmsFeeDetails.class);
        if(feeDetails.getStartDate() != null) {
    		cfd.setStrStartDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(feeDetails.getStartDate()))));
    	}
    	if(feeDetails.getCreatedOn() != null) {
    		cfd.setStrCreatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.SRC_DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(feeDetails.getCreatedOn()))));
    	}
        return cfd;
    }


    public UpdateFeeDetailsPayload updateFeeDetails(UpdateFeeDetailsInput updateFeeDetailsInput) {
        FeeDetails feeDetails = feeDetailsRepository.findById(updateFeeDetailsInput.getId()).get();
        if (updateFeeDetailsInput.getFeeParticularsName() != null) {
            feeDetails.setFeeParticularsName(updateFeeDetailsInput.getFeeParticularsName());
        }

        if (updateFeeDetailsInput.getFeeParticularDesc() != null) {
            feeDetails.setFeeParticularDesc(updateFeeDetailsInput.getFeeParticularDesc());
        }

        if (updateFeeDetailsInput.getStudentType() != null) {
            feeDetails.setStudentType(updateFeeDetailsInput.getStudentType());
        }

        if (updateFeeDetailsInput.getGender() != null) {
            feeDetails.setGender(updateFeeDetailsInput.getGender());
        }
        if (updateFeeDetailsInput.getAmount() != null) {
            feeDetails.setAmount(updateFeeDetailsInput.getAmount());
        }
        logger.debug("Amount : "+updateFeeDetailsInput.getAmount());
        if (updateFeeDetailsInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateFeeDetailsInput.getDepartmentId()).get();
            feeDetails.setDepartment(department);
        }
//        if (updateFeeDetailsInput.getBranchId() != null) {
//            Branch branch = branchRepository.findById(updateFeeDetailsInput.getBranchId()).get();
//            feeDetails.setBranch(branch);
//        }
//        if (updateFeeDetailsInput.getCollegeId() != null) {
//            College college = collegeRepository.findById(updateFeeDetailsInput.getCollegeId()).get();
//            feeDetails.setCollege(college);
//        }
//        if (updateFeeDetailsInput.getAcademicyearId() != null) {
//            AcademicYear academicYear = academicYearRepository.findById(updateFeeDetailsInput.getAcademicyearId()).get();
//            feeDetails.setAcademicYear(academicYear);
//        }
        if (updateFeeDetailsInput.getFeeCategoryId() != null) {
            FeeCategory feeCategory = feeCategoryRepository.findById(updateFeeDetailsInput.getFeeCategoryId()).get();
            feeDetails.setFeeCategory(feeCategory);
        }
        if (updateFeeDetailsInput.getBatchId() != null) {
            Batch batch = batchRepository.findById(updateFeeDetailsInput.getBatchId()).get();
            feeDetails.setBatch(batch);
        }

        if (updateFeeDetailsInput.getFacilityId() != null) {
            Facility facility = facilityRepository.findById(updateFeeDetailsInput.getFacilityId()).get();
            feeDetails.setFacility(facility);
        }
        if (updateFeeDetailsInput.getTransportRouteId() != null) {
            TransportRoute transportRoute = transportRouteRepository.findById(updateFeeDetailsInput.getTransportRouteId()).get();
            feeDetails.setTransportRoute(transportRoute);
        }

        feeDetailsRepository.save(feeDetails);
        return new UpdateFeeDetailsPayload(feeDetails);
    }

    public RemoveFeeDetailsPayload removeFeeDetails(RemoveFeeDetailsInput removeFeeDetailsInput) {
        FeeDetails feeDetails = feeDetailsRepository.findById(removeFeeDetailsInput.getFeeDetailsId()).get();
        feeDetailsRepository.delete(feeDetails);
        return new RemoveFeeDetailsPayload(Lists.newArrayList(feeDetailsRepository.findAll()));
    }

    public CmsAdmissionApplicationVo addAdmissionPersonaldetails(AddAdmissionPersonalDetailsInput addAdmissionPersonalDetailsInput) {

        Country country = countryRepository.findById(addAdmissionPersonalDetailsInput.getCountryId()).get();

        final AdmissionApplication admissionApplication = new AdmissionApplication();
        admissionApplication.setStudentName(addAdmissionPersonalDetailsInput.getStudentName());
        admissionApplication.setStudentMiddleName(addAdmissionPersonalDetailsInput.getStudentMiddleName());
        admissionApplication.setStudentLastName(addAdmissionPersonalDetailsInput.getStudentLastName());
        admissionApplication.setFatherName(addAdmissionPersonalDetailsInput.getFatherName());
        admissionApplication.setFatherMiddleName(addAdmissionPersonalDetailsInput.getFatherMiddleName());
        admissionApplication.setFatherLastName(addAdmissionPersonalDetailsInput.getFatherLastName());
        admissionApplication.setMotherName(addAdmissionPersonalDetailsInput.getMotherName());
        admissionApplication.setMotherMiddleName(addAdmissionPersonalDetailsInput.getMotherMiddleName());
        admissionApplication.setMotherLastName(addAdmissionPersonalDetailsInput.getMotherLastName());
        admissionApplication.setContactNumber(addAdmissionPersonalDetailsInput.getContactNumber());
        admissionApplication.setAlternateMobileNumber(addAdmissionPersonalDetailsInput.getAlternateMobileNumber());
        admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(addAdmissionPersonalDetailsInput.getDateOfBirth()));
        admissionApplication.setEmail(addAdmissionPersonalDetailsInput.getEmail());
        admissionApplication.setSex(addAdmissionPersonalDetailsInput.getSex());
        admissionApplication.setCountry(country);
        admissionApplicationRepository.save(admissionApplication);
        CmsAdmissionApplicationVo caa = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);
       return caa;
    }

    public CmsAdmissionApplicationVo updateAdmissionPersonaldetails(UpdateAdmissionPersonalDetailsInput updateAdmissionPersonalDetailsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(updateAdmissionPersonalDetailsInput.getId()).get();


        if (updateAdmissionPersonalDetailsInput.getStudentName() != null) {
            admissionApplication.setStudentName(updateAdmissionPersonalDetailsInput.getStudentName());
        }
        if (updateAdmissionPersonalDetailsInput.getStudentMiddleName() != null) {
            admissionApplication.setStudentMiddleName(updateAdmissionPersonalDetailsInput.getStudentMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getStudentLastName() != null) {
            admissionApplication.setStudentLastName(updateAdmissionPersonalDetailsInput.getStudentLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherName() != null) {
            admissionApplication.setFatherName(updateAdmissionPersonalDetailsInput.getFatherName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherMiddleName() != null) {
            admissionApplication.setFatherMiddleName(updateAdmissionPersonalDetailsInput.getFatherMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getFatherLastName() != null) {
            admissionApplication.setFatherLastName(updateAdmissionPersonalDetailsInput.getFatherLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherName() != null) {
            admissionApplication.setMotherName(updateAdmissionPersonalDetailsInput.getMotherName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherMiddleName() != null) {
            admissionApplication.setMotherMiddleName(updateAdmissionPersonalDetailsInput.getMotherMiddleName());
        }
        if (updateAdmissionPersonalDetailsInput.getMotherLastName() != null) {
            admissionApplication.setMotherLastName(updateAdmissionPersonalDetailsInput.getMotherLastName());
        }
        if (updateAdmissionPersonalDetailsInput.getContactNumber() != null) {
            admissionApplication.setContactNumber(updateAdmissionPersonalDetailsInput.getContactNumber());
        }
        if (updateAdmissionPersonalDetailsInput.getAlternateMobileNumber() != null) {
            admissionApplication.setAlternateMobileNumber(updateAdmissionPersonalDetailsInput.getAlternateMobileNumber());
        }
        if (updateAdmissionPersonalDetailsInput.getDateOfBirth() != null) {
            admissionApplication.setDateOfBirth(DateFormatUtil.convertLocalDateFromUtilDate(updateAdmissionPersonalDetailsInput.getDateOfBirth()));
        }
        if (updateAdmissionPersonalDetailsInput.getEmail() != null) {
            admissionApplication.setEmail(updateAdmissionPersonalDetailsInput.getEmail());
        }
        if (updateAdmissionPersonalDetailsInput.getSex() != null) {
            admissionApplication.setSex(updateAdmissionPersonalDetailsInput.getSex());
        }

        if (updateAdmissionPersonalDetailsInput.getCountryId() != null) {
            Country country = countryRepository.findById(updateAdmissionPersonalDetailsInput.getCountryId()).get();
            admissionApplication.setCountry(country);
        }


        admissionApplicationRepository.save(admissionApplication);

        CmsAdmissionApplicationVo caa = CommonUtil.createCopyProperties(admissionApplication, CmsAdmissionApplicationVo.class);
        return caa;
    }

    public RemoveAdmissionPersonalDetailsPayload removeAdmissionPersonalDetails(RemoveAdmissionPersonalDetailsInput removeAdmissionPersonalDetailsInput) {
        AdmissionApplication admissionApplication = admissionApplicationRepository.findById(removeAdmissionPersonalDetailsInput.getAdmissionApplicationId()).get();

        admissionApplicationRepository.delete(admissionApplication);
        return new RemoveAdmissionPersonalDetailsPayload(Lists.newArrayList(admissionApplicationRepository.findAll()));
    }

    public AddFacilityPayload addFacility(AddFacilityInput addFacilityInput) {
        final Branch branch = branchRepository.findById(addFacilityInput.getBranchId()).get();
//        final Student student = studentRepository.findById(addFacilityInput.getStudentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addFacilityInput.getAcademicyearId()).get();
        Facility facility = new Facility();

        facility.setAcademicYear(academicYear);
        facility.setBranch(branch);
        facility.setName(addFacilityInput.getName());
        facility.setStatus(Status.ACTIVE);
        facility.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(addFacilityInput.getStartDate()));

        facilityRepository.save(facility);

        return new AddFacilityPayload(facility);
    }

    public UpdateFacilityPayload updateFacility(UpdateFacilityInput updateFacilityInput) {
        Facility facility = facilityRepository.findById(updateFacilityInput.getId()).get();

        if (updateFacilityInput.getName() != null) {
            facility.setName(updateFacilityInput.getName());
        }
        if (updateFacilityInput.getStatus() != null) {
            facility.setStatus(updateFacilityInput.getStatus());
        }
        if (updateFacilityInput.getStartDate() != null) {
            facility.setStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getStartDate()));
        }
        if (updateFacilityInput.getEndDate() != null) {
            facility.setEndDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getEndDate()));
        }
        if (updateFacilityInput.getSuspandStartDate() != null) {
            facility.setSuspandStartDate(DateFormatUtil.convertLocalDateFromUtilDate(updateFacilityInput.getSuspandStartDate()));
        }
        if (updateFacilityInput.getSuspandEndDate() != null) {
            facility.setSuspandEndDate(DateFormatUtil.convertLocalDateFromUtilDate (updateFacilityInput.getSuspandEndDate()));
        }


        if (updateFacilityInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateFacilityInput.getBranchId()).get();
            facility.setBranch(branch);
        }
        if (updateFacilityInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateFacilityInput.getAcademicyearId()).get();
            facility.setAcademicYear(academicYear);
        }
        facilityRepository.save(facility);

        return new UpdateFacilityPayload(facility);

    }

    public RemoveFacilityPayload removeFacility(RemoveFacilityInput removeFacilityInput) {
        Facility facility = facilityRepository.getOne(removeFacilityInput.getFacilityId());
        facilityRepository.delete(facility);

        return new RemoveFacilityPayload(Lists.newArrayList(facilityRepository.findAll()));
    }

    public AddTransportRoutePayload addTransportRoute(AddTransportRouteInput addTransportRouteInput) {
        final TransportRoute transportRoute = new TransportRoute();
        transportRoute.setRouteDetails(addTransportRouteInput.getRouteDetails());
        transportRoute.setRouteName(addTransportRouteInput.getRouteName());
        transportRoute.setRouteMapUrl(addTransportRouteInput.getRouteMapUrl());
        transportRoute.setNoOfStops(addTransportRouteInput.getNoOfStops());
        transportRoute.setRouteFrequency(addTransportRouteInput.getRouteFrequency());
        transportRouteRepository.save(transportRoute);

        return new AddTransportRoutePayload(transportRoute);
    }


    public UpdateTransportRoutePayload updateTransportRoute(UpdateTransportRouteInput updateTransportRouteInput) {
        TransportRoute transportRoute = transportRouteRepository.findById(updateTransportRouteInput.getId()).get();
        if (updateTransportRouteInput.getRouteDetails() != null) {
            transportRoute.setRouteDetails(updateTransportRouteInput.getRouteDetails());
        }
        if (updateTransportRouteInput.getRouteName() != null) {
            transportRoute.setRouteName(updateTransportRouteInput.getRouteName());
        }
        if (updateTransportRouteInput.getRouteMapUrl() != null) {
            transportRoute.setRouteMapUrl(updateTransportRouteInput.getRouteMapUrl());
        }
        if (updateTransportRouteInput.getNoOfStops() != null) {
            transportRoute.setNoOfStops(updateTransportRouteInput.getNoOfStops());
        }
        if (updateTransportRouteInput.getRouteFrequency() != null) {
            transportRoute.setRouteFrequency(updateTransportRouteInput.getRouteFrequency());
        }
        transportRouteRepository.save(transportRoute);
        return new UpdateTransportRoutePayload(transportRoute);
    }

    public RemoveTransportRoutePayload removeTransportRoute(RemoveTransportRouteInput removeTransportRouteInput) {
        TransportRoute transportRoute = transportRouteRepository.getOne(removeTransportRouteInput.getTransportRouteId());
        return new RemoveTransportRoutePayload(Lists.newArrayList(transportRouteRepository.findAll()));
    }

    public AddDueDatePayload addDueDate(AddDueDateInput addDueDateInput) {
        College college = collegeRepository.findById(addDueDateInput.getCollegeId()).get();
        Branch branch = branchRepository.findById((addDueDateInput.getBranchId())).get();
        DueDate dueDate = new DueDate();
        dueDate.setPaymentMethod(addDueDateInput.getPaymentMethod());
        dueDate.setInstallments(addDueDateInput.getInstallments());
        dueDate.setDayDesc(addDueDateInput.getDayDesc());
        dueDate.setPaymentDay(addDueDateInput.getPaymentDay());
        dueDate.setFrequency(findFrequency(addDueDateInput.getFrequency()));
        dueDate.setCollege(college);
        dueDate.setBranch(branch);
        dueDate = dueDateRepository.save(dueDate);
        return new AddDueDatePayload(dueDate);
    }

    private Frequency findFrequency(Frequency frequency) {
    	if(Frequency.WEEKLY.equals(frequency)) {
    		return Frequency.WEEKLY;
    	}else if(Frequency.MONTHLY.equals(frequency)) {
    		return Frequency.MONTHLY;
    	}else if(Frequency.QUARTERLY.equals(frequency)) {
    		return Frequency.QUARTERLY;
    	}else if(Frequency.HALFYEARLY.equals(frequency)) {
    		return Frequency.HALFYEARLY;
    	}else if(Frequency.ANNUALLY.equals(frequency)) {
    		return Frequency.ANNUALLY;
    	}
    	return null;
    }

    public UpdateDueDatePayload updateDueDate(UpdateDueDateInput updateDueDateInput) {
        DueDate dueDate = dueDateRepository.findById(updateDueDateInput.getId()).get();
        if (updateDueDateInput.getPaymentMethod() != null) {
            dueDate.setPaymentMethod(updateDueDateInput.getPaymentMethod());
        }
        if (updateDueDateInput.getInstallments() != null) {
            dueDate.setInstallments(updateDueDateInput.getInstallments());
        }
        if (updateDueDateInput.getDayDesc() != null) {
            dueDate.setDayDesc(updateDueDateInput.getDayDesc());
        }
        if (updateDueDateInput.getPaymentDay() != null) {
            dueDate.setPaymentDay(updateDueDateInput.getPaymentDay());
        }
        if (updateDueDateInput.getFrequency() != null) {
            dueDate.setFrequency(updateDueDateInput.getFrequency());
        }
        if (updateDueDateInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateDueDateInput.getCollegeId()).get();
            dueDate.setCollege(college);

        }
        if (updateDueDateInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateDueDateInput.getBranchId()).get();
            dueDate.setBranch(branch);

        }
        dueDateRepository.save(dueDate);
        return new UpdateDueDatePayload(dueDate);
    }

    public RemoveDueDatePayload removeDueDate(RemoveDueDateInput removeDueDateInput) {
        DueDate dueDate = dueDateRepository.findById(removeDueDateInput.getDueDateId()).get();
        return new RemoveDueDatePayload(Lists.newArrayList(dueDateRepository.findAll()));
    }

    public AddLateFeePayload addLateFee(AddLateFeeInput addLateFeeInput) {
        final College college = collegeRepository.findById(addLateFeeInput.getCollegeId()).get();
        final Branch branch = branchRepository.findById(addLateFeeInput.getBranchId()).get();
        LateFee lateFee = CommonUtil.createCopyProperties(addLateFeeInput, LateFee.class);
        lateFee.setCollege(college);
        lateFee.setBranch(branch);
        lateFee = lateFeeRepository.save(lateFee);
        return new AddLateFeePayload(lateFee);
    }

    public UpdateLateFeePayload updateLateFee(UpdateLateFeeInput updateLateFeeInput) {
        LateFee lateFee = CommonUtil.createCopyProperties(updateLateFeeInput, LateFee.class);

        if (updateLateFeeInput.getCollegeId() != null) {
            final College college = collegeRepository.findById(updateLateFeeInput.getCollegeId()).get();
            lateFee.setCollege(college);
        }

        if (updateLateFeeInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updateLateFeeInput.getBranchId()).get();
            lateFee.setBranch(branch);
        }
        lateFeeRepository.save(lateFee);
        return new UpdateLateFeePayload(lateFee);
    }

    public RemoveLateFeePayload removeLateFee(RemoveLateFeeInput removeLateFeeInput) {
        LateFee lateFee = lateFeeRepository.findById(removeLateFeeInput.getLateFeeId()).get();
        lateFeeRepository.delete(lateFee);
        return new RemoveLateFeePayload(Lists.newArrayList(lateFeeRepository.findAll()));
    }

    public AddPaymentRemainderPayload addPaymentRemainder(AddPaymentRemainderInput addPaymentRemainderInput) {
        final College college = collegeRepository.findById(addPaymentRemainderInput.getCollegeId()).get();
        final Branch branch = branchRepository.findById(addPaymentRemainderInput.getBranchId()).get();
        PaymentRemainder pr = CommonUtil.createCopyProperties(addPaymentRemainderInput, PaymentRemainder.class);
        pr.setCollege(college);
        pr.setBranch(branch);
        pr = paymentRemainderRepository.save(pr);
        return new AddPaymentRemainderPayload(pr);
    }

    public UpdatePaymentRemainderPayload updatePaymentRemainder(UpdatePaymentRemainderInput updatePaymentRemainderInput) {
        PaymentRemainder pr = CommonUtil.createCopyProperties(updatePaymentRemainderInput, PaymentRemainder.class);

        if (updatePaymentRemainderInput.getCollegeId() != null) {
            final College college = collegeRepository.findById(updatePaymentRemainderInput.getCollegeId()).get();
            pr.setCollege(college);
        }
        if (updatePaymentRemainderInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updatePaymentRemainderInput.getBranchId()).get();
            pr.setBranch(branch);
        }
        paymentRemainderRepository.save(pr);
        return new UpdatePaymentRemainderPayload(pr);
    }

    public RemovePaymentRemainderPayload removePaymentRemainder(RemovePaymentRemainderInput removePaymentRemainderInput) {
        PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(removePaymentRemainderInput.getPaymentRemainderId()).get();
        paymentRemainderRepository.delete(paymentRemainder);
        return new RemovePaymentRemainderPayload(Lists.newArrayList(paymentRemainderRepository.findAll()));
    }


    public AddInvoicePayload addInvoice(AddInvoiceInput addInvoiceInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(addInvoiceInput.getFeeCategoryId()).get();
        Branch branch = branchRepository.findById(addInvoiceInput.getBranchId()).get();
        College college = collegeRepository.findById(addInvoiceInput.getCollegeId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addInvoiceInput.getAcademicyearId()).get();
        FeeDetails feeDetails = feeDetailsRepository.findById(addInvoiceInput.getFeeDetailsId()).get();
        DueDate dueDate = dueDateRepository.findById(addInvoiceInput.getDueDateId()).get();
        Student student = studentRepository.findById(addInvoiceInput.getStudentId()).get();
        PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(addInvoiceInput.getPaymentRemainderId()).get();
        final Invoice invoice   = new Invoice();
        invoice.setInvoiceNumber(addInvoiceInput.getInvoiceNumber());
        invoice.setAmountPaid(addInvoiceInput.getAmountPaid());
        invoice.setPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getPaymentDate()));
        invoice.setNextPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getNextPaymentDate()));
        invoice.setOutStandingAmount(addInvoiceInput.getOutStandingAmount());
        invoice.setModeOfPayment(addInvoiceInput.getModeOfPayment());
        invoice.setChequeNumber(addInvoiceInput.getChequeNumber());
        invoice.setDemandDraftNumber(addInvoiceInput.getDemandDraftNumber());
        invoice.setOnlineTxnRefNumber(addInvoiceInput.getOnlineTxnRefNumber());
        invoice.setPaymentStatus(addInvoiceInput.getPaymentStatus());
        invoice.setComments(addInvoiceInput.getComments());
        invoice.setUpdatedBy(addInvoiceInput.getUpdatedBy());
        invoice.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(addInvoiceInput.getUpdatedOn()));
        invoice.setFeeCategory(feeCategory);
        invoice.setFeeDetails(feeDetails);
        invoice.setDueDate(dueDate);
        invoice.setPaymentRemainder(paymentRemainder);
        invoice.setCollege(college);
        invoice.setBranch(branch);
        invoice.setAcademicYear(academicYear);
        invoice.setStudent(student);
        invoiceRepository.save(invoice);
        return new AddInvoicePayload(invoice);
    }

    public UpdateInvoicePayload updateInvoice(UpdateInvoiceInput updateInvoiceInput) {
        Invoice invoice = invoiceRepository.findById(updateInvoiceInput.getId()).get();
        if (updateInvoiceInput.getInvoiceNumber() != null) {
            invoice.setInvoiceNumber(updateInvoiceInput.getInvoiceNumber());
        }

        if (updateInvoiceInput.getAmountPaid() != null) {
            invoice.setAmountPaid(updateInvoiceInput.getAmountPaid());
        }

        if (updateInvoiceInput.getPaymentDate() != null) {
            invoice.setPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getPaymentDate()));
        }

        if (updateInvoiceInput.getNextPaymentDate() != null) {
            invoice.setNextPaymentDate(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getNextPaymentDate()));
        }
        if (updateInvoiceInput.getOutStandingAmount() != null) {
            invoice.setOutStandingAmount(updateInvoiceInput.getOutStandingAmount());
        }

        if (updateInvoiceInput.getModeOfPayment() != null) {
            invoice.setModeOfPayment(updateInvoiceInput.getModeOfPayment());
        }

        if (updateInvoiceInput.getChequeNumber() != null) {
            invoice.setChequeNumber(updateInvoiceInput.getChequeNumber());
        }

        if (updateInvoiceInput.getDemandDraftNumber() != null) {
            invoice.setDemandDraftNumber(updateInvoiceInput.getDemandDraftNumber());
        }

        if (updateInvoiceInput.getOnlineTxnRefNumber() != null) {
            invoice.setOnlineTxnRefNumber(updateInvoiceInput.getOnlineTxnRefNumber());
        }
        if (updateInvoiceInput.getPaymentStatus() != null) {
            invoice.setPaymentStatus(updateInvoiceInput.getPaymentStatus());
        }

        if (updateInvoiceInput.getComments() != null) {
            invoice.setComments(updateInvoiceInput.getComments());
        }

        if (updateInvoiceInput.getUpdatedBy() != null) {
            invoice.setUpdatedBy(updateInvoiceInput.getUpdatedBy());
        }

        if (updateInvoiceInput.getUpdatedOn() != null) {
            invoice.setUpdatedOn(DateFormatUtil.convertLocalDateFromUtilDate(updateInvoiceInput.getUpdatedOn()));
        }


        if(updateInvoiceInput.getFeeCategoryId() != null) {
            FeeCategory feeCategory = feeCategoryRepository.findById(updateInvoiceInput.getFeeCategoryId()).get();
            invoice.setFeeCategory(feeCategory);
        }
        if(updateInvoiceInput.getFeeDetailsId() != null) {
            FeeDetails feeDetails = feeDetailsRepository.findById(updateInvoiceInput.getFeeDetailsId()).get();
            invoice.setFeeDetails(feeDetails);
        }
        if(updateInvoiceInput.getCollegeId() != null) {
            College college =collegeRepository.findById(updateInvoiceInput.getCollegeId()).get();
            invoice.setCollege(college);
        }
        if(updateInvoiceInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateInvoiceInput.getAcademicyearId()).get();
            invoice.setAcademicYear(academicYear);
        }
        if(updateInvoiceInput.getDueDateId() != null) {
            DueDate dueDate = dueDateRepository.findById(updateInvoiceInput.getDueDateId()).get();
            invoice.setDueDate(dueDate);
        }
        if(updateInvoiceInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateInvoiceInput.getBranchId()).get();
            invoice.setBranch(branch);
        }

        if(updateInvoiceInput.getStudentId() != null) {
            Student student = studentRepository.findById(updateInvoiceInput.getStudentId()).get();
            invoice.setStudent(student);
        }
        if(updateInvoiceInput.getPaymentRemainderId() != null) {
            PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(updateInvoiceInput.getPaymentRemainderId()).get();
            invoice.setPaymentRemainder(paymentRemainder);
        }

        invoiceRepository.save(invoice);
        return new UpdateInvoicePayload(invoice);
    }

    public RemoveInvoicePayload removeInvoice(RemoveInvoiceInput removeInvoiceInput) {
        Invoice invoice = invoiceRepository.findById(removeInvoiceInput.getInvoiceId()).get();
        invoiceRepository.delete(invoice);
        return new RemoveInvoicePayload(Lists.newArrayList(invoiceRepository.findAll()));
    }

    public AddLibraryPayload addLibrary(AddLibraryInput libraryInput){
        Batch batch = batchRepository.findById(libraryInput.getBatchId()).get();
        Subject subject = subjectRepository.findById(libraryInput.getSubjectId()).get();
        Department department =departmentRepository.findById(libraryInput.getDepartmentId()).get();
        final Library book = new Library ();
        book.setBookTitle(libraryInput.getBookTitle());
        book.setAuthor(libraryInput.getAuthor());
        book.setBookId(libraryInput.getBookId());
        book.setNoOfCopies(libraryInput.getNoOfCopies());
        book.setBatch(batch);
        book.setSubject(subject);
        book.setDepartment(department);
        libraryRepository.save(book);
        return new AddLibraryPayload(book);
    }
    public UpdateLibraryPayload updateLibrary(UpdateLibraryInput updateLibraryInput) {
        Library book = libraryRepository.findById(updateLibraryInput.getId()).get();
        if(updateLibraryInput.getBookTitle()!=null) {
            book.setBookTitle(updateLibraryInput.getBookTitle());
        }
        if (updateLibraryInput.getAuthor() != null){
            book.setAuthor(updateLibraryInput.getAuthor());
        }
        if(updateLibraryInput.getBookId()!=null){
            book.setBookId(updateLibraryInput.getBookId());
        }
        if (updateLibraryInput.getNoOfCopies() != null) {
            book.setNoOfCopies(updateLibraryInput.getNoOfCopies());
        }

        if(updateLibraryInput.getBatchId()!=null){
            Batch batch =batchRepository.findById(updateLibraryInput.getBatchId()).get();
            book.setBatch(batch);
        }
        if(updateLibraryInput.getSubjectId()!=null){
            Subject subject =subjectRepository.findById(updateLibraryInput.getSubjectId()).get();
        }
        if(updateLibraryInput.getDepartmentId()!= null){
            Department department =departmentRepository.findById(updateLibraryInput.getDepartmentId()).get();
        }

        libraryRepository.save(book);
        return new UpdateLibraryPayload(book);

    }
    public RemoveLibraryPayload removeLibrary(RemoveLibraryInput removeLibraryInput){
        Library book =libraryRepository.findById(removeLibraryInput.getLibraryId()).get();
        libraryRepository.delete(book);
        return new RemoveLibraryPayload((Lists.newArrayList(libraryRepository.findAll())));
    }

    public AddInsurancePayload addInsurance(AddInsuranceInput addInsuranceInput) {
        final Vehicle vehicle = vehicleRepository.findById(addInsuranceInput.getVehicleId()).get();
        final Insurance insurance = new Insurance();
        insurance.setInsuranceCompany(addInsuranceInput.getInsuranceCompany());
        insurance.setTypeOfInsurance(addInsuranceInput.getTypeOfInsurance());
        insurance.setDateOfInsurance(DateFormatUtil.convertLocalDateFromUtilDate(addInsuranceInput.getDateOfInsurance()));
        insurance.setValidTill(DateFormatUtil.convertLocalDateFromUtilDate(addInsuranceInput.getValidTill()));
        insurance.setVehicle(vehicle);
        insuranceRepository.save(insurance);

        return new AddInsurancePayload(insurance);
    }

    public UpdateInsurancePayload updateInsurance(UpdateInsuranceInput updateInsuranceInput) {
        Insurance insurance = insuranceRepository.findById(updateInsuranceInput.getId()).get();

        if (updateInsuranceInput.getInsuranceCompany() != null) {
            insurance.setInsuranceCompany(updateInsuranceInput.getInsuranceCompany());
        }
        if (updateInsuranceInput.getTypeOfInsurance() != null) {
            insurance.setInsuranceCompany(updateInsuranceInput.getInsuranceCompany());
        }
        if (updateInsuranceInput.getDateOfInsurance() != null) {
            insurance.setDateOfInsurance(DateFormatUtil.convertLocalDateFromUtilDate(updateInsuranceInput.getDateOfInsurance()));
        }
        if (updateInsuranceInput.getValidTill() != null) {
            insurance.setValidTill(DateFormatUtil.convertLocalDateFromUtilDate(updateInsuranceInput.getValidTill()));
        }

        if (updateInsuranceInput.getVehicleId() != null) {
            final Vehicle vehicle = vehicleRepository.findById(updateInsuranceInput.getVehicleId()).get();
            insurance.setVehicle(vehicle);
        }
        insuranceRepository.save(insurance);

        return new UpdateInsurancePayload(insurance);
    }

    public RemoveInsurancePayload removeInsurance(RemoveInsuranceInput removeInsuranceInput) {
        Insurance insurance = insuranceRepository.findById(removeInsuranceInput.getInsuranceId()).get();
        insuranceRepository.delete(insurance);
        return new RemoveInsurancePayload(Lists.newArrayList(insuranceRepository.findAll()));
    }

    public AddVehiclePayload addVehicle(AddVehicleInput addVehicleInput) {
        final Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber(addVehicleInput.getVehicleNumber());
        vehicle.setVehicleType(addVehicleInput.getVehicleType());
        vehicle.setCapacity(addVehicleInput.getCapacity());
        vehicle.setOwnerShip(addVehicleInput.getOwnerShip());
        vehicle.setDateOfRegistration(DateFormatUtil.convertLocalDateFromUtilDate(addVehicleInput.getDateOfRegistration()));
        vehicle.setYearOfManufacturing(addVehicleInput.getYearOfManufacturing());
        vehicle.setManufacturingCompany(addVehicleInput.getManufacturingCompany());
        vehicle.setModel(addVehicleInput.getModel());
        vehicle.setChasisNo(addVehicleInput.getChasisNo());
        vehicle.setRcNo(addVehicleInput.getRcNo());
        vehicle.setContactNumber(addVehicleInput.getContactNumber());
        vehicle.setStatus(addVehicleInput.getStatus());
        vehicleRepository.save(vehicle);

        return new AddVehiclePayload(vehicle);
    }

    public UpdateVehiclePayload updateVehicle(UpdateVehicleInput updateVehicleInput) {
        Vehicle vehicle = vehicleRepository.findById(updateVehicleInput.getId()).get();

        if (updateVehicleInput.getVehicleNumber() != null) {
            vehicle.setVehicleNumber(updateVehicleInput.getVehicleNumber());
        }
        if (updateVehicleInput.getVehicleType() != null) {
            vehicle.setVehicleType(updateVehicleInput.getVehicleType());
        }
        if (updateVehicleInput.getCapacity() != null) {
            vehicle.setCapacity(updateVehicleInput.getCapacity());
        }
        if (updateVehicleInput.getOwnerShip() != null) {
            vehicle.setOwnerShip(updateVehicleInput.getOwnerShip());
        }
        if (updateVehicleInput.getDateOfRegistration() != null) {
            vehicle.setDateOfRegistration(DateFormatUtil.convertLocalDateFromUtilDate(updateVehicleInput.getDateOfRegistration()));
        }
        if (updateVehicleInput.getYearOfManufacturing() != null) {
            vehicle.setYearOfManufacturing(updateVehicleInput.getYearOfManufacturing());
        }
        if (updateVehicleInput.getModel() != null) {
            vehicle.setModel(updateVehicleInput.getModel());
        }
        if (updateVehicleInput.getChasisNo() != null) {
            vehicle.setChasisNo(updateVehicleInput.getChasisNo());
        }
        if (updateVehicleInput.getRcNo() != null) {
            vehicle.setRcNo(updateVehicleInput.getRcNo());
        }
        if (updateVehicleInput.getContactNumber() != null) {
            vehicle.setContactNumber(updateVehicleInput.getContactNumber());
        }
        if (updateVehicleInput.getStatus() != null) {
            vehicle.setStatus(updateVehicleInput.getStatus());
        }

        vehicleRepository.save(vehicle);

        return new UpdateVehiclePayload(vehicle);
    }

    public RemoveVehiclePayload removeVehicle(RemoveVehicleInput removeVehicleInput) {
        Vehicle vehicle = vehicleRepository.findById(removeVehicleInput.getVehicleId()).get();
        vehicleRepository.delete(vehicle);
        return new RemoveVehiclePayload(Lists.newArrayList(vehicleRepository.findAll()));
    }

    /**
     * Method to update student attendance data. It accepts a list of objects containing student attendance ids and lecture id.
     * It returns the status as zero (0) if method completes successfully or non zero (>0) if execution fails due to any reason.
     * @param list
     * @return QueryResult
     * @throws JSONException
     * @throws ParseException
     */
    public QueryResult updateStudentAttendanceData(List<StudentAttendanceUpdateFilter> list) throws JSONException, ParseException {
        logger.debug("Mutation to update student attendance data " + list.toString());
    	QueryResult res = this.studentAttendanceFilterImpl.updateStudentStatus(list);
    	if(res.getStatusCode() == 0) {
    		logger.info("Student attendance data updated successfully.");
    	}else {
    		logger.info("Due to some error student attendance data could not be updated successfully.");
    	}
        return res;
    }
    public QueryResult updateAcademicExamSettingData(List<AcademicExamSettingUpdateFilter> list) throws JSONException, ParseException {
        logger.debug("Mutation to update exam data " + list.toString());
        QueryResult res = this.academicExamSettingFilterImpl.updateExam(list);
        if(res.getStatusCode() == 0) {
            logger.info("exam data updated successfully.");
        }else {
            logger.info("Due to some error exam data could not be updated successfully.");
        }
        return res;
    }
    public QueryResult updateAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        QueryResult res = this.academicSubjectProcessor.updateAcademicSubjects(academicSubjectMutationPayload);
        return res;
    }

    public QueryResult addAcademicSubjects(AcademicSubjectMutationPayload academicSubjectMutationPayload) throws JSONException, ParseException {
        QueryResult res = this.academicSubjectProcessor.addAcademicSubjects(academicSubjectMutationPayload);
        return res;
    }

    public QueryResult addLectures(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) throws JSONException, ParseException {
        QueryResult res = lectureScheduleProcessor.addLectureSchedule(lectureScheduleInput, filter);
        return res;
    }

    public QueryResult updateLectures(LectureScheduleInput lectureScheduleInput, LectureScheduleFilter filter) {
        QueryResult res = lectureScheduleProcessor.updateLectureSchedule(lectureScheduleInput, filter);
        return res;
    }

    /**
     * getDailyStudentAttendanceData(StudentAttendanceFilterInput filter)
     * Retrieves student attendance data for teacher role end user
     * @param filter
     * @return
     * @throws Exception
     */
    public List<DailyAttendanceVo> getDailyStudentAttendanceData(StudentAttendanceFilterInput filter) throws Exception {
        return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendance(filter));
    }
    public List<CmsInvoice> searchInvoice(String invoiceNumber, Long studentId, Long collegeId, Long branchId, Long academicYearId) throws Exception{
        return Lists.newArrayList(invoiceFilterProcessor.searchInvoice(invoiceNumber, studentId, collegeId, branchId, academicYearId));
    }
    public List<CmsLibrary>searchLib(String bookTitle, String author, Long departmentId, Long batchId, Long subjectId)throws Exception{
        return Lists.newArrayList(libraryFilterProcessor.searchLib( bookTitle,  author,  departmentId,  batchId, subjectId));
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

    public List<CmsInvoice> searchInvoiceOnType(String invoiceType, Long collegeId, Long branchId, Long academicYearId) throws Exception{
        return Lists.newArrayList(invoiceFilterProcessor.searchInvoiceOnType(invoiceType, collegeId, branchId, academicYearId));
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


    public List<CmsAdmissionEnquiryVo> searchAdmissionOnType(String admissionEnquiryType,Long branchId) throws Exception{
        return Lists.newArrayList(admissionEnquiryProcessor.searchAdmissionOnType(admissionEnquiryType, branchId));
    }

    public List<CmsAdmissionEnquiryVo> admissionEnquiryList(Long branchId) throws Exception{
        return Lists.newArrayList(admissionEnquiryProcessor.admissionEnquiryList(branchId));
    }

    public List<Student> getStudentList(StudentListFilterInput filter) throws Exception {
    	List<Student> list = this.studentFilterProcessor.searchStudent(filter);
    	logger.debug("Total students retrieved. "+list.size());
    	return list;
    }


    public Long getTotalReceived( long academicyearId) {
        return admissionApplicationProcessor.getTotalReceived( academicyearId);
    }

    public Long getTotalInprocess( long academicyearId) {
        return admissionEnquiryProcessor.getTotalFollowup( academicyearId);
    }
    public Long getTotalDecline(long academicyearId) {
        return admissionApplicationProcessor.getTotalDeclined( academicyearId);
    }

    public Long getTotalAccepted( long academicyearId) {
        return admissionApplicationProcessor.getTotalAccepted(academicyearId);
    }


    public List<CmsAdmissionApplicationVo> searchAdmissionApplicationOnType(String admissionApplicationType,Long academicyearId) throws Exception{
        return Lists.newArrayList(admissionApplicationProcessor.searchAdmissionApplicationOnType(admissionApplicationType, academicyearId));
    }




    /**
	 * getStudentAttendanceDataForAdmin(StudentAttendanceFilterInput filter)
	 * Retrieves student attendance data for admin role end user
	 * @param filter
	 * @return
	 * @throws Exception
	 */
	public List<DailyAttendanceVo> getStudentAttendanceDataForAdmin(StudentAttendanceFilterInput filter) throws Exception {
        return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendanceDataForAdmin(filter));
    }
    public List<DailyExamVo> getExamDataForAdmin(ExamListFilterInput filter) throws Exception {
        return Lists.newArrayList(academicExamSettingFilterImpl.getExamDataForAdmin(filter));
    }

    public List<AcademicExamSetting> getSubjectList(ExamListFilterInput filter) throws Exception {
        List<AcademicExamSetting> list = this.examFilterProcessor.searchSubject(filter);
        logger.debug("Total Subjects retrieved. "+list.size());
        return list;
    }

    public List<StudentExamReport> getSubjectandStudents(ExamReportFilterInput filter) throws Exception {
        List<StudentExamReport> list = this.examFilterProcessor.searchSubjectandStudents(filter);
        logger.debug("Total Subjects retrieved. "+list.size());
        return list;
    }

	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult saveDueDatePaymentRemLateFee(UpdateDueDateInput udd, UpdatePaymentRemainderInput upr, UpdateLateFeeInput ulf) {
		QueryResult qr = new QueryResult();
		qr.setStatusCode(0);
		qr.setStatusDesc("DueDateId: #ddid#, PaymentRemainderId: #prid#, LateFeeId: #lfid#");
		AbstractDueDatePayload ddpl = null;
		AbstractPaymentRemainderPayload prpl = null;
		AbstractLateFeePayload lfpl = null;
		try {
			if(udd.getId() == -1) {
				logger.debug("Its a new record for due date. Adding due date.");
				AddDueDateInput add = CommonUtil.createCopyProperties(udd, AddDueDateInput.class);
				add.setId(null);
				ddpl = addDueDate(add);
			}else {
				logger.debug("Its an existing record for due date. Updating due date.");
				ddpl = updateDueDate(udd);
			}

			if(upr.getId() == -1) {
				logger.debug("Its a new record for payment remainder. Adding payment remainder.");
				AddPaymentRemainderInput apr = CommonUtil.createCopyProperties(upr, AddPaymentRemainderInput.class);
				apr.setId(null);
				prpl = addPaymentRemainder(apr);
			}else {
				logger.debug("Its an existing record for payment remainder. Updating payment remainder.");
				prpl = updatePaymentRemainder(upr);
			}

			if(ulf.getId() == -1) {
				logger.debug("Its a new record for late fee. Adding late fee.");
				AddLateFeeInput alf = CommonUtil.createCopyProperties(ulf, AddLateFeeInput.class);
				alf.setId(null);
				lfpl = addLateFee(alf);
			}else {
				logger.debug("Its an existing record for late fee. Updating late fee.");
				lfpl = updateLateFee(ulf);
			}

			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#ddid#", String.valueOf(ddpl.getDueDate().getId())));
			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#prid#", String.valueOf(prpl.getPaymentRemainder().getId())));
			qr.setStatusDesc(qr.getStatusDesc().replaceAll("#lfid#", String.valueOf(lfpl.getLateFee().getId())));
		}catch(Exception e) {
			qr.setStatusCode(1);
			qr.setStatusDesc("Due to some error due date, payment remainder and late fee could not be saved");
		}
		logger.debug("Success message : "+qr.getStatusDesc());
		return qr;
	}


	public CmsFeeSettingsVo getFeeSettingData(Long branchId) {
		LateFee lf = new LateFee();
		PaymentRemainder pr = new PaymentRemainder();
		Branch branch = new Branch();
		branch.setId(branchId);

		lf.setBranch(branch);
		Example<LateFee> example = Example.of(lf);
		Optional<LateFee> olf = this.lateFeeRepository.findOne(example);
		CmsFeeSettingsVo vo = new CmsFeeSettingsVo();
		if(olf.isPresent()) {
			logger.debug("Getting data for late fee");
			vo = CommonUtil.createCopyProperties(olf.get(), CmsFeeSettingsVo.class);
			vo.setLateFeeId(olf.get().getId());
		}

		pr.setBranch(branch);
		Example<PaymentRemainder> exPr = Example.of(pr);
		Optional<PaymentRemainder> opr = this.paymentRemainderRepository.findOne(exPr);
		if(opr.isPresent()) {
			logger.debug("Getting data for payment remainder");
			vo.setPrId(opr.get().getId());
			vo.setIsAutoRemainder(opr.get().getIsAutoRemainder());
			vo.setIsFirstPaymentRemainder(opr.get().getIsFirstPaymentRemainder());
			vo.setFirstPaymentRemainderDays(opr.get().getFirstPaymentRemainderDays());
			vo.setIsSecondPaymentRemainder(opr.get().getIsSecondPaymentRemainder());
			vo.setSecondPaymentRemainderDays(opr.get().getSecondPaymentRemainderDays());
			vo.setIsOverDuePaymentRemainder(opr.get().getIsOverDuePaymentRemainder());
			vo.setOverDuePaymentRemainderAfterDueDateOrUntilPaid(opr.get().getOverDuePaymentRemainderAfterDueDateOrUntilPaid());
			vo.setOverDuePaymentRemainderDays(opr.get().getOverDuePaymentRemainderDays());
			vo.setIsRemainderRecipients(opr.get().getIsRemainderRecipients());
			vo.setRemainderRecipients(opr.get().getRemainderRecipients());
		}
		return vo;
	}

	public CmsFeeSettingsVo getFeeSettingDueDateData(Long branchId, String paymentType) {
		Branch branch = new Branch();
		branch.setId(branchId);
		DueDate dueDate = new DueDate();
		dueDate.setBranch(branch);
		dueDate.setPaymentMethod(paymentType);

		CmsFeeSettingsVo vo = new CmsFeeSettingsVo();
		Example<DueDate> example = Example.of(dueDate);
		Optional<DueDate> odd = this.dueDateRepository.findOne(example);
		if(odd.isPresent()) {
			logger.debug("Getting data for due date");
			vo = CommonUtil.createCopyProperties(odd.get(), CmsFeeSettingsVo.class);
			vo.setDueDateId(odd.get().getId());
		}
		return vo;
	}
}
