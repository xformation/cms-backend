
package com.synectiks.cms.automated.testdatasetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synectiks.cms.business.dto.LectureScheduleDTO;
import com.synectiks.cms.business.service.LectureService;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AttendanceMaster;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.domain.Department;
import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.domain.Section;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.filter.lecture.LectureScheduleFilter;
import com.synectiks.cms.filter.lecture.LectureScheduleInput;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.AttendanceMasterRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.LectureRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentAttendanceRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.TeachRepository;
import com.synectiks.cms.repository.TeacherRepository;
import com.synectiks.cms.repository.TermRepository;

@RestController
@RequestMapping("/api")
public class CmsAutomatedTestDataSetupProcessor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String DATA_FILE_XLSX = "cms_test_data_setup.xlsx";
	private static final String DATA_FILE_XLS = "cms_test_data_setup.xls";
	private TestDataPojoBuilder testDataPojoBuilder;
	
	private Country country = null;
	private State state = null;
	private City city = null;
	private AcademicYear academicYear = null;
	private Term term = null;
	private College college = null;
	private Branch branch = null;
	private Department department = null;
	private Batch batch = null;
	private Section section = null;
	private Student student = null;
	private Subject subject = null;
	private Teacher teacher = null;
	private Teach teach = null;
	private AttendanceMaster attendanceMaster = null;
	private StudentAttendance studentAttendance = null;
	
	@Autowired
	private AcademicYearRepository academicYearRepository;
	
	@Autowired
	private TermRepository termRepository;
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private TeachRepository teachRepository;
	
	@Autowired
	private AttendanceMasterRepository attendanceMasterRepository;
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private LectureRepository lectureRepository;
	
	@Autowired
	private StudentAttendanceRepository studentAttendanceRepository;
	
	@Autowired
	ServletContext context;
	
//	public static void main(String args[]) throws IOException, ParseException{
//		new CmsAutomatedTestDataSetupProcessor().init();
//	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/create")
	public void createCmsTestData() throws IOException, ParseException{
		try {
			execute();
		}catch(Exception e) {
			logger.error("Exception in creating CMS test data ",e);
		}
	}
	
	private void execute() throws IOException, ParseException, InterruptedException {
		this.testDataPojoBuilder = new TestDataPojoBuilder();
		FileInputStream fis = null;
		try {
			File f = getFile();
			fis = loadFile(f);
			Workbook wb = getWorkbook(fis);
			saveCountryData();
			saveStateData(wb);
			saveCityData(wb);
			saveCmsData(wb);
			logger.info("TEST DATA LOADING COMPLETED......");
		}finally {
			if(fis != null) fis.close();
		}
	}
	private File getFile() throws FileNotFoundException {
		String r = Paths.get("").toAbsolutePath()+"/src/test/"+DATA_FILE_XLSX;
		File f = new File(r);
    	if(!f.exists()) {
    		r = Paths.get("").toAbsolutePath()+"/src/test/"+DATA_FILE_XLS;
    		f = new File(r);
    		if(!f.exists()) {
    			throw new FileNotFoundException("File not found : "+r);
    		}
    	}
		return f;
	}
	private FileInputStream loadFile(File f) throws FileNotFoundException {
		return new FileInputStream(f);
	}
	
	private Workbook getWorkbook(FileInputStream fis) throws IOException {
		Workbook workbook = new XSSFWorkbook(fis);
		return workbook;
	}
	
	private Sheet getSheet(Workbook workbook, String sheetName) {
		return workbook.getSheet(sheetName);
	}
	
	private void saveCountryData() {
		logger.debug("Saving country data started.");
		this.country = this.testDataPojoBuilder.createCountryPojo();
		try {
			Example<Country> example = Example.of(this.country);
			if(this.countryRepository.exists(example) == false) {
				this.country = this.countryRepository.save(this.country);
			}else {
				this.country = this.countryRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving country data. "+e.getMessage());
		}
		logger.debug("Saving country data completed.");
	}
	
	private void saveStateData(Workbook workbook) {
		logger.debug("Saving state data started.");
		Sheet sheet = getSheet(workbook, "state");
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if(row.getRowNum() <= 0) continue; // First row is a header row. skipping it.
			this.state = this.testDataPojoBuilder.createStatePojo(row, this.country);
			try {
				Example<State> example = Example.of(this.state);
				if(this.stateRepository.exists(example) == false) {
					this.state = this.stateRepository.save(this.state);
				}
			}catch(Exception e) {
				logger.warn("Exception in saving state data. "+e.getMessage());
			}
		}
		logger.debug("Saving state data completed.");
	}
	
	private void saveCityData(Workbook workbook) {
		logger.debug("Saving city data started.");
		Sheet sheet = getSheet(workbook, "city");
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if(row.getRowNum() <= 0) continue; // First row is a header row. skipping it.
			this.city = this.testDataPojoBuilder.createCityPojo(row, this.stateRepository);
			try {
				Example<City> example = Example.of(this.city);
				if(this.cityRepository.exists(example) == false) {
					this.city = this.cityRepository.save(this.city);
				}
			}catch(Exception e) {
				logger.warn("Exception in saving city data. "+e.getMessage());
			}
		}
		logger.debug("Saving city data completed.");
	}
	
	private void saveCmsData(Workbook workbook) throws ParseException, InterruptedException {
		Sheet sheet = getSheet(workbook, "cmstestdata");
		logger.debug(sheet.getSheetName());
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			logger.debug("Row number : "+row.getRowNum());
			if(row.getRowNum() <= 0) continue; // First row is a header row. skipping it.
			
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == 0 ) {
					saveAcademicYear(cell);
				}
				if(cell.getColumnIndex() == 1 ) {
					saveTermData(cell);
				}
				if(cell.getColumnIndex() == 2 ) {
					saveCollegeData(cell);
				}
				if(cell.getColumnIndex() == 3 ) {
					saveBranchData(cell);
				}
				if(cell.getColumnIndex() == 4 ) {
					saveDepartmentData(cell);
				}
				if(cell.getColumnIndex() == 5 ) {
					saveBatchData(cell);
				}
				if(cell.getColumnIndex() == 6 ) {
					saveSectionData(cell);
				}
				if(cell.getColumnIndex() == 7 ) {
					saveStudentData(cell);
				}
				if(cell.getColumnIndex() == 8 ) {
					saveSubjectData(cell);
				}
				if(cell.getColumnIndex() == 9 ) {
					saveTeacherTeachAttendanceMasterData(cell);
				}
				if(cell.getColumnIndex() == 10 ) {
					saveLectureSchedule("MONDAY", cell, Calendar.MONDAY);
				}
				if(cell.getColumnIndex() == 11 ) {
					saveLectureSchedule("TUESDAY", cell, Calendar.TUESDAY);
				}
				if(cell.getColumnIndex() == 12 ) {
					saveLectureSchedule("WEDNESDAY", cell, Calendar.WEDNESDAY);
				}
				if(cell.getColumnIndex() == 13 ) {
					saveLectureSchedule("THURSDAY", cell, Calendar.THURSDAY);
				}
				if(cell.getColumnIndex() == 14 ) {
					saveLectureSchedule("FRIDAY", cell, Calendar.FRIDAY);
				}
				if(cell.getColumnIndex() == 15 ) {
					saveLectureSchedule("SATURDAY", cell, Calendar.SATURDAY);
				}
				if(cell.getColumnIndex() == 16 ) {
					saveStudentAttendanceData();
				}
			}
		}
	} 
	
	private void saveAcademicYear(Cell cell) {
		logger.debug("Saving academic year data started.");
		this.academicYear = this.testDataPojoBuilder.createAcademicYearPojo(cell);
		try {
			Example<AcademicYear> example = Example.of(this.academicYear);
			if(this.academicYearRepository.exists(example) == false) {
				this.academicYear = this.academicYearRepository.save(this.academicYear);
			}else {
				this.academicYear = this.academicYearRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving academic year data. "+e.getMessage());
		}
		logger.debug("Saving academic year data completed.");
	}
	
	private void saveTermData(Cell cell) throws ParseException {
		logger.debug("Saving term data started.");
		this.term = this.testDataPojoBuilder.createTermPojo(cell, this.academicYear);
		try {
			Example<Term> example = Example.of(this.term);
			if(this.termRepository.exists(example) == false) {
				this.term = this.termRepository.save(this.term);
			}else {
				this.term = this.termRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving term data. "+e.getMessage());
		}
		logger.debug("Saving term data completed.");
	}
	
	private void saveCollegeData(Cell cell) {
		logger.debug("Saving college data started.");
		this.college = this.testDataPojoBuilder.createCollegePojo(cell);
		try {
			Example<College> example = Example.of(this.college);
			if(this.collegeRepository.exists(example) == false) {
				this.college = this.collegeRepository.save(this.college);
			}else {
				this.college = this.collegeRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving college data. "+e.getMessage());
		}
		logger.debug("Saving college data completed.");
	}
	
	private void saveBranchData(Cell cell) {
		logger.debug("Saving branch data started.");
		State st = findStateWithStateName("Telangana");
		City ct = findCiyt(st, "Hyderabad");
		this.branch = this.testDataPojoBuilder.createBranchPojo(cell, this.college, st, ct);
		try {
			Example<Branch> example = Example.of(this.branch);
			if(this.branchRepository.exists(example) == false) {
				this.branch = this.branchRepository.save(this.branch);
			}else {
				this.branch = this.branchRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving branch data. "+e.getMessage());
		}
		logger.debug("Saving branch data completed.");
	}
	
	private State findStateWithStateName(String stateName) {
		State st = new State();
		st.setStateName(stateName);
		Example<State> example = Example.of(st);
		Optional<State> newTh = this.stateRepository.findOne(example);
		if(newTh.isPresent()) {
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
		if(newTh.isPresent()) {
			return newTh.get();
		}
		return null;
	}
	
	private void saveDepartmentData(Cell cell) {
		logger.debug("Saving department data started.");
		this.department = this.testDataPojoBuilder.createDepartmentPojo(cell, this.branch, this.academicYear);
		try {
			Example<Department> example = Example.of(this.department);
			if(this.departmentRepository.exists(example) == false) {
				this.department = this.departmentRepository.save(this.department);
			}else {
				this.department = this.departmentRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving department data. "+e.getMessage());
		}
		logger.debug("Saving department data completed.");
	}
	
	private void saveBatchData(Cell cell) {
		logger.debug("Saving batch data started.");
		this.batch = this.testDataPojoBuilder.createBatchPojo(cell, this.department);
		try {
			Example<Batch> example = Example.of(this.batch);
			if(this.batchRepository.exists(example) == false) {
				this.batch = this.batchRepository.save(this.batch);
			}else {
				this.batch = this.batchRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving batch data. "+e.getMessage());
		}
		logger.debug("Saving batch data completed.");
	}
	
	private void saveSectionData(Cell cell) {
		logger.debug("Saving section data started.");
		this.section = this.testDataPojoBuilder.createSectionPojo(cell, this.batch);
		try {
			Example<Section> example = Example.of(this.section);
			if(this.sectionRepository.exists(example) == false) {
				this.section = this.sectionRepository.save(this.section);
			}else {
				this.section = this.sectionRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving section data. "+e.getMessage());
		}
		logger.debug("Saving section data completed.");
	}
	
	private void saveStudentData(Cell cell) {
		logger.debug("Saving student data started.");
		State st = findStateWithStateName("Telangana");
		City ct = findCiyt(st, "Hyderabad");
		this.student = this.testDataPojoBuilder.createStudentPojo(cell, this.department, this.batch, 
				this.section, this.branch, st, ct, this.country);
		try {
			Example<Student> example = Example.of(this.student);
			if(this.studentRepository.exists(example) == false) {
				this.student = this.studentRepository.save(this.student);
			}else {
				this.student = this.studentRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving student data. "+e.getMessage());
		}
		logger.debug("Saving student data completed.");
	}
	
	private void saveSubjectData(Cell cell) {
		logger.debug("Saving subject data object started.");
		this.subject = this.testDataPojoBuilder.createSubjectPojo(cell, this.department, this.batch);
		try {
			Example<Subject> example = Example.of(this.subject);
			if(this.subjectRepository.exists(example) == false) {
				this.subject = this.subjectRepository.save(this.subject);
			}else {
				this.subject = this.subjectRepository.findOne(example).get();
			}
		}catch(Exception e) {
			logger.warn("Exception in saving subject data. "+e.getMessage());
		}
		logger.debug("Saving subject data object completed.");
	}
	
	private void saveTeacherTeachAttendanceMasterData(Cell cell) {
		logger.debug("Saving teacher, teach and attendance_master data started.");
		State st = findStateWithStateName("Telangana");
		City ct = findCiyt(st, "Hyderabad");
		
		try {
			this.teacher = this.testDataPojoBuilder.createTeacherPojo(cell, this.department, this.branch, st, ct, this.country);
			Example<Teacher> thrExample = Example.of(this.teacher);
			if(this.teacherRepository.exists(thrExample) == false) {
				this.teacher = this.teacherRepository.save(this.teacher);
			}else {
				this.teacher = this.teacherRepository.findOne(thrExample).get();
			}
			
			this.teach = this.testDataPojoBuilder.createTeachPojo(cell, this.subject, this.teacher);
			Example<Teach> thExample = Example.of(this.teach);
			if(this.teachRepository.exists(thExample) == false) {
				this.teach = this.teachRepository.save(this.teach);
			}else {
				this.teach = this.teachRepository.findOne(thExample).get();
			}
			
			this.attendanceMaster = this.testDataPojoBuilder.createAttendanceMasterPojo(cell, this.batch, this.section, this.teach, this.subject, this.teacher);
			Example<AttendanceMaster> amExample = Example.of(this.attendanceMaster);
			if(this.attendanceMasterRepository.exists(amExample) == false) {
				this.attendanceMaster = this.attendanceMasterRepository.save(this.attendanceMaster);
			}else {
				this.attendanceMaster = this.attendanceMasterRepository.findOne(amExample).get();
			}
			
		}catch(Exception e) {
			logger.warn("Exception in saving teacher, teach and attendance_master data. "+e.getMessage());
		}
		logger.debug("Saving teacher, teach and attendance_master data completed.");
	}
	
	private void saveLectureSchedule(String day, Cell cell, int intDay) {
		boolean isGoAhead = false;
		switch(cell.getCellTypeEnum()) {
			case BLANK:
				logger.warn("Blank cell. No lecture record will be created.");
				break;
			case BOOLEAN:
				logger.warn("Boolean cell. No lecture record will be created.");
				break;
			case ERROR:
				logger.warn("Error cell. No lecture record will be created.");
				break;
			case FORMULA:
				logger.warn("Formula cell. lecture No record will be created.");
				break;
			case NUMERIC:
				logger.warn("Numeric cell. lecture No record will be created.");
				break;
			case STRING:
				isGoAhead = true;
				break;
			case _NONE:
				logger.warn("_None cell. No lecture record will be created.");
				break;
			default:
				logger.warn("Blank or _None cell. lecture No record will be created.");
				break;
		}
		if(isGoAhead == false) {
			return;
		}
		logger.debug("Saving lecture data for "+day+" started.");
		try {
			LectureScheduleInput lectureScheduleInput = new LectureScheduleInput();
			ObjectMapper mapper = new ObjectMapper();
			String values[] = new String[1];
			LectureScheduleDTO dto =  this.testDataPojoBuilder.getDto(day, cell, this.subject, this.teacher);
			values[0] = mapper.writeValueAsString(dto);
			lectureScheduleInput.setValues(values); 
			
			LectureScheduleFilter filter = new LectureScheduleFilter();
			filter.setTermId(String.valueOf(this.term.getId()));
			filter.setAcademicYear(this.academicYear.getYear());
			filter.setSectionId(String.valueOf(this.section.getId()));
			filter.setBatchId(String.valueOf(this.batch.getId()));
			List<Date> dtList = filterDates(intDay);
			for(Date dt : dtList) {
				boolean isLectureExists = this.testDataPojoBuilder.findLecture(dt, dto, this.attendanceMaster, this.lectureRepository);
				if(isLectureExists == false) {
					QueryResult res = this.lectureService.addLectureSchedule(lectureScheduleInput, filter);
				}
			}
			
		}catch(Exception e) {
			logger.warn("Lecture data for "+day+" could not be saved in DB due the below exception.");
			logger.warn("Exception. "+e.getMessage());
		}
		logger.debug("Saving lecture data for "+day+" completed.");
	}
	
	private List<Date> filterDates(int day) throws ParseException {
		List<Date> dateList = this.lectureService.createDates(this.term);
		List<Holiday> holidayList = this.lectureService.getHolidayList(this.academicYear.getYear());
		this.lectureService.filterHolidays (holidayList,dateList);
		this.lectureService.filterSundays(dateList);
		List<Date> list = this.lectureService.filterDateListOnDayOfweek(dateList, day);
		return list;
	}
	private void saveStudentAttendanceData() {
		List<Lecture> lcList =  this.testDataPojoBuilder.findLectureByAttendanceMaster(this.lectureRepository, this.attendanceMaster);
		for(Lecture lecture : lcList) {
			this.studentAttendance = this.testDataPojoBuilder.createStudentAttendanceData(this.student, lecture);
			try {
				Example<StudentAttendance> example = Example.of(this.studentAttendance);
				if(this.studentAttendanceRepository.exists(example) == false) {
					this.studentAttendance = this.studentAttendanceRepository.save(this.studentAttendance);
					this.studentAttendance = null;
				}
			}catch(Exception e) {
				logger.warn("Exception in saving studentAttendance data. "+e.getMessage());
			}
		}
	}
	
	
}
