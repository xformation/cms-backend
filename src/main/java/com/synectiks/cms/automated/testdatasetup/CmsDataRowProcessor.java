/**
 * 
 */
package com.synectiks.cms.automated.testdatasetup;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.synectiks.cms.service.util.DateFormatUtil;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.CellType;
import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.AcademicHistory;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.DueDate;
import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Invoice;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.PaymentRemainder;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.domain.StudentFacilityLink;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.domain.TypeOfGrading;
import com.synectiks.cms.domain.enumeration.AttendanceStatusEnum;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.repository.AcademicHistoryRepository;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.DueDateRepository;
import com.synectiks.cms.repository.FacilityRepository;
import com.synectiks.cms.repository.FeeCategoryRepository;
import com.synectiks.cms.repository.FeeDetailsRepository;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.InvoiceRepository;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.PaymentRemainderRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentExamReportRepository;
import com.synectiks.cms.repository.StudentFacilityLinkRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.repository.TransportRouteRepository;
import com.synectiks.cms.repository.TypeOfGradingRepository;
import com.synectiks.cms.service.dto.LectureScheduleDTO;

/**
 * @author Rajesh Upadhyay
 */
@Service
public class CmsDataRowProcessor {

	private static final Logger logger = LoggerFactory.getLogger(CmsDataRowProcessor.class);
	private static final TestDataPojoBuilder pojoBuilder = new TestDataPojoBuilder();
	private static final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private AcademicHistoryRepository acHistoryRepo;
	@Autowired
	private AcademicYearRepository acYearRepo;
	@Autowired
	private AdmissionApplicationRepository admAppRepo;
	@Autowired
	private AdmissionEnquiryRepository admEnqRepo;
	@Autowired
	private AttendanceMasterRepository attndMstrRepo;
	@Autowired
	private BranchRepository branchRepo;
	@Autowired
	private BatchRepository batchRepo;
	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private CollegeRepository collegeRepo;
	@Autowired
	private CommonService commonService;
	@Autowired
	private DepartmentRepository deptRepo;
	@Autowired
	private DueDateRepository dueDtRepo;
	@Autowired
	private FacilityRepository facilityRepo;
	@Autowired
	private FeeCategoryRepository feeCatRepo;
	@Autowired
	private FeeDetailsRepository feeDetailRepo;
	@Autowired
	private HolidayRepository holidayRepo;
	@Autowired
	private InvoiceRepository invoiceRepo;
	@Autowired
	private LectureService lectureService;
	@Autowired
	private LectureRepository lectureRepository;
	@Autowired
	private PaymentRemainderRepository pmtRemRepo;
	@Autowired
	private SectionRepository sectionRepo;
	@Autowired
	private StudentExamReportRepository serRepo;
	@Autowired
	private StudentFacilityLinkRepository sflRepo;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private StudentAttendanceRepository stAttenRepo;
	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private SubjectRepository subjectRepo;
	@Autowired
	private TeacherRepository teacherRepo;
	@Autowired
	private TeachRepository teachRepo;
	@Autowired
	private TermRepository termRepo;
	@Autowired
	private TypeOfGradingRepository togRepo;
	@Autowired
	private TransportRouteRepository transRepo;

	// private List<AcademicYear> acYears = null;

	private AcademicExamSetting acExSet = null;
	private AcademicHistory acHistory = null;
	private AcademicYear aYear = null;
	private AdmissionApplication admApp = null;
	private AdmissionEnquiry admEnquiry = null;
	private AttendanceMaster attndMstr = null;
	private Batch batch = null;
	private Branch branch = null;
	private City city = null;
	private College college = null;
	private Country country = null;
	private Department department = null;
	private DueDate dueDate = null;
	private Facility facility = null;
	private FeeCategory feeCat = null;
	private FeeDetails feeDetail = null;
	private Holiday holiday = null;
	private Invoice invoice = null;
	private PaymentRemainder pmtRmdr = null;
	private Section section = null;
	private State state = null;
	private Student student = null;
	private StudentExamReport ser = null;
	private StudentFacilityLink sfl = null;
	private Subject subject = null;
	private Teach teach = null;
	private Teacher teacher = null;
	private Term term = null;
	private TransportRoute transRoute = null;
	private TypeOfGrading tpOfGrd = null;

	public static <T> T saveOrGet(JpaRepository<T, Long> repo, T obj) {
		try {
			if (repo.exists(Example.of(obj))) {
				obj = repo.findOne(Example.of(obj)).get();
			} else {
				obj = repo.save(obj);
			}
		} catch (Exception e) {
			logger.error("Error in saving ", e);
		}
		return obj;
	}

	private State findStateWithStateName(String stateName) {
		State st = new State();
		st.setStateName(stateName);
		Example<State> example = Example.of(st);
		Optional<State> newTh = this.stateRepository.findOne(example);
		if (newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}

	private City findCiyt(State st, String cityName) {
		City ct = new City();
		ct.setState(st);
		ct.setCityName(cityName);
		Example<City> example = Example.of(ct);
		Optional<City> newTh = this.cityRepository.findOne(example);
		if (newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}

	public void reset() {
		// this.acYears = null;
	}

	private void finishRow() {
		this.acExSet = null;
		this.acHistory = null;
		this.aYear = null;
		this.admApp = null;
		this.admEnquiry = null;
		this.attndMstr = null;
		this.batch = null;
		this.branch = null;
		this.city = null;
		this.college = null;
		this.country = null;
		this.department = null;
		this.dueDate = null;
		this.facility = null;
		this.feeCat = null;
		this.feeDetail = null;
		this.holiday = null;
		this.invoice = null;
		this.pmtRmdr = null;
		this.section = null;
		this.state = null;
		this.student = null;
		this.ser = null;
		this.sfl = null;
		this.subject = null;
		this.teach = null;
		this.teacher = null;
		this.term = null;
		this.transRoute = null;
		this.tpOfGrd = null;
	}

	public void process(Row row, Country cntry, State state, City city) {
		this.process(row, cntry, state, city, -1, null);
	}

	public void process(Row row, Country cntry, State state,
			City city, int col, CallbackHandler handler) {
		logger.info("Process row with: " + cntry + ", " + state + ", " + city + ", " + col);
		this.country = cntry;
		this.state = state;
		this.city = city;
		finishRow();
		if (row != null) {
			row.forEach(cell -> {
				if (col != -1) {
					processCmsCell(cell);
				} else if (cell.getColumnIndex() == col) {
					if (handler != null) {
						handler.handle(cell);
					} else {
						processCmsCell(cell);
					}
				}
			});
		}
	}

	private void processCmsCell(Cell cell) {
		if (cell != null && cell.getValue() != null) {
			logger.info("Cell: " + cell.getColumnIndex() + ", " + cell.asString());
			switch (cell.getColumnIndex()) {
			case 0:
				saveAcademicYear(cell);
				break;
			case 1:
				saveTermData(cell);
				break;
			case 2:
				saveCollegeData(cell);
				break;
			case 3:
				saveBranchData(cell);
				break;
			case 4:
				saveDepartmentData(cell);
				break;
			case 5:
				saveBatchData(cell);
				break;
			case 6:
				saveSectionData(cell);
				break;
			case 7:
				saveStudentData(cell);
				break;
			case 8:
				saveSubjectData(cell);
				break;
			case 9:
				saveTeacherTeachAttendanceMasterData(cell);
				break;
			case 10:
				saveLectureSchedule("MONDAY", cell, Calendar.MONDAY);
				break;
			case 11:
				saveLectureSchedule("TUESDAY", cell, Calendar.TUESDAY);
				break;
			case 12:
				saveLectureSchedule("WEDNESDAY", cell, Calendar.WEDNESDAY);
				break;
			case 13:
				saveLectureSchedule("THURSDAY", cell, Calendar.THURSDAY);
				break;
			case 14:
				saveLectureSchedule("FRIDAY", cell, Calendar.FRIDAY);
				break;
			case 15:
				saveLectureSchedule("SATURDAY", cell, Calendar.SATURDAY);
				break;
			case 16:
				saveStudentAttendanceData();
				break;
			case 17:
				saveFeeCategory(cell);
				break;
			case 18:
				saveFacility(cell);
				break;
			case 19:
				saveTransportRoute(cell);
				break;
			case 20:
				saveFeeDetails(cell);
				break;
			case 22:
				saveAcademicHistory(cell);
				break;
			case 23:
				saveAdmissionApplication(cell);
				break;
			case 24:
				saveAdmissionEnquiry(cell);
				break;
			case 25:
				saveDueDate(cell);
				break;
			case 26:
				savePaymentRemainder(cell);
				break;
			case 27:
				saveHoliday(cell);
				break;
			case 28:
				saveInvoice(cell);
				break;
			case 29:
				saveTypeOfGrading(cell);
				break;
			case 30:
				saveStudentExamReport(cell);
				break;
			case 31:
				saveStudentFacilityLink(cell);
			}
		}
	}

	private void saveAcademicYear(Cell cell) {
		this.aYear = pojoBuilder.createAcademicYearPojo(cell);
		this.aYear = saveOrGet(this.acYearRepo, this.aYear);
	}

	private void saveTermData(Cell cell) {
		this.term = pojoBuilder.createTermPojo(cell);
		this.term.setAcademicyear(this.aYear);
		this.term = saveOrGet(this.termRepo, this.term);
		
	}

	private void saveCollegeData(Cell cell) {
		this.college = pojoBuilder.createCollegePojo(cell);
		this.college = saveOrGet(this.collegeRepo, this.college);
		
	}

	private void saveBranchData(Cell cell) {
		this.branch = pojoBuilder.createBranchPojo(cell);
		this.branch.setCollege(college);
		this.branch.setState(state);
		this.branch.setCity(city);
		this.branch = saveOrGet(this.branchRepo, this.branch);
	}

	private void saveDepartmentData(Cell cell) {
		this.department = pojoBuilder.createDepartmentPojo(cell);
		this.department.setBranch(this.branch);
		this.department.setAcademicyear(this.aYear);
		this.department = saveOrGet(this.deptRepo, this.department);
	}

	private void saveBatchData(Cell cell) {
		this.batch = pojoBuilder.createBatchPojo(cell);
		this.batch.setDepartment(this.department);
		this.batch = saveOrGet(this.batchRepo, this.batch);
	}

	private void saveSectionData(Cell cell) {
		this.section = pojoBuilder.createSectionPojo(cell);
		this.section.setBatch(this.batch);
		this.section = saveOrGet(this.sectionRepo, this.section);
	}

	private void saveStudentData(Cell cell) {
        State st = findStateWithStateName("Telangana");
        City ct = findCiyt(st, "Hyderabad");
        this.student = pojoBuilder.createStudentPojo(cell);
        this.student.setTown(ct.getCityName());
        this.student.setState(st.getStateName());
        this.student.setCountry(country.getCountryName());
        this.student.setDepartment(department);
        this.student.setBatch(batch);
        this.student.setSection(section);
        this.student.setBranch(branch);
        this.student = saveOrGet(this.studentRepo, this.student);
		
	}

	private void saveSubjectData(Cell cell) {
		this.subject = pojoBuilder.createSubjectPojo(cell);
		this.subject.setBatch(this.batch);
		this.subject.setDepartment(this.department);
		this.subject = saveOrGet(this.subjectRepo, this.subject);
	}

	private void saveTeacherTeachAttendanceMasterData(Cell cell) {
		this.teacher = pojoBuilder.createTeacherPojo(cell);
		this.teacher.setTown(city.getCityName());
		this.teacher.setState(state.getStateName());
		this.teacher.setCountry(country.getCountryName());
		this.teacher.setDepartment(department);
		this.teacher.setBranch(branch);
		this.teacher = saveOrGet(this.teacherRepo, this.teacher);
		// Teach
		this.teach = new Teach();
		this.teach.setDesc("Subject " + subject.getSubjectCode() + " and teacher "
				+ teacher.getTeacherName());
		this.teach.setSubject(this.subject);
		this.teach.setTeacher(this.teacher);
		this.teach = saveOrGet(this.teachRepo, this.teach);
		// Attendance
		this.attndMstr  = new AttendanceMaster();
		this.attndMstr.setDesc("Teacher " + teacher.getTeacherName()
				+ " is the attendance master of section " + section.getSection()
				+ " and subject " + subject.getSubjectCode());
		this.attndMstr.setBatch(this.batch);
		this.attndMstr.setSection(this.section);
		this.attndMstr.setTeach(this.teach);
		this.attndMstr = saveOrGet(this.attndMstrRepo, this.attndMstr);
	}

	private void saveLectureSchedule(String weekday, Cell cell, int day) {
		if (cell != null && cell.getType() == CellType.STRING) {
			LectureScheduleDTO dto = pojoBuilder.getDto(weekday, cell);
			dto.setSubjectId(String.valueOf(this.subject.getId()));
			dto.setTeacherId(String.valueOf(this.subject.getId()));
			LectureScheduleFilter filter = new LectureScheduleFilter();
			filter.setTermId(String.valueOf(this.term.getId()));
			filter.setAcademicYear(this.aYear.getYear());
			filter.setSectionId(String.valueOf(this.section.getId()));
			filter.setBatchId(String.valueOf(this.batch.getId()));
			
			Lecture lectue = new Lecture();
			lectue.setAttendancemaster(this.attndMstr);
			lectue.setStartTime(dto.getStartTime());
			lectue.setEndTime(dto.getEndTime());

			List<Date> dtList = filterDates(day);

			dtList.forEach(dt -> {
				lectue.setLecDate(DateFormatUtil.convertLocalDateFromUtilDate(dt));
				if (!this.lectureRepository.exists(Example.of(lectue))) {
					try {
						this.lectureService.createLectureSchedule(
								dt, getObjAsString(dto), weekday, filter);
					} catch (ParseException e) {
						logger.error(e.getMessage(), e);
					}
				}
			});
			
		} else {
			logger.warn("Invalid lecture schedule cell data type");
		}
	}

	private static String[] getObjAsString(LectureScheduleDTO dto) {
		try {
			return new String[] { mapper.writeValueAsString(dto) };
		} catch (JsonProcessingException e) {
			// ignore it
		}
		return null;
	}

	private List<Date> filterDates(int day) {
		List<Date> dateList = this.lectureService.createDates(this.term);
		List<Holiday> holidayList = null;
		
		holidayList = this.commonService.getHolidayList(Optional.of(this.aYear));
		
		this.lectureService.filterHolidays(holidayList, dateList);
		this.lectureService.filterSundays(dateList);
		List<Date> list = this.lectureService.filterDateListOnDayOfweek(dateList, day);
		return list;
	}

	private void saveStudentAttendanceData() {
		List<Lecture> lectures = pojoBuilder.findLectureByAttendanceMaster(
				this.lectureRepository, this.attndMstr);
		StudentAttendance sa = new StudentAttendance();
		sa.attendanceStatus(AttendanceStatusEnum.PRESENT);
		sa.setStudent(student);
		lectures.forEach(lectuer -> {
			sa.setLecture(lectuer);
			saveOrGet(this.stAttenRepo, sa);
		});
	}

	private void saveFeeCategory(Cell cell) {
		this.feeCat = pojoBuilder.createFeeCategoryPojo(cell);
		this.feeCat = saveOrGet(this.feeCatRepo, this.feeCat);
	}

	private void saveFacility(Cell cell) {
		this.facility = pojoBuilder.createFacilityPojo(cell);
		this.facility.setAcademicYear(this.aYear);
		this.facility.setBranch(this.branch);
		this.facility = saveOrGet(this.facilityRepo, this.facility);
	}

	private void saveTransportRoute(Cell cell) {
		this.transRoute = pojoBuilder.createTransportRoutePojo(cell);
		this.transRoute = saveOrGet(this.transRepo, this.transRoute);
		
	}

	private void saveFeeDetails(Cell cell) {
		this.feeDetail = pojoBuilder.createFeeDetailsPojo(cell);
		this.feeDetail.setFeeCategory(this.feeCat);
		this.feeDetail.setBatch(this.batch);
		this.feeDetail.setFacility(this.facility);
		this.feeDetail.setTransportRoute(this.transRoute);
//		this.feeDetail.setCollege(this.college);
		this.feeDetail.setDepartment(this.department);
//		this.feeDetail.setBranch(this.branch);
//		this.feeDetail.setAcademicYear(this.aYear);
		this.feeDetail = saveOrGet(this.feeDetailRepo, this.feeDetail);
	}

	private void saveAcademicHistory(Cell cell) {
		this.acHistory = pojoBuilder.createAcademicHistoryPojo(cell);
		this.acHistory.setStudent(this.student);
		this.acHistory = saveOrGet(this.acHistoryRepo, this.acHistory);
	}

	private void saveAdmissionApplication(Cell cell) {
		this.admApp = pojoBuilder.createAdmissionApplicationPojo(cell);
		this.admApp.setAcademicyear(this.aYear);
		this.admApp = saveOrGet(this.admAppRepo, this.admApp);
	}

	private void saveAdmissionEnquiry(Cell cell) {
		this.admEnquiry = pojoBuilder.createAdmissionEnquiryPojo(cell);
		this.admEnquiry.setBranch(this.branch);
		this.admEnquiry = saveOrGet(this.admEnqRepo, this.admEnquiry);
	}

	private void saveDueDate(Cell cell) {
		this.dueDate = pojoBuilder.createDueDatePojo(cell);
		this.dueDate.setCollege(this.college);
		this.dueDate.setBranch(this.branch);
		this.dueDate = saveOrGet(this.dueDtRepo, this.dueDate);
	}

	private void savePaymentRemainder(Cell cell) {
		this.pmtRmdr = pojoBuilder.createPaymentRemainder(cell);
		this.pmtRmdr.setCollege(this.college);
		this.pmtRmdr.setBranch(this.branch);
		this.pmtRmdr = saveOrGet(this.pmtRemRepo, this.pmtRmdr);
	}

	private void saveHoliday(Cell cell) {
		this.holiday = pojoBuilder.createHolidayPojo(cell);
		this.holiday.setAcademicyear(aYear);
		this.holiday = saveOrGet(this.holidayRepo, this.holiday);
	}

	private void saveInvoice(Cell cell) {
		this.invoice = pojoBuilder.createInvoicePojo();
		this.invoice.setInvoiceNumber(cell.asString());
		this.invoice.setFeeCategory(this.feeCat);
		this.invoice.setFeeDetails(this.feeDetail);
		this.invoice.setDueDate(this.dueDate);
		this.invoice.setPaymentRemainder(this.pmtRmdr);
		this.invoice.setCollege(this.college);
		this.invoice.setBranch(this.branch);
		this.invoice.setStudent(this.student);
		this.invoice.setAcademicYear(this.aYear);
		this.invoice = saveOrGet(this.invoiceRepo, this.invoice);
	}

	private void saveTypeOfGrading(Cell cell) {
		this.tpOfGrd = new TypeOfGrading();
		this.tpOfGrd.setId(1234l);
		this.tpOfGrd.setMinMarks(35);
		this.tpOfGrd.setMaxMarks(95);
		this.tpOfGrd.setGrades(cell.asString());
		this.tpOfGrd.setGroupvalue(1234l);
		this.tpOfGrd = saveOrGet(this.togRepo, this.tpOfGrd);
	}

	private void saveStudentExamReport(Cell cell) {
		this.ser = new StudentExamReport();
		this.ser.setAcademicExamSetting(this.acExSet);
		this.ser.setAcademicyear(this.aYear);
		this.ser.setBatch(this.batch);
		this.ser.setMarksObtained(35);
		this.ser.setStudent(this.student);
		this.ser.setTypeOfGrading(this.tpOfGrd);
		this.ser.setComments(cell.asString());
		this.ser = saveOrGet(this.serRepo, this.ser);
	}

	private void saveStudentFacilityLink(Cell cell) {
		if (this.student != null && this.facility != null) {
			this.sfl = new StudentFacilityLink();
			this.sfl.setStudent(this.student);
			this.sfl.setFacility(this.facility);
			this.sfl.setLinkDesc(cell.getText());
			this.sfl = saveOrGet(this.sflRepo, this.sfl);
		}
	}

	public static interface CallbackHandler {

		public void handle(Cell cell);

	}
}
