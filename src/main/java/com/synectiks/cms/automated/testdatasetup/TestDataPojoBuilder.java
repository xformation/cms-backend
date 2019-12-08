package com.synectiks.cms.automated.testdatasetup;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;
import org.springframework.data.domain.Example;

import com.synectiks.cms.entities.AcademicExamSetting;
import com.synectiks.cms.entities.AcademicHistory;
import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.entities.AdmissionApplication;
import com.synectiks.cms.entities.AdmissionEnquiry;
import com.synectiks.cms.entities.AttendanceMaster;
import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.College;
import com.synectiks.cms.entities.Country;
import com.synectiks.cms.entities.Department;
import com.synectiks.cms.entities.DueDate;
import com.synectiks.cms.entities.Facility;
import com.synectiks.cms.entities.FeeCategory;
import com.synectiks.cms.entities.FeeDetails;
import com.synectiks.cms.entities.Holiday;
import com.synectiks.cms.entities.Invoice;
import com.synectiks.cms.entities.Lecture;
import com.synectiks.cms.entities.PaymentRemainder;
import com.synectiks.cms.entities.Section;
import com.synectiks.cms.entities.State;
import com.synectiks.cms.entities.Student;
import com.synectiks.cms.entities.StudentExamReport;
import com.synectiks.cms.entities.Subject;
import com.synectiks.cms.entities.Teacher;
import com.synectiks.cms.entities.Term;
import com.synectiks.cms.entities.TransportRoute;
import com.synectiks.cms.entities.TypeOfGrading;
import com.synectiks.cms.entities.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.entities.enumeration.BatchEnum;
import com.synectiks.cms.entities.enumeration.Bloodgroup;
import com.synectiks.cms.entities.enumeration.Caste;
import com.synectiks.cms.entities.enumeration.CourseEnum;
import com.synectiks.cms.entities.enumeration.EnquiryStatus;
import com.synectiks.cms.entities.enumeration.Frequency;
import com.synectiks.cms.entities.enumeration.Gender;
import com.synectiks.cms.entities.enumeration.InvoicePaymentStatus;
import com.synectiks.cms.entities.enumeration.ModeOfEnquiry;
import com.synectiks.cms.entities.enumeration.ModeOfPayment;
import com.synectiks.cms.entities.enumeration.RelationWithStudentEnum;
import com.synectiks.cms.entities.enumeration.Religion;
import com.synectiks.cms.entities.enumeration.SectionEnum;
import com.synectiks.cms.entities.enumeration.SemesterEnum;
import com.synectiks.cms.entities.enumeration.StaffType;
import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.entities.enumeration.StudentTypeEnum;
import com.synectiks.cms.entities.enumeration.SubTypeEnum;
import com.synectiks.cms.repositories.LectureRepository;
import com.synectiks.cms.repositories.StateRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;

public class TestDataPojoBuilder {
	SimpleDateFormat ddMMyyyyDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public Country createCountryPojo() {
		Country ct = new Country();
		ct.setCountryName("India");
		ct.setCountryCode("IND");
		ct.setIsdCode("+91");
		return ct;
	}

	State findStateByStateCode(String stateCode, StateRepository stateRepository) {
		State st = new State();
		st.setStateCode(stateCode);
		Example<State> example = Example.of(st);
		Optional<State> newSt = stateRepository.findOne(example);
		if (newSt.isPresent()) {
			return newSt.get();
		}
		return null;
	}

    public AcademicYear createAcademicYearPojo(Cell cell) {
        if (cell != null) {
            AcademicYear aYear = new AcademicYear();
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            if (cell != null && cell.getType() == CellType.NUMBER) {
                year = cell.asNumber().intValue();
            }
            aYear.setYear(String.valueOf(year));
            cal.set(year, 1, 1);
            aYear.setStartDate(LocalDate.now());
            cal.set(year, 12, 31);
            aYear.setEndDate(LocalDate.now());
            return aYear;
        }
        return null;
    }

    public Term createTermPojo(Cell cell) {
        Term tm = new Term();
        String dt[] = cell.asString().split("to");
        tm.setStartDate(LocalDate.now());
        tm.setEndDate(LocalDate.now());

        tm.setTermsDesc("" );
        tm.setTermStatus(Status.ACTIVE);
        System.out.println(tm.toString());
        return tm;
    }

	public College createCollegePojo(Cell cell) {
		College college = new College();
		college.setShortName(cell.asString());
		college.setInstructionInformation(cell.asString());
		college.setLogoPath("");
		college.setBackgroundImagePath("");
		college.setLogoFileName("");
		college.backgroundImageFileName("");
		return college;
	}

	public Branch createBranchPojo(Cell cell) {
		Branch branch = new Branch();
		branch.setBranchName(cell.asString());
		branch.setAddress1("Address 1");
		branch.setAddress2("Address 2");
		branch.setBranchHead("Kriss");
		return branch;
	}

	public Department createDepartmentPojo(Cell cell) {
		Department dt = new Department();
		dt.setName(cell.asString());
		dt.setDescription(cell.asString());
		dt.setDeptHead("Paul");
		return dt;
	}

	public Batch createBatchPojo(Cell cell) {
		Batch bt = new Batch();
		if (cell.asString().equalsIgnoreCase("FIRSTYEAR")) {
			bt.setBatch(BatchEnum.FIRSTYEAR);
		} else if (cell.asString().equalsIgnoreCase("SECONDYEAR")) {
			bt.setBatch(BatchEnum.SECONDYEAR);
		} else if (cell.asString().equalsIgnoreCase("THIRDYEAR")) {
			bt.setBatch(BatchEnum.THIRDYEAR);
		} else if (cell.asString().equalsIgnoreCase("FOURTHYEAR")) {
			bt.setBatch(BatchEnum.FOURTHYEAR);
		}
		return bt;
	}

	public Section createSectionPojo(Cell cell) {
		Section sec = new Section();
		if (cell.asString().equalsIgnoreCase("A")) {
			sec.setSection(SectionEnum.A);
		} else if (cell.asString().equalsIgnoreCase("B")) {
			sec.setSection(SectionEnum.B);
		} else if (cell.asString().equalsIgnoreCase("C")) {
			sec.setSection(SectionEnum.C);
		} else if (cell.asString().equalsIgnoreCase("D")) {
			sec.setSection(SectionEnum.D);
		}
		return sec;
	}

	public Student createStudentPojo(Cell cell) {
		Student st = new Student();
		st.setStudentName(cell.asString());
		st.setStudentMiddleName("");
		st.setStudentLastName("");
		st.setFatherName("F" + cell.asString());
		st.setFatherMiddleName("");
		st.setFatherLastName("");
		st.setMotherName("M" + cell.asString());
		st.setMotherMiddleName("");
		st.setMotherLastName("");
		st.setStudentAadharNo("1234");
		st.setDateOfBirth(LocalDate.now());
		st.setPlaceOfBirth("");
		st.setReligion(Religion.HINDU);
		st.setCaste(Caste.OBC);
		st.setSubCaste("");
		st.setAge(25);
		st.setSex(Gender.MALE);
		st.setBloodGroup(Bloodgroup.OPOSITIVE);
		st.setStudentLocalAddress("Local Address");
		st.setStudentPermanentAddress("Permanent Address");
		st.setPinCode("123456");
		st.setStudentPrimaryCellNumber("123456789");
		st.setStudentAlternateCellNumber("123456789");
		st.setStudentPrimaryEmailId(cell.asString()+"@gmail.com");
		st.setRelationWithStudent(RelationWithStudentEnum.FATHER);
		st.setEmergencyContactName("");
		st.setEmergencyContactMiddleName("");
		st.setEmergencyContactLastName("");
		st.setEmergencyContactCellNumber("123456789");
		st.setEmergencyContactEmailId("");
		st.setAdmissionNo("123456");
		st.setRollNo("");
		st.setStudentType(StudentTypeEnum.REGULAR);
		return st;
	}

	public Subject createSubjectPojo(Cell cell) {
		Subject sb = new Subject();
		sb.setSubjectCode(cell.asString());
		sb.setSubjectType(SubTypeEnum.COMMON);
		sb.setSubjectDesc(cell.asString());
		sb.setStatus(Status.ACTIVE);
		return sb;
	}

	public Teacher createTeacherPojo(Cell cell) {
		Teacher thr = new Teacher();
		thr.setTeacherName(cell.asString());
		thr.setTeacherMiddleName("M" + cell.asString());
		thr.setTeacherLastName("");
		thr.setFatherName("F" + cell.asString());
		thr.setFatherMiddleName("");
		thr.setFatherLastName("");
		thr.setMotherName("M" + cell.asString());
		thr.setMotherMiddleName("");
		thr.setMotherLastName("");
		thr.setAadharNo(1234L);
		thr.setDateOfBirth(LocalDate.now());
		thr.setPlaceOfBirth("");
		thr.setReligion(Religion.HINDU);
		thr.setCaste(Caste.GENERAL);
		thr.setSubCaste("");
		thr.setAge(25);
		thr.setSex(Gender.MALE);
		thr.setBloodGroup(Bloodgroup.OPOSITIVE);
		thr.setAddressLineOne("address one");
		thr.setAddressLineTwo("address two");
		thr.setAddressLineThree("address three");
		thr.setPincode(123456L);
		thr.setTeacherContactNumber("123456789");
		thr.setAlternateContactNumber("123456789");
		thr.setTeacherEmailAddress("");
		thr.setAlternateEmailAddress("");
		thr.setRelationWithStaff(RelationWithStudentEnum.FATHER);
		thr.setEmergencyContactName("");
		thr.setEmergencyContactMiddleName("");
		thr.setEmergencyContactLastName("");
		thr.setEmergencyContactNo("123456789");
		thr.setEmergencyContactEmailAddress("");
		thr.setUploadPhoto("");
		thr.setStatus(Status.ACTIVE);
		thr.setEmployeeId(123456L);
		thr.setDesignation("");
		thr.setStaffType(StaffType.TEACHING);
		return thr;
	}

	public LectureScheduleDTO getDto(String weekDay, Cell cell) {
		String time[] = cell.asString().split("-");

		LectureScheduleDTO dto = new LectureScheduleDTO();
		dto.setWeekDay(weekDay);
		dto.setStartTime(time[0].trim());
		dto.setEndTime(time[1].trim());

		return dto;
	}

	public List<Lecture> findLectureByAttendanceMaster(
			LectureRepository lectureRepository, AttendanceMaster am) {
		Lecture lc = new Lecture();
		lc.setAttendancemaster(am);
		Example<Lecture> example = Example.of(lc);
		List<Lecture> list = lectureRepository.findAll(example);
		return list;
	}

	public FeeCategory createFeeCategoryPojo(Cell cell) {
		FeeCategory feeCategory = new FeeCategory();
		feeCategory.setCategoryName(cell.asString());
		feeCategory.setDescription(" ");
		return feeCategory;
	}

	public Facility createFacilityPojo(Cell cell) {
		Facility facility = new Facility();
		facility.setName("Gym");
		facility.setStatus(Status.ACTIVE);
		// facility.setTransport(Status.ACTIVE);
		// facility.setMess(Status.ACTIVE);
		// facility.setGym(Status.ACTIVE);
		// facility.setCulturalClass(Status.DEACTIVE);
		// facility.setSports(Status.DEACTIVE);
		// facility.setSwimming(Status.ACTIVE);
		// facility.setExtraClass(Status.DEACTIVE);
		// facility.setHandicrafts(Status.ACTIVE);
		// facility.setStudent(student);
		return facility;
	}

	public TransportRoute createTransportRoutePojo(Cell cell) {
		TransportRoute transportRoute = new TransportRoute();
		transportRoute.setRouteName(cell.asString());
		transportRoute.setRouteDetails(cell.asString());
		transportRoute.setRouteMapUrl(" ");
		return transportRoute;
	}

	public FeeDetails createFeeDetailsPojo(Cell cell) {
		FeeDetails feeDetails = new FeeDetails();
		feeDetails.setFeeParticularsName(cell.asString());
		feeDetails.setFeeParticularDesc(cell.asString());
		feeDetails.setStudentType(StudentTypeEnum.REGULAR);
		feeDetails.setGender(Gender.MALE);
		feeDetails.setAmount(1234f);
		return feeDetails;
	}

	public AcademicExamSetting createAcademicExamSettingPojo(Cell cell,
			Department department, AcademicYear academicYear, Section section) {
		AcademicExamSetting academicExamSetting = new AcademicExamSetting();
		academicExamSetting.setExamName(cell.asString());
		academicExamSetting.setSemester(SemesterEnum.SEMESTER4);
//		academicExamSetting.setSubject(cell.asString());
		academicExamSetting.setExamDate(LocalDate.now());
		academicExamSetting.setStartTime(cell.asString());
		academicExamSetting.setEndTime(cell.asString());
		academicExamSetting.setTotal(60);
		academicExamSetting.setPassing(55);
		academicExamSetting.setActions(cell.asString());
		academicExamSetting.setDepartment(department);
		academicExamSetting.setAcademicyear(academicYear);

		return academicExamSetting;
	}

	public DueDate createDueDatePojo(Cell cell) {
		DueDate dueDate = new DueDate();
		dueDate.setId(1234l);
		dueDate.setPaymentMethod(cell.asString());
		dueDate.setInstallments(3243);
		dueDate.setDayDesc("");
		dueDate.setPaymentDay(4);
		dueDate.setFrequency(Frequency.WEEKLY);
		return dueDate;
	}

	public PaymentRemainder createPaymentRemainder(Cell cell) {
		PaymentRemainder paymentRemainder = new PaymentRemainder();
		paymentRemainder.setIsAutoRemainder(cell.asString());
		paymentRemainder.setIsFirstPaymentRemainder("");
		paymentRemainder.setFirstPaymentRemainderDays(21);
		paymentRemainder.setIsSecondPaymentRemainder("");
		paymentRemainder.setSecondPaymentRemainderDays(21);
		paymentRemainder.setIsOverDuePaymentRemainder("");
		paymentRemainder.setOverDuePaymentRemainderAfterDueDateOrUntilPaid("");
		paymentRemainder.setOverDuePaymentRemainderDays(22);
		paymentRemainder.setIsRemainderRecipients("");
		paymentRemainder.setRemainderRecipients("");
		return paymentRemainder;
	}

	public Holiday createHolidayPojo(Cell cell) {
		Holiday holiday = new Holiday();
		holiday.setHolidayDesc(cell.asString());
		holiday.setHolidayDate(LocalDate.now());
		holiday.setHolidayStatus(Status.ACTIVE);
		return holiday;
	}

	public Invoice createInvoicePojo() {
		Invoice invoice = new Invoice();
		invoice.setAmountPaid(3342l);
		invoice.setPaymentDate(LocalDate.now());
		invoice.setNextPaymentDate(LocalDate.now());
		invoice.setOutStandingAmount(345l);
		invoice.setModeOfPayment(ModeOfPayment.CARD);
		invoice.setChequeNumber(543l);
		invoice.setDemandDraftNumber(556l);
		invoice.setOnlineTxnRefNumber("");
		invoice.setPaymentStatus(InvoicePaymentStatus.PAID);
		invoice.setComments("");
		invoice.setUpdatedBy("");
		invoice.setUpdatedOn(LocalDate.now());
		return invoice;
	}

	public TypeOfGrading createTypeOfGradingPojo(Cell cell)
        {
		TypeOfGrading typeOfGrading = new TypeOfGrading();
		typeOfGrading.setId(1234l);
		typeOfGrading.setMinMarks(35);
		typeOfGrading.setMaxMarks(95);
		typeOfGrading.setGrades("");
		typeOfGrading.setGroupvalue(3342l);
		return typeOfGrading;
	}

	public StudentExamReport createStudentExamReportPojo(Cell cell,
			AcademicExamSetting academicExamSetting, Student student, Batch batch,
			TypeOfGrading typeofGrading, AcademicYear academicYear) {
		StudentExamReport studentExamReport = new StudentExamReport();
		studentExamReport.setMarksObtained(35);
		studentExamReport.setComments("");
        studentExamReport.setgOp(23);
		studentExamReport.setAcademicExamSetting(academicExamSetting);
		studentExamReport.setStudent(student);
		studentExamReport.setTypeOfGrading(typeofGrading);
		studentExamReport.setBatch(batch);
		studentExamReport.setAcademicyear(academicYear);
		return studentExamReport;
	}

	public AcademicHistory createAcademicHistoryPojo(Cell cell) {
		AcademicHistory ah = new AcademicHistory();
		ah.setQualification(cell.asString());
		ah.setYearOfPassing(cell.asString());
		ah.setInstitution("");
		ah.setUniversity("");
		ah.setEnrollmentNo(45L);
		ah.setScore(90L);
		ah.setPercentage(90);
		return ah;
	}

	public AdmissionApplication createAdmissionApplicationPojo(Cell cell) {
		AdmissionApplication aa = new AdmissionApplication();
		aa.setAdmissionStatus(AdmissionStatusEnum.ACCEPTED);
		aa.setCourse(CourseEnum.BTECH);
		aa.setAdmissionDate(LocalDate.now());
		aa.setComments("");
		return aa;
	}

	public AdmissionEnquiry createAdmissionEnquiryPojo(Cell cell) {
		AdmissionEnquiry ae = new AdmissionEnquiry();
		ae.setStudentName(cell.asString());
		ae.setContactNumber("");
		ae.setAlternateMobileNumber("");
		ae.setEmail("");
		ae.setCourseApplyingFor(CourseEnum.BTECH);
		ae.setModeOfEnquiry(ModeOfEnquiry.TELEPHONE);
		ae.setStatus(EnquiryStatus.DECLINED);
		ae.setDescription("");
		ae.setEnquiryDate(LocalDate.now());
		ae.setUpdatedOn(LocalDate.now());
		ae.setUpdatedBy("");
		return ae;
	}

}
