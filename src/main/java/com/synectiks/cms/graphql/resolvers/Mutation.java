package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectMutationPayload;
import com.synectiks.cms.filter.academicsubject.AcademicSubjectProcessor;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.filter.lecture.LectureScheduleProcessor;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceUpdateFilter;
import com.synectiks.cms.graphql.types.AcademicYear.*;
import com.synectiks.cms.graphql.types.AttendanceMaster.*;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.*;
import com.synectiks.cms.graphql.types.BankAccounts.*;
import com.synectiks.cms.graphql.types.Batch.*;
import com.synectiks.cms.graphql.types.Branch.*;
import com.synectiks.cms.graphql.types.City.*;
import com.synectiks.cms.graphql.types.College.*;
import com.synectiks.cms.graphql.types.CompetitiveExam.*;
import com.synectiks.cms.graphql.types.Country.*;
import com.synectiks.cms.graphql.types.Department.*;
import com.synectiks.cms.graphql.types.Documents.*;
import com.synectiks.cms.graphql.types.DueDate.*;
import com.synectiks.cms.graphql.types.Facility.*;
import com.synectiks.cms.graphql.types.FeeCategory.*;
import com.synectiks.cms.graphql.types.FeeDetails.*;
import com.synectiks.cms.graphql.types.Holiday.*;
import com.synectiks.cms.graphql.types.Invoice.*;
import com.synectiks.cms.graphql.types.LateFee.*;
import com.synectiks.cms.graphql.types.Lecture.*;
import com.synectiks.cms.graphql.types.LegalEntity.*;
import com.synectiks.cms.graphql.types.PaymentRemainder.*;
import com.synectiks.cms.graphql.types.Section.*;
import com.synectiks.cms.graphql.types.State.*;
import com.synectiks.cms.graphql.types.Student.*;
import com.synectiks.cms.graphql.types.StudentAttendance.*;
import com.synectiks.cms.graphql.types.Subject.*;
import com.synectiks.cms.graphql.types.Teach.*;
import com.synectiks.cms.graphql.types.Teacher.*;
import com.synectiks.cms.graphql.types.Term.*;
import com.synectiks.cms.graphql.types.TransportRoute.*;
import com.synectiks.cms.repository.*;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;


@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);

    private final AcademicYearRepository academicYearRepository;
    private final AttendanceMasterRepository attendanceMasterRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
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
    @PersistenceContext
    private EntityManager entityManager;

//    @Autowired
//    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    @Autowired
    private LectureScheduleProcessor lectureScheduleProcessor;

    @Autowired
    private AcademicSubjectProcessor academicSubjectProcessor;

    public Mutation(CountryRepository countryRepository, LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository, TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository, CityRepository cityRepository, StateRepository stateRepository, FeeCategoryRepository feeCategoryRepository, FacilityRepository facilityRepository, TransportRouteRepository transportRouteRepository, FeeDetailsRepository feeDetailsRepository, DueDateRepository dueDateRepository, PaymentRemainderRepository paymentRemainderRepository, LateFeeRepository lateFeeRepository, InvoiceRepository invoiceRepository, CompetitiveExamRepository competitiveExamRepository, DocumentsRepository documentsRepository) {
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

    public AddStudentPayload addStudent(AddStudentInput addStudentInput) {
        final Section section = sectionRepository.findById(addStudentInput.getSectionId()).get();
        final Branch branch = branchRepository.findById(addStudentInput.getBranchId()).get();
        final Department department = departmentRepository.findById(addStudentInput.getDepartmentId()).get();
        final Batch batch = batchRepository.findById(addStudentInput.getBatchId()).get();
        final Student student = new Student();
        student.setStudentName(addStudentInput.getStudentName());
        student.setStudentMiddleName(addStudentInput.getStudentMiddleName());
        student.setStudentLastName(addStudentInput.getStudentLastName());
        student.setFatherName(addStudentInput.getFatherName());
        student.setFatherMiddleName(addStudentInput.getFatherMiddleName());
        student.setFatherLastName(addStudentInput.getFatherLastName());
        student.setMotherName(addStudentInput.getMotherName());
        student.setMotherMiddleName(addStudentInput.getMotherMiddleName());
        student.setMotherLastName(addStudentInput.getMotherLastName());
        student.setAadharNo(addStudentInput.getAadharNo());
        student.setDateOfBirth(addStudentInput.getDateOfBirth());
        student.setPlaceOfBirth(addStudentInput.getPlaceOfBirth());
        student.setReligion(addStudentInput.getReligion());
        student.setCaste(addStudentInput.getCaste());
        student.setSubCaste(addStudentInput.getSubCaste());
        student.setAge(addStudentInput.getAge());
        student.setSex(addStudentInput.getSex());
        student.setBloodGroup(addStudentInput.getBloodGroup());
        student.setAddressLineOne(addStudentInput.getAddressLineOne());
        student.setAddressLineTwo(addStudentInput.getAddressLineTwo());
        student.setAddressLineThree(addStudentInput.getAddressLineThree());
        student.setTown(addStudentInput.getTown());
        student.setState(addStudentInput.getState());
        student.setCountry(addStudentInput.getCountry());
        student.setPincode(addStudentInput.getPincode());
        student.setStudentContactNumber(addStudentInput.getStudentContactNumber());
        student.setAlternateContactNumber(addStudentInput.getAlternateContactNumber());
        student.setStudentEmailAddress(addStudentInput.getStudentEmailAddress());
        student.setAlternateEmailAddress(addStudentInput.getAlternateEmailAddress());
        student.setRelationWithStudent(addStudentInput.getRelationWithStudent());
        student.setEmergencyContactName(addStudentInput.getEmergencyContactName());
        student.setEmergencyContactMiddleName(addStudentInput.getEmergencyContactMiddleName());
        student.setEmergencyContactLastName(addStudentInput.getEmergencyContactLastName());
        student.setEmergencyContactNo(addStudentInput.getEmergencyContactNo());
        student.setEmergencyContactEmailAddress(addStudentInput.getEmergencyContactEmailAddress());
        student.setUploadPhoto(addStudentInput.getUploadPhoto());
        student.setAdmissionNo(addStudentInput.getAdmissionNo());
        student.setRollNo(addStudentInput.getRollNo());
        student.setStudentType(addStudentInput.getStudentType());
        student.setBatch(batch);
        student.setSection(section);
        student.setBranch(branch);
        student.setDepartment(department);
        studentRepository.save(student);
        return new AddStudentPayload(student);
    }

    public UpdateStudentPayload updateStudent(UpdateStudentInput updateStudentInput) {
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
            student.setDateOfBirth(updateStudentInput.getDateOfBirth());
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

        if (updateStudentInput.getUploadPhoto() != null) {
            student.setUploadPhoto(updateStudentInput.getUploadPhoto());
        }
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

        final Documents documents = new Documents();
        documents.setStudent(student);
        documents.setUpload(addDocumentsInput.getUpload());
        documents.setDocumentName(addDocumentsInput.getDocumentName());
        documentsRepository.save(documents);
        return new AddDocumentsPayload(documents);
    }


    public UpdateDocumentsPayload updateDocuments(UpdateDocumentsInput updateDocumentsInput) {
        final Documents documents = documentsRepository.findById(updateDocumentsInput.getId()).get();

        if (updateDocumentsInput.getUpload() != null){
            documents.setUpload(updateDocumentsInput.getUpload());
        }
        if(updateDocumentsInput.getDocumentName() != null){
            documents.setDocumentName(updateDocumentsInput.getDocumentName());
        }

        if (updateDocumentsInput.getStudentId() != null) {
            final Student student = studentRepository.findById(updateDocumentsInput.getStudentId()).get();
            documents.setStudent(student);
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
        college.setLogo(addCollegeInput.getLogo());
        college.setBackgroundImage(addCollegeInput.getBackgroundImage());
        college.setInstructionInformation(addCollegeInput.getInstructionInformation());

        collegeRepository.save(college);

        return new AddCollegePayload(college);
    }

    public UpdateCollegePayload updateCollege(UpdateCollegeInput updateCollegeInput) {
        College college = collegeRepository.findById(updateCollegeInput.getId()).get();
        if (updateCollegeInput.getShortName() != null) {
            college.setShortName(updateCollegeInput.getShortName());
        }

        if (updateCollegeInput.getLogo() != null) {
            college.setLogo(updateCollegeInput.getLogo());
        }

        if (updateCollegeInput.getBackgroundImage() != null) {
            college.setBackgroundImage(updateCollegeInput.getBackgroundImage());
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
        teacher.setDateOfBirth(addTeacherInput.getDateOfBirth());
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
            teacher.setDateOfBirth(updateTeacherInput.getDateOfBirth());
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
        legalEntity.setLogo(addLegalEntityInput.getLogo());
        legalEntity.setLegalNameOfTheCollege(addLegalEntityInput.getLegalNameOfTheCollege());
        legalEntity.setTypeOfCollege(addLegalEntityInput.getTypeOfCollege());
        legalEntity.setRegisteredOfficeAddress1(addLegalEntityInput.getRegisteredOfficeAddress1());
        legalEntity.setRegisteredOfficeAddress2(addLegalEntityInput.getRegisteredOfficeAddress2());
        legalEntity.setRegisteredOfficeAddress3(addLegalEntityInput.getRegisteredOfficeAddress3());
        legalEntity.setRegisteredOfficeAddress4(addLegalEntityInput.getRegisteredOfficeAddress4());
        legalEntity.setRegisteredOfficeAddress5(addLegalEntityInput.getRegisteredOfficeAddress5());
        legalEntity.setDateOfIncorporation(addLegalEntityInput.getDateOfIncorporation());
        legalEntity.setCollegeIdentificationNumber(addLegalEntityInput.getCollegeIdentificationNumber());
        legalEntity.setPan(addLegalEntityInput.getPan());
        legalEntity.setTan(addLegalEntityInput.getTan());
        legalEntity.setTanCircleNumber(addLegalEntityInput.getTanCircleNumber());
        legalEntity.setCitTdsLocation(addLegalEntityInput.getCitTdsLocation());
        legalEntity.setFormSignatory(addLegalEntityInput.getFormSignatory());
        legalEntity.setPfNumber(addLegalEntityInput.getPfNumber());
        legalEntity.setPfRegistrationDate(addLegalEntityInput.getPfRegistrationDate());
        legalEntity.setPfSignatory(addLegalEntityInput.getPfSignatory());
        legalEntity.setEsiNumber(addLegalEntityInput.getEsiNumber());
        legalEntity.setEsiRegistrationDate(addLegalEntityInput.getEsiRegistrationDate());
        legalEntity.setEsiSignatory(addLegalEntityInput.getEsiSignatory());
        legalEntity.setPtNumber(addLegalEntityInput.getPtNumber());
        legalEntity.setPtRegistrationDate(addLegalEntityInput.getPtRegistrationDate());
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
        if (updateLegalEntityInput.getLogo() != null) {
            legalEntity.setLogo(updateLegalEntityInput.getLogo());
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
            legalEntity.setDateOfIncorporation(updateLegalEntityInput.getDateOfIncorporation());
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
            legalEntity.setPfRegistrationDate(updateLegalEntityInput.getPfRegistrationDate());
        }
        if (updateLegalEntityInput.getPfSignatory() != null) {
            legalEntity.setPfSignatory(updateLegalEntityInput.getPfSignatory());
        }

        if (updateLegalEntityInput.getEsiNumber() != null) {
            legalEntity.setEsiNumber(updateLegalEntityInput.getEsiNumber());
        }
        if (updateLegalEntityInput.getEsiRegistrationDate() != null) {
            legalEntity.setEsiRegistrationDate(updateLegalEntityInput.getEsiRegistrationDate());
        }
        if (updateLegalEntityInput.getEsiSignatory() != null) {
            legalEntity.setEsiSignatory(updateLegalEntityInput.getEsiSignatory());
        }

        if (updateLegalEntityInput.getPtRegistrationDate() != null) {
            legalEntity.setPtRegistrationDate(updateLegalEntityInput.getPtRegistrationDate());
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

    public RemoveLegalEntityPayload removeLegalEntity(RemoveLegalEntityInput removeLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(removeLegalEntityInput.getLegalEntityId()).get();
        legalEntityRepository.delete(legalEntity);
        return new RemoveLegalEntityPayload(Lists.newArrayList(legalEntityRepository.findAll()));
    }

    public AddAcademicYearPayload addAcademicYear(AddAcademicYearInput addAcademicYearInput) {
        final AcademicYear academicYear = new AcademicYear();
        academicYear.setYear(addAcademicYearInput.getYear());
        academicYear.setStartDate(addAcademicYearInput.getStartDate());
        academicYear.setEndDate(addAcademicYearInput.getEndDate());
        academicYearRepository.save(academicYear);
        return new AddAcademicYearPayload(academicYear);
    }


    public UpdateAcademicYearPayload updateAcademicYear(UpdateAcademicYearInput updateAcademicYearInput) {
        AcademicYear academicYear = academicYearRepository.findById(updateAcademicYearInput.getId()).get();
        if (updateAcademicYearInput.getYear() != null) {
            academicYear.setYear(updateAcademicYearInput.getYear());
        }
        if (updateAcademicYearInput.getStartDate() != null) {
            academicYear.setStartDate(updateAcademicYearInput.getStartDate());
        }

        if (updateAcademicYearInput.getEndDate() != null) {
            academicYear.setEndDate(updateAcademicYearInput.getEndDate());
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
        holiday.setHolidayDate(addHolidayInput.getHolidayDate());
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
            holiday.setHolidayDate(updateHolidayInput.getHolidayDate());
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
        term.setStartDate(addTermInput.getStartDate());
        term.setEndDate(addTermInput.getEndDate());
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
            term.setStartDate(updateTermInput.getStartDate());
        }

        if (updateTermInput.getEndDate() != null) {
            term.setEndDate(updateTermInput.getEndDate());
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
        lecture.setLecDate(addLectureInput.getLecDate());
        lecture.setLastUpdatedOn(addLectureInput.getLastUpdatedOn());
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
            lecture.setLecDate(updateLectureInput.getLecDate());
        }
        if (updateLectureInput.getLastUpdatedBy() != null) {
            lecture.setLastUpdatedBy(updateLectureInput.getLastUpdatedBy());
        }
        if (updateLectureInput.getLastUpdatedOn() != null) {
            lecture.setLastUpdatedOn(updateLectureInput.getLastUpdatedOn());
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

    public AddFeeCategoryPayload addFeeCategory(AddFeeCategoryInput addFeeCategoryInput) {
        final FeeCategory feeCategory = new FeeCategory();
        feeCategory.setCategoryName(addFeeCategoryInput.getCategoryName());
        feeCategory.setDescription(addFeeCategoryInput.getDescription());
        FeeCategory fe = feeCategoryRepository.save(feeCategory);
        return new AddFeeCategoryPayload(fe);
    }

    public UpdateFeeCategoryPayload updateFeeCategory(UpdateFeeCategoryInput updateFeeCategoryInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(updateFeeCategoryInput.getId()).get();
        if (updateFeeCategoryInput.getCategoryName() != null) {
            feeCategory.setCategoryName(updateFeeCategoryInput.getCategoryName());
        }
        if (updateFeeCategoryInput.getDescription() != null) {
            feeCategory.setDescription(updateFeeCategoryInput.getDescription());
        }

        feeCategoryRepository.save(feeCategory);

        return new UpdateFeeCategoryPayload(feeCategory);
    }

    public RemoveFeeCategoryPayload removeFeeCategory(RemoveFeeCategoryInput removeFeeCategoryInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(removeFeeCategoryInput.getFeeCategoryId()).get();
        feeCategoryRepository.delete(feeCategory);
        return new RemoveFeeCategoryPayload(Lists.newArrayList(feeCategoryRepository.findAll()));
    }

    public AddFeeDetailsPayload addFeeDetails(AddFeeDetailsInput addFeeDetailsInput) {
        FeeCategory feeCategory = feeCategoryRepository.findById(addFeeDetailsInput.getFeeCategoryId()).get();
        Batch batch = batchRepository.findById(addFeeDetailsInput.getBatchId()).get();
        Facility facility = facilityRepository.findById(addFeeDetailsInput.getFacilityId()).get();
        TransportRoute transportRoute = transportRouteRepository.findById(addFeeDetailsInput.getTransportRouteId()).get();
        Department department = departmentRepository.findById(addFeeDetailsInput.getDepartmentId()).get();
        Branch branch = branchRepository.findById(addFeeDetailsInput.getBranchId()).get();
        College college = collegeRepository.findById(addFeeDetailsInput.getCollegeId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addFeeDetailsInput.getAcademicyearId()).get();
        final FeeDetails feeDetails = new FeeDetails();
        feeDetails.setFeeParticularsName(addFeeDetailsInput.getFeeParticularsName());
        feeDetails.setFeeParticularDesc(addFeeDetailsInput.getFeeParticularDesc());
        feeDetails.setStudentType(addFeeDetailsInput.getStudentType());
        feeDetails.setGender(addFeeDetailsInput.getGender());
        feeDetails.setAmount(addFeeDetailsInput.getAmount());
        feeDetails.setFeeCategory(feeCategory);
        feeDetails.setBatch(batch);
        feeDetails.setFacility(facility);
        feeDetails.setTransportRoute(transportRoute);
        feeDetails.setDepartment(department);
        feeDetails.setBranch(branch);
        feeDetails.setAcademicYear(academicYear);
        feeDetails.setCollege(college);
        feeDetailsRepository.save(feeDetails);

        return new AddFeeDetailsPayload(feeDetails);
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
        if (updateFeeDetailsInput.getDepartmentId() != null) {
            Department department = departmentRepository.findById(updateFeeDetailsInput.getDepartmentId()).get();
            feeDetails.setDepartment(department);
        }
        if (updateFeeDetailsInput.getBranchId() != null) {
            Branch branch = branchRepository.findById(updateFeeDetailsInput.getBranchId()).get();
            feeDetails.setBranch(branch);
        }
        if (updateFeeDetailsInput.getCollegeId() != null) {
            College college = collegeRepository.findById(updateFeeDetailsInput.getCollegeId()).get();
            feeDetails.setCollege(college);
        }
        if (updateFeeDetailsInput.getAcademicyearId() != null) {
            AcademicYear academicYear = academicYearRepository.findById(updateFeeDetailsInput.getAcademicyearId()).get();
            feeDetails.setAcademicYear(academicYear);
        }
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


    public AddFacilityPayload addFacility(AddFacilityInput addFacilityInput) {
        final Branch branch = branchRepository.findById(addFacilityInput.getBranchId()).get();
        final Student student = studentRepository.findById(addFacilityInput.getStudentId()).get();
        AcademicYear academicYear = academicYearRepository.findById(addFacilityInput.getAcademicyearId()).get();
        final Facility facility = new Facility();
        facility.setTransport(addFacilityInput.getTransport());
        facility.setMess(addFacilityInput.getMess());
        facility.setGym(addFacilityInput.getGym());
        facility.setCulturalClass(addFacilityInput.getCulturalClass());
        facility.setLibrary(addFacilityInput.getLibrary());
        facility.setSports(addFacilityInput.getSports());
        facility.setSwimming(addFacilityInput.getSwimming());
        facility.setExtraClass(addFacilityInput.getExtraClass());
        facility.setHandicrafts(addFacilityInput.getHandicrafts());
        facility.setAcademicYear(academicYear);
        facility.setStudent(student);
        facility.setBranch(branch);
        facilityRepository.save(facility);

        return new AddFacilityPayload(facility);
    }

    public UpdateFacilityPayload updateFacility(UpdateFacilityInput updateFacilityInput) {
        Facility facility = facilityRepository.findById(updateFacilityInput.getId()).get();

        if (updateFacilityInput.getTransport() != null) {
            facility.setTransport(updateFacilityInput.getTransport());
        }
        if (updateFacilityInput.getMess() != null) {
            facility.setMess(updateFacilityInput.getMess());
        }
        if (updateFacilityInput.getGym() != null) {
            facility.setGym(updateFacilityInput.getGym());
        }
        if (updateFacilityInput.getCulturalClass() != null) {
            facility.setCulturalClass(updateFacilityInput.getCulturalClass());
        }
        if (updateFacilityInput.getLibrary() != null) {
            facility.setLibrary(updateFacilityInput.getLibrary());
        }
        if (updateFacilityInput.getSports() != null) {
            facility.setSports(updateFacilityInput.getSports());
        }
        if (updateFacilityInput.getSwimming() != null) {
            facility.setSwimming(updateFacilityInput.getSwimming());
        }
        if (updateFacilityInput.getExtraClass() != null) {
            facility.setExtraClass(updateFacilityInput.getExtraClass());
        }
        if (updateFacilityInput.getHandicrafts() != null) {
            facility.setHandicrafts(updateFacilityInput.getHandicrafts());
        }

        if(updateFacilityInput.getStudentId() != null) {
     	Student student = studentRepository.findById(updateFacilityInput.getStudentId()).get();
     	facility.setStudent(student);
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
        final DueDate dueDate = new DueDate();
        dueDate.setPaymentMethod(addDueDateInput.getPaymentMethod());
        dueDate.setInstallments(addDueDateInput.getInstallments());
        dueDate.setDayDesc(addDueDateInput.getDayDesc());
        dueDate.setFrequency(addDueDateInput.getFrequency());
        dueDate.setCollege(college);
        dueDate.setBranch(branch);
        dueDateRepository.save(dueDate);
        return new AddDueDatePayload(dueDate);
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
        final LateFee lateFee = new LateFee();
        lateFee.setAssignLateFee(addLateFeeInput.getAssignLateFee());
        lateFee.setLateFeeDays(addLateFeeInput.getLateFeeDays());
        lateFee.setFixed(addLateFeeInput.getFixed());
        lateFee.setPercentage(addLateFeeInput.getPercentage());
        lateFee.setFixedCharges(addLateFeeInput.getFixedCharges());
        lateFee.setPercentCharges(addLateFeeInput.getPercentCharges());
        lateFee.setLateFeeAssignmentFrequency(addLateFeeInput.getLateFeeAssignmentFrequency());
        lateFee.setCollege(college);
        lateFee.setBranch(branch);
        lateFeeRepository.save(lateFee);
        return new AddLateFeePayload(lateFee);
    }

    public UpdateLateFeePayload updateLateFee(UpdateLateFeeInput updateLateFeeInput) {
        LateFee lateFee = lateFeeRepository.findById(updateLateFeeInput.getId()).get();
        if (updateLateFeeInput.getAssignLateFee() != null) {
            lateFee.setAssignLateFee(updateLateFeeInput.getAssignLateFee());
        }
        if (updateLateFeeInput.getLateFeeDays() != null) {
            lateFee.setLateFeeDays(updateLateFeeInput.getLateFeeDays());
        }
        if (updateLateFeeInput.getFixed() != null) {
            lateFee.setFixed(updateLateFeeInput.getFixed());
        }
        if (updateLateFeeInput.getPercentage() != null) {
            lateFee.setPercentage(updateLateFeeInput.getPercentage());
        }
        if (updateLateFeeInput.getFixedCharges() != null) {
            lateFee.setFixedCharges(updateLateFeeInput.getFixedCharges());
        }
        if (updateLateFeeInput.getPercentCharges() != null) {
            lateFee.setPercentCharges(updateLateFeeInput.getPercentCharges());
        }
        if (updateLateFeeInput.getLateFeeAssignmentFrequency() != null) {
            lateFee.setLateFeeAssignmentFrequency(updateLateFeeInput.getLateFeeAssignmentFrequency());
        }

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
        final DueDate dueDate = dueDateRepository.findById(addPaymentRemainderInput.getDueDateId()).get();
        final College college = collegeRepository.findById(addPaymentRemainderInput.getCollegeId()).get();
        final Branch branch = branchRepository.findById(addPaymentRemainderInput.getBranchId()).get();
        final PaymentRemainder paymentRemainder = new PaymentRemainder();
        paymentRemainder.setFeeRemainder(addPaymentRemainderInput.getFeeRemainder());
        paymentRemainder.setNoticeDay(addPaymentRemainderInput.getNoticeDay());
        paymentRemainder.setOverDueRemainder(addPaymentRemainderInput.getOverDueRemainder());
        paymentRemainder.setRemainderRecipients(addPaymentRemainderInput.getRemainderRecipients());
        paymentRemainder.setDueDate(dueDate);
        paymentRemainder.setCollege(college);
        paymentRemainder.setBranch(branch);
        paymentRemainderRepository.save(paymentRemainder);
        return new AddPaymentRemainderPayload(paymentRemainder);

    }

    public UpdatePaymentRemainderPayload updatePaymentRemainder(UpdatePaymentRemainderInput updatePaymentRemainderInput) {
        PaymentRemainder paymentRemainder = paymentRemainderRepository.findById(updatePaymentRemainderInput.getId()).get();
        if (updatePaymentRemainderInput.getFeeRemainder() != null) {
            paymentRemainder.setFeeRemainder((updatePaymentRemainderInput.getFeeRemainder()));
        }
        if (updatePaymentRemainderInput.getNoticeDay() != null) {
            paymentRemainder.setNoticeDay((updatePaymentRemainderInput.getNoticeDay()));
        }
        if (updatePaymentRemainderInput.getOverDueRemainder() != null) {
            paymentRemainder.setOverDueRemainder((updatePaymentRemainderInput.getOverDueRemainder()));
        }
        if (updatePaymentRemainderInput.getRemainderRecipients() != null) {
            paymentRemainder.setRemainderRecipients((updatePaymentRemainderInput.getRemainderRecipients()));
        }

        if (updatePaymentRemainderInput.getDueDateId() != null) {
            final DueDate dueDate = dueDateRepository.findById(updatePaymentRemainderInput.getDueDateId()).get();
            paymentRemainder.setDueDate(dueDate);
        }
        if (updatePaymentRemainderInput.getCollegeId() != null) {
            final College college = collegeRepository.findById(updatePaymentRemainderInput.getCollegeId()).get();
            paymentRemainder.setCollege(college);
        }
        if (updatePaymentRemainderInput.getBranchId() != null) {
            final Branch branch = branchRepository.findById(updatePaymentRemainderInput.getBranchId()).get();
            paymentRemainder.setBranch(branch);
        }
        paymentRemainderRepository.save(paymentRemainder);
        return new UpdatePaymentRemainderPayload(paymentRemainder);
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
        invoice.setPaymentDate(addInvoiceInput.getPaymentDate());
        invoice.setNextPaymentDate(addInvoiceInput.getNextPaymentDate());
        invoice.setOutStandingAmount(addInvoiceInput.getOutStandingAmount());
        invoice.setModeOfPayment(addInvoiceInput.getModeOfPayment());
        invoice.setChequeNumber(addInvoiceInput.getChequeNumber());
        invoice.setDemandDraftNumber(addInvoiceInput.getDemandDraftNumber());
        invoice.setOnlineTxnRefNumber(addInvoiceInput.getOnlineTxnRefNumber());
        invoice.setPaymentStatus(addInvoiceInput.getPaymentStatus());
        invoice.setComments(addInvoiceInput.getComments());
        invoice.setUpdatedBy(addInvoiceInput.getUpdatedBy());
        invoice.setUpdatedOn(addInvoiceInput.getUpdatedOn());
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
            invoice.setPaymentDate(updateInvoiceInput.getPaymentDate());
        }

        if (updateInvoiceInput.getNextPaymentDate() != null) {
            invoice.setNextPaymentDate(updateInvoiceInput.getNextPaymentDate());
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
            invoice.setUpdatedOn(updateInvoiceInput.getUpdatedOn());
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
    @Transactional
    public QueryResult updateStudenceAttendanceData(StudentAttendanceUpdateFilter filter) throws JSONException, ParseException {
        System.out.println("Input contents : " + filter.getStudentIds());
        String sql = "update student_attendance set attendance_status= ? where student_id  = ?  and lecture_id = ? ";
        Query query1 = this.entityManager.createNativeQuery(sql);
        StringTokenizer token = new StringTokenizer(filter.getStudentIds(), ",");
        QueryResult res = new QueryResult();
        res.setStatusCode(0);
        res.setStatusDesc("Records updated successfully.");
        try {
            while (token.hasMoreTokens()) {
                query1.setParameter(1, "ABSENT");
                query1.setParameter(2, Integer.parseInt(token.nextToken()));
                query1.setParameter(3, filter.getLectureId());
                query1.executeUpdate();
            }
        } catch (Exception e) {
            logger.error("Exception. There is some error in updating the student attendance records. ", e);
            res.setStatusCode(1);
            res.setStatusDesc("There is some error in updating the student attendance records.");
        }

        return res;

    }

    public String subtractDays(String dt, int days) throws ParseException {
        String dtFormat = "yyyy-MM-dd";
        Date date = new SimpleDateFormat(dtFormat).parse(dt);
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.add(Calendar.DATE, -days);
        String newDt = new SimpleDateFormat(dtFormat).format(cal.getTime());
        return newDt;
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
}
