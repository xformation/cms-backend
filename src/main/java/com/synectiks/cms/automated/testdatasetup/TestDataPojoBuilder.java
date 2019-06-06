package com.synectiks.cms.automated.testdatasetup;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.data.domain.Example;

import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;

import static org.elasticsearch.search.rescore.QueryRescoreMode.Total;

public class TestDataPojoBuilder {
	SimpleDateFormat ddMMyyyyDateFormat = new SimpleDateFormat("dd-MM-yyyy");

	public Country createCountryPojo() {
		Country ct = new Country();
		ct.setCountryName("India");
		ct.setCountryCode("IND");
		ct.setIsdCode("+91");
		return ct;
	}

	public State createStatePojo(Row row, Country country) {
		State state = new State();
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if(cell.getColumnIndex() ==0 ) {
				state.setStateName(cell.getStringCellValue());
			}
			if(cell.getColumnIndex() ==1 ) {
				state.setDivisionType(cell.getStringCellValue());
			}
			if(cell.getColumnIndex() ==2 ) {
				state.setStateCode(cell.getStringCellValue());
			}
		}
		state.setCountry(country);
		return state;
	}

	public City createCityPojo(Row row, StateRepository stateRepository) {
		City city = new City();
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			if(cell.getColumnIndex() ==0 ) {
				city.setCityName(cell.getStringCellValue());
			}
			if(cell.getColumnIndex() ==1 ) {
				city.setCityCode(cell.getStringCellValue());
			}
			if(cell.getColumnIndex() ==2 ) {
				city.setStdCode(cell.getStringCellValue());
			}
			if(cell.getColumnIndex() == 3 ) {
				String stateCode = cell.getStringCellValue();
				State st = findStateByStateCode(stateCode, stateRepository);
				city.setState(st);
			}
		}
		return city;
	}

	private State findStateByStateCode(String stateCode, StateRepository stateRepository) {
		State st = new State();
		st.setStateCode(stateCode);
		Example<State> example = Example.of(st);
		Optional<State> newSt = stateRepository.findOne(example);
		if(newSt.isPresent()) {
			return newSt.get();
		}
		return null;
	}

	public AcademicYear createAcademicYearPojo(Cell cell) {
		AcademicYear ay = new AcademicYear();
		String jhiYear = null;
		switch(cell.getCellTypeEnum()) {
			case NUMERIC:
				jhiYear = String.valueOf(cell.getNumericCellValue()).substring(0, String.valueOf(cell.getNumericCellValue()).indexOf("."));
//				System.out.println("Row index : "+cell.getRowIndex()+", cell index : "+cell.getColumnIndex()+", NUMERIC CELL, Cell value : "+jhiYear);
				break;
			case STRING:
				jhiYear = cell.getStringCellValue();
//				System.out.println("Row index : "+cell.getRowIndex()+", cell index : "+cell.getColumnIndex()+", STRING CELL, Cell value : "+jhiYear);
				break;
			default:
				jhiYear = "2019";
//				System.out.println("Row index : "+cell.getRowIndex()+", cell index : "+cell.getColumnIndex()+", DEFAULT CELL");
				break;
		}

		ay.setYear(jhiYear);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(jhiYear), 01, 01);
		ay.setStartDate(new Date(cal.getTimeInMillis()));
		cal.set(Integer.parseInt(jhiYear), 12, 31);
		ay.setEndDate(new Date(cal.getTimeInMillis()));
		ay.setStatus(Status.ACTIVE);
		System.out.println(ay.toString());
		return ay;
	}

	public Term createTermPojo(Cell cell, AcademicYear ay) throws ParseException {
		Term tm = new Term();
		String dt [] = cell.getStringCellValue().split("to");
		tm.setStartDate(this.ddMMyyyyDateFormat.parse(dt[0].trim()));
		tm.setEndDate(this.ddMMyyyyDateFormat.parse(dt[1].trim()));
		tm.setTermsDesc("From "+dt[0].trim()+" to "+dt[1].trim());
		tm.setTermStatus(Status.ACTIVE);
		tm.setAcademicyear(ay);
		System.out.println(tm.toString());
		return tm;
	}

	public College createCollegePojo(Cell cell) {
		College college = new College();
		college.setShortName(cell.getStringCellValue());
		college.setInstructionInformation(cell.getStringCellValue());
		college.setLogoPath("");
		college.setBackgroundImagePath("");
		college.setLogoFileName("");
		college.backgroundImageFileName("");
		System.out.println(college.toString());
		return college;
	}

	public Branch createBranchPojo(Cell cell, College college, State state, City city) {
		Branch branch = new Branch();
		branch.setBranchName(cell.getStringCellValue());
		branch.setAddress1("Address 1");
		branch.setAddress2("Address 2");
		branch.setBranchHead("Kriss");
		branch.setCollege(college);
		branch.setState(state);
		branch.setCity(city);
		return branch;
	}

	public Department createDepartmentPojo(Cell cell, Branch branch, AcademicYear academicYear) {
		Department dt = new Department();
		dt.setName(cell.getStringCellValue());
		dt.setDescription(cell.getStringCellValue());
		dt.setDeptHead("Paul");
		dt.setBranch(branch);
		dt.setAcademicyear(academicYear);
		return dt;
	}

	public Batch createBatchPojo(Cell cell,  Department department) {
		Batch bt = new Batch();
		if(cell.getStringCellValue().equalsIgnoreCase("FIRSTYEAR")) {
			bt.setBatch(BatchEnum.FIRSTYEAR);
		}else if(cell.getStringCellValue().equalsIgnoreCase("SECONDYEAR")) {
			bt.setBatch(BatchEnum.SECONDYEAR);
		}else if(cell.getStringCellValue().equalsIgnoreCase("THIRDYEAR")) {
			bt.setBatch(BatchEnum.THIRDYEAR);
		}else if(cell.getStringCellValue().equalsIgnoreCase("FOURTHYEAR")) {
			bt.setBatch(BatchEnum.FOURTHYEAR);
		}
		bt.setDepartment(department);
		return bt;
	}

	public Section createSectionPojo(Cell cell, Batch batch) {
		Section sec = new Section();
		if(cell.getStringCellValue().equalsIgnoreCase("A")) {
			sec.setSection(SectionEnum.A);
		}else if(cell.getStringCellValue().equalsIgnoreCase("B")) {
			sec.setSection(SectionEnum.B);
		}else if(cell.getStringCellValue().equalsIgnoreCase("C")) {
			sec.setSection(SectionEnum.C);
		}else if(cell.getStringCellValue().equalsIgnoreCase("D")) {
			sec.setSection(SectionEnum.D);
		}
		sec.batch(batch);
		return sec;
	}

	public Student createStudentPojo(Cell cell, Department department, Batch batch,
			Section section, Branch branch, State state, City city, Country country) {
		Student st = new Student();
		st.setStudentName(cell.getStringCellValue());
		st.setStudentMiddleName("");
		st.setStudentLastName("");
		st.setFatherName("F"+cell.getStringCellValue());
		st.setFatherMiddleName("");
		st.setFatherLastName("");
		st.setMotherName("M"+cell.getStringCellValue());
		st.setMotherMiddleName("");
		st.setMotherLastName("");
		st.setAadharNo(1234L);
		st.setDateOfBirth(new Date());
		st.setPlaceOfBirth("");
		st.setReligion(Religion.HINDU);
		st.setCaste(Caste.OC);
		st.setSubCaste("");
		st.setAge(25);
		st.setSex(Gender.MALE);
		st.setBloodGroup(Bloodgroup.OPOSITIVE);
		st.setAddressLineOne("address one");
		st.setAddressLineTwo("address two");
		st.setAddressLineThree("address three");
		st.setTown(city.getCityName());
		st.setState(state.getStateName());
		st.setCountry(country.getCountryName());
		st.setPincode(123456L);
		st.setStudentContactNumber("123456789");
		st.setAlternateContactNumber("123456789");
		st.setStudentEmailAddress("");
		st.setAlternateEmailAddress("");
		st.setRelationWithStudent(RelationWithStudentEnum.FATHER);
		st.setEmergencyContactName("");
		st.setEmergencyContactMiddleName("");
		st.setEmergencyContactLastName("");
		st.setEmergencyContactNo("123456789");
		st.setEmergencyContactEmailAddress("");
		st.setUploadPhoto("");
		st.setAdmissionNo(123456L);
		st.setRollNo("");
		st.setStudentType(StudentTypeEnum.REGULAR);
		st.setDepartment(department);
		st.setBatch(batch);
		st.setSection(section);
		st.setBranch(branch);
		return st;
	}

	public Subject createSubjectPojo(Cell cell, Department department, Batch batch) {
		Subject sb = new Subject();
		sb.setSubjectCode(cell.getStringCellValue());
		sb.setSubjectType(SubTypeEnum.COMMON);
		sb.setSubjectDesc(cell.getStringCellValue());
		sb.setStatus(Status.ACTIVE);
		sb.setDepartment(department);
		sb.setBatch(batch);
		return sb;
	}

	public Teacher createTeacherPojo(Cell cell, Department department, Branch branch, State state, City city, Country country) {
		Teacher thr = new Teacher();
		thr.setTeacherName(cell.getStringCellValue());
		thr.setTeacherMiddleName("M"+cell.getStringCellValue());
		thr.setTeacherLastName("");
		thr.setFatherName("F"+cell.getStringCellValue());
		thr.setFatherMiddleName("");
		thr.setFatherLastName("");
		thr.setMotherName("M"+cell.getStringCellValue());
		thr.setMotherMiddleName("");
		thr.setMotherLastName("");
		thr.setAadharNo(1234L);
		thr.setDateOfBirth(new Date());
		thr.setPlaceOfBirth("");
		thr.setReligion(Religion.HINDU);
		thr.setCaste(Caste.OC);
		thr.setSubCaste("");
		thr.setAge(25);
		thr.setSex(Gender.MALE);
		thr.setBloodGroup(Bloodgroup.OPOSITIVE);
		thr.setAddressLineOne("address one");
		thr.setAddressLineTwo("address two");
		thr.setAddressLineThree("address three");
		thr.setTown(city.getCityName());
		thr.setState(state.getStateName());
		thr.setCountry(country.getCountryName());
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
		thr.setDepartment(department);
		thr.setBranch(branch);
		return thr;
	}

	public Teach createTeachPojo(Cell cell, Subject subject, Teacher teacher) {
		Teach th = new Teach();
		th.setDesc("Subject "+subject.getSubjectCode()+" and teacher "+teacher.getTeacherName());
		th.setSubject(subject);
		th.setTeacher(teacher);
		return th;
	}

	public AttendanceMaster createAttendanceMasterPojo(Cell cell,Batch batch, Section section, Teach teach, Subject subject, Teacher teacher) {
		AttendanceMaster am = new AttendanceMaster();
		am.setId(null);
		am.setDesc("Teacher "+teacher.getTeacherName()+ " is the attendance master of section "+section.getSection()+" and subject "+subject.getSubjectCode());
		am.setBatch(batch);
		am.setSection(section);
		am.setTeach(teach);
		return am;
	}

	public LectureScheduleDTO getDto(String weekDay, Cell cell, Subject sub, Teacher thr) {
		String time[] = cell.getStringCellValue().split("-");

		LectureScheduleDTO dto = new LectureScheduleDTO();
		dto.setWeekDay(weekDay);
		dto.setStartTime(time[0].trim());
		dto.setEndTime(time[1].trim());
		dto.setSubjectId(String.valueOf(sub.getId()));
		dto.setTeacherId(String.valueOf(thr.getId()));

		return dto;
	}

	public List<Lecture> findLectureByAttendanceMaster(LectureRepository lectureRepository, AttendanceMaster am) {
		Lecture lc = new Lecture();
		lc.setAttendancemaster(am);
		Example<Lecture> example = Example.of(lc);
		List<Lecture> list = lectureRepository.findAll(example);
		return list;
	}


    public FeeCategory createFeeCategoryPojo(Cell cell) {
        FeeCategory feeCategory = new FeeCategory();
        feeCategory.setCategoryName(cell.getStringCellValue());
        feeCategory.setDescription(" ");
        return feeCategory;
    }

    public Facility createFacilityPojo(Cell cell, AcademicYear academicYear,Branch branch,Student student) {
        Facility facility = new Facility();
        facility.setTransport(Status.ACTIVE);
        facility.setMess(Status.ACTIVE);
        facility.setGym(Status.ACTIVE);
        facility.setCulturalClass(Status.DEACTIVE);
        facility.setSports(Status.DEACTIVE);
        facility.setSwimming(Status.ACTIVE);
        facility.setExtraClass(Status.DEACTIVE);
        facility.setHandicrafts(Status.ACTIVE);
        facility.setAcademicYear(academicYear );
        facility.setBranch(branch);
        facility.setStudent(student);
        return facility;
    }

    public TransportRoute createTransportRoutePojo(Cell cell) {
        TransportRoute transportRoute = new TransportRoute();
        transportRoute.setRouteName(cell.getStringCellValue());
        transportRoute.setRouteDetails(cell.getStringCellValue());
        transportRoute.setRouteMapUrl(" ");
        return transportRoute;
    }
    public FeeDetails createFeeDetailsPojo(Cell cell,FeeCategory feeCategory,Batch batch,Facility facility, TransportRoute transportRoute,College college,Department department,Branch branch,AcademicYear academicYear) {
        FeeDetails feeDetails  = new FeeDetails();
        feeDetails.setFeeParticularsName(cell.getStringCellValue());
        feeDetails.setFeeParticularDesc(cell.getStringCellValue());
        feeDetails.setStudentType(StudentTypeEnum.REGULAR);
        feeDetails.setGender(Gender.MALE);
        feeDetails.setAmount(1234l);
        feeDetails.setFeeCategory(feeCategory);
        feeDetails.setBatch(batch);
        feeDetails.setFacility(facility);
        feeDetails.setTransportRoute(transportRoute);
        feeDetails.setCollege(college);
        feeDetails.setDepartment(department);
        feeDetails.setBranch(branch);
        feeDetails.setAcademicYear(academicYear);

        return feeDetails ;
    }

    public AcademicExamSetting createAcademicExamSettingPojo(Cell cell,Department department,AcademicYear academicYear,Section section, Batch batch) {
        AcademicExamSetting aes  = new AcademicExamSetting();
        aes.setExamType(cell.getStringCellValue());
        aes.setSemester(SemesterEnum.SEMESTER4);
        aes.setSubject("");
        aes.setExamDate(new Date());
        aes.setDay("");
        aes.setDuration("");
        aes.setStartTime("");
        aes.setEndTime("");
        aes.setGradeType(GradeType.GRADE);
        aes.setTotal(100);
        aes.setPassing(35);
        aes.setActions("");
        aes.setDepartment(department);
        aes.setAcademicyear(academicYear);
        aes.setSection(section);
        aes.setBatch(batch);

        return aes ;
    }

    public AcademicHistory createAcademicHistoryPojo(Cell cell,Student student) {
        AcademicHistory ah  = new AcademicHistory();
        ah.setQualification(cell.getStringCellValue());
        ah.setYearOfPassing("");
        ah.setInstitution("");
        ah.setUniversity("");
        ah.setEnrollmentNo(20312L);
        ah.setScore(86L);
        ah.setPercentage(83);
        ah.setStudent(student);

        return ah ;
    }

    public AdmissionApplication createAdmissionApplicationPojo(Cell cell,Student student) {
        AdmissionApplication aap  = new AdmissionApplication();
        aap.setAdmissionStatus(AdmissionStatusEnum.ACCEPTED);
        aap.setCourse(CourseEnum.BTECH);
        aap.setAdmissionDate(new Date());
        aap.setComments("");
        aap.setStudent(student);

        return aap ;
    }

    public AdmissionEnquiry createAdmissionEnquiryPojo(Cell cell,Branch branch, AdmissionApplication admissionApplication) {
        AdmissionEnquiry ae  = new AdmissionEnquiry();
        ae.setStudentName(cell.getStringCellValue());
        ae.setMobileNumber("");
        ae.setAlternateMobileNumber("");
        ae.setEmail("");
        ae.setCourseApplyingFor(CourseEnum.BTECH);
        ae.setModeOfEnquiry(ModeOfEnquiry.TELEPHONE);
        ae.setStatus(EnquiryStatus.RECEIVED);
        ae.setDescription("");
        ae.setEnquiryDate(new Date());
        ae.setUpdatedOn(new Date());
        ae.setUpdatedBy("");
        ae.setBranch(branch);
        ae.setAdmissionApplication(admissionApplication);


        return ae ;
    }

    public DueDate createDueDatePojo(Cell cell,College college,Branch branch) {
        DueDate ddt  = new DueDate();
        ddt.setPaymentMethod(cell.getStringCellValue());
        ddt.setInstallments(3);
        ddt.setDayDesc("");
        ddt.setPaymentDate(new Date());
        ddt.setFrequency(Frequency.ANNUALLY);
        ddt.setCollege(college);
        ddt.setBranch(branch);

        return ddt ;
    }

    public PaymentRemainder createPaymentRemainderPojo(Cell cell,DueDate dueDate,College college,Branch branch) {
        PaymentRemainder pr  = new PaymentRemainder();
        pr.setFeeRemainder(Status.ACTIVE);
        pr.setNoticeDay(3);
        pr.setOverDueRemainder(Status.DEACTIVE);
        pr.setRemainderRecipients("");
        pr.setDueDate(dueDate);
        pr.setCollege(college);
        pr.setBranch(branch);

        return pr ;
    }

    public Holiday createHolidayPojo(Cell cell,AcademicYear academicYear) {
        Holiday hy  = new Holiday();
        hy.setHolidayDesc(cell.getStringCellValue());
        hy.setHolidayDate(new Date());
        hy.setHolidayStatus(Status.ACTIVE);
        hy.setAcademicyear(academicYear);

        return hy ;
    }

    public Invoice createInvoicePojo(Cell cell,FeeCategory feeCategory,FeeDetails feeDetails,DueDate dueDate, PaymentRemainder paymentRemainder, College college,Branch branch,Student student,AcademicYear academicYear) {
        Invoice inv  = new Invoice();
        inv.setInvoiceNumber(cell.getStringCellValue());
        inv.setAmountPaid(142512L);
        inv.setPaymentDate(new Date());
        inv.setNextPaymentDate(new Date());
        inv.setOutStandingAmount (14252L);
        inv.setModeOfPayment(ModeOfPayment.CARD);
        inv.setChequeNumber (51241254L);
        inv.setDemandDraftNumber (12541251L);
        inv.setOnlineTxnRefNumber ("");
        inv.setPaymentStatus (InvoicePaymentStatus.PAID);
        inv.setComments ("");
        inv.setUpdatedBy ("");
        inv.setUpdatedOn (new Date());
        inv.setFeeCategory (feeCategory);
        inv.setFeeDetails (feeDetails);
        inv.setDueDate(dueDate);
        inv.setPaymentRemainder(paymentRemainder);
        inv.setCollege(college);
        inv.setBranch(branch);
        inv.setStudent(student);
        inv.setAcademicYear(academicYear);

        return inv ;
    }

    public StudentExamReport createStudentExamReportPojo(Cell cell,AcademicExamSetting academicExamSetting,Student student,TypeOfGrading typeOfGrading, Batch batch, AcademicYear academicYear) {
        StudentExamReport sep  = new StudentExamReport();
        sep.setMarksObtained(78);
        sep.setComments("");
        sep.setCreatedOn(new Date());
        sep.setCreatedBy("");
        sep.setUpdatedOn(new Date());
        sep.setUpdatedBy("");
        sep.setAcademicExamSetting(academicExamSetting);
        sep.setStudent(student);
        sep.setTypeOfGrading(typeOfGrading);
        sep.setBatch(batch);
        sep.setAcademicyear(academicYear);

        return sep ;
    }

    public TypeOfGrading createTypeOfGradingPojo(Cell cell,AcademicExamSetting academicExamSetting) {
        TypeOfGrading tog  = new TypeOfGrading();
        tog.setMinMarks(78);
        tog.setMaxMarks(100);
        tog.setGrades(GradesEnum.A);
        tog.setAcademicExamSetting(academicExamSetting);

        return tog ;
    }

//    public StudentAttendance createStudentAttendanceData (Student student, Lecture lecture) {
//        StudentAttendance sa = new StudentAttendance();
//        sa.attendanceStatus(AttendanceStatusEnum.PRESENT);
//        sa.setStudent(student);
//        sa.setLecture(lecture);
//        return sa;
//	}


}







