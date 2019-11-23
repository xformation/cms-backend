
package com.synectiks.cms.automated.testdatasetup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.commons.entities.cms.AcademicExamSetting;
import com.synectiks.commons.entities.cms.AcademicYear;
import com.synectiks.commons.entities.cms.City;
import com.synectiks.commons.entities.cms.Country;
import com.synectiks.commons.entities.cms.Department;
import com.synectiks.commons.entities.cms.Section;
import com.synectiks.commons.entities.cms.State;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.StateRepository;

@RestController
@RequestMapping("/api")
public class CmsAutomatedTestDataSetupProcessor {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final TestDataPojoBuilder pojoBuilder = new TestDataPojoBuilder();
	private static final String DATA_FILE_XLSX = "cms_test_data_setup.xlsx";
	private static final String DATA_FILE_XLS = "cms_test_data_setup.xls";
	private static final int BATCH_SIZE = 10;

	private Country country = null;
	private State state = null;
	private City city = null;
	private AcademicYear academicYear = null;
	private Department department = null;
	private Section section = null;
	private AcademicExamSetting academicExamSetting = null;

	@Autowired
	private AcademicExamSettingRepository academicExamSettingRepository;
	@Autowired
	private CmsDataRowProcessor processor;
	@Autowired
	private CountryRepository countryRepository;
	@Autowired
	private StateRepository stateRepository;
	@Autowired
	private CityRepository cityRepository;

	private void processCmsDataSheetForAllCell(ReadableWorkbook wb) {
		this.processCmsDataSheetForCell(wb, -1);
	}

	private void processCmsDataSheetForCell(ReadableWorkbook wb, int cellIndx) {
		this.processCmsDataSheetForCell(wb, cellIndx, null);
	}

	private void processCmsDataSheetForCell(ReadableWorkbook wb, int cellIndx,
			CmsDataRowProcessor.CallbackHandler handler) {
		Sheet sheet = wb.findSheet("cmstestdata").orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				rows.forEach(row -> {
					logger.info("RowNum: " + row.getRowNum());
					// Skip first header row
					if (row.getRowNum() > 1) {
						processor.process(row, this.country, this.state, this.city,
								cellIndx, handler);
					}
				});
			}
		} catch (Exception e) {
			logger.error("Failed to iterate city sheet rows ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/create")
	public void createCmsTestData() throws IOException, ParseException {
		try {
			execute();
		} catch (Exception e) {
			logger.error("Exception in creating CMS test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAcademicYear")
	public void createCmsTestAcademicYearData() throws IOException, ParseException {
		try {
			executeAcademicYear();
		} catch (Exception e) {
			logger.error("Exception in creating CMS AcademicYear test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAcademicHistory")
	public void createCmsTestAcademicHistoryData() throws IOException, ParseException {
		try {
			executeAcademicHistory();
		} catch (Exception e) {
			logger.error("Exception in creating CMS AcademicHistory test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAdmissionApplication")
	public void createCmsTestAdmissionApplicationData()
			throws IOException, ParseException {
		try {
			executeAdmissionApplication();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  AdmissionApplication test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAdmissionEnquiry")
	public void createCmsTestAdmissionEnquiryData() throws IOException, ParseException {
		try {
			executeAdmissionEnquiry();
		} catch (Exception e) {
			logger.error("Exception in creating CMS   AdmissionEnquiry test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTerm")
	public void createCmsTestTermData() throws IOException, ParseException {
		try {
			executeTerm();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Term test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createCollege")
	public void createCmsTestCollegeData() throws IOException, ParseException {
		try {
			executeCollege();
		} catch (Exception e) {
			logger.error("Exception in creating CMS College test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTeacherTeachAttendanceMaster")
	public void createCmsTeacherTeachAttendanceMasterData()
			throws IOException, ParseException {
		try {
			executeTeacherTeachAttendanceMaster();
		} catch (Exception e) {
			logger.error(
					"Exception in creating CMS TeacherTeachAttendanceMaster test data ",
					e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createBranch")
	public void createCmsTestBranchData() throws IOException, ParseException {
		try {
			executeBranch();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Branch test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createDepartment")
	public void createCmsTestDepartmentData() throws IOException, ParseException {
		try {
			executeDepartment();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Department test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createBatch")
	public void createCmsTestBatchData() throws IOException, ParseException {
		try {
			executeBatch();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Batch test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createSection")
	public void createCmsTestSectionData() throws IOException, ParseException {
		try {
			executeSection();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Section test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createStudent")
	public void createCmsTestStudentData() throws IOException, ParseException {
		try {
			executeStudent();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Student test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createSubject")
	public void createCmsTestSubjectData() throws IOException, ParseException {
		try {
			executeSubject();
		} catch (Exception e) {
			logger.error("Exception in creating CMS Subject test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createLecture")
	public void createCmsTestLectureData() throws IOException, ParseException {
		try {
			executeLecture();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  Lecture test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createFeeCategory")
	public void createCmsTestFeeCategoryData() throws IOException, ParseException {
		try {
			executeFeeCategory();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  FeeCategory test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createFacility")
	public void createCmsTestFacilityData() throws IOException, ParseException {
		try {
			executeFacility();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  Facility test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTransportRoute")
	public void createCmsTestTransportRouteData() throws IOException, ParseException {
		try {
			executeTransportRoute();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  TransportRoute test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createFeeDetails")
	public void createCmsTestFeeDetailsData() throws IOException, ParseException {
		try {
			executeFeeDetails();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  FeeDetails test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createAcademicExamSetting")
	public void createCmsTestAcademicExamSettingData()
			throws IOException, ParseException {
		try {
			executeAcademicExamSetting();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  AcademicExamSetting test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createDueDate")
	public void createCmsTestDueDate() throws IOException, ParseException {
		try {
			executeDueDate();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  DueDate test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createPaymentRemainder")
	public void createCmsTestPaymentRemainderData() throws IOException, ParseException {
		try {
			executePaymentRemainder();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  PaymentRemainder test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createHoliday")
	public void createCmsTestHolidayData() throws IOException, ParseException {
		try {
			executeHoliday();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  Holiday test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createInvoice")
	public void createCmsTestInvoiceData() throws IOException, ParseException {
		try {
			executeInvoice();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  Invoice test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createTypeOfGrading")
	public void createCmsTestTypeOfGradingData() throws IOException, ParseException {
		try {
			executeTypeOfGrading();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  TypeOfGrading test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createStudentExamReport")
	public void createCmsTestStudentExamReportData() throws IOException, ParseException {
		try {
			executeStudentExamReport();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  StudentExamReport test data ", e);
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/cmstestdata/createStudentFacilityLink")
	public void createCmsTestStudentFacilityLinkReportData()
			throws IOException, ParseException {
		try {
			executeStudentFacilityLink();
		} catch (Exception e) {
			logger.error("Exception in creating CMS  StudentFacilityLink test data ", e);
		}
	}

	private void executeAcademicExamSetting()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAcademicExamSettingData(wb);
			logger.info("AcademicExamSetting TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeDueDate()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsDueDate(wb);
			logger.info("DueDate TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeAcademicYear()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAcademicYearData(wb);
			logger.info("AcademicYear TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeAcademicHistory()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAcademicHistoryData(wb);
			logger.info("AcademicHistory TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeAdmissionApplication()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAdmissionApplication(wb);
			logger.info(" AdmissionApplication TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeStudentFacilityLink()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsStudentFacilityLink(wb);
			logger.info("  StudentFacilityLink TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeAdmissionEnquiry()
			throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAdmissionEnquiry(wb);
			logger.info("  AdmissionEnquiry TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeTerm() throws IOException, ParseException, InterruptedException {
		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAcademicYearData(wb);
			saveCmsTermData(wb);
			logger.info("Term TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeCollege()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsCollegeData(wb);
			logger.info("College TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeBranch()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCountryData();
			saveStateData(wb);
			saveCityData(wb);
			saveCmsCollegeData(wb);
			saveCmsBranchData(wb);
			logger.info("Branch TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeTeacherTeachAttendanceMaster()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCountryData();
			saveStateData(wb);
			saveCityData(wb);
			saveCmsSubjectData(wb);
			saveCmsTeacherTeachAttendanceMaster(wb);
			saveCmsBatchData(wb);
			saveCmsSectionData(wb);
			saveCmsDepartmentData(wb);
			saveCmsBranchData(wb);
			logger.info("TeacherTeachAttendanceMaster TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeDepartment()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsBranchData(wb);
			saveCmsAcademicYearData(wb);
			saveCmsDepartmentData(wb);
			logger.info("Department TEST DATA LOADING COMPLETED......");
		}
	}

	private void executeBatch() throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsDepartmentData(wb);
			saveCmsBatchData(wb);
			logger.info("Batch TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeSection()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsBatchData(wb);
			saveCmsSectionData(wb);
			logger.info("Section TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeStudent()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCountryData();
			saveStateData(wb);
			saveCityData(wb);
			saveCmsDepartmentData(wb);
			saveCmsBatchData(wb);
			saveCmsSectionData(wb);
			saveCmsBranchData(wb);
			saveCmsStudentData(wb);
			logger.info("Student TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeSubject()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsDepartmentData(wb);
			saveCmsBatchData(wb);
			saveCmsSubjectData(wb);
			logger.info("Subject TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeLecture()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsTeacherTeachAttendanceMaster(wb);
			saveCmsLecture(wb);
			logger.info("Lecture TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeFeeCategory()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsFeeCategory(wb);
			logger.info("FeeCategory TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeFacility()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsAcademicYearData(wb);
			saveCmsBranchData(wb);
			saveCmsStudentData(wb);
			saveCmsFacilityData(wb);
			logger.info("Facility TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeTransportRoute()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsTransportRouteData(wb);
			logger.info("TransportRoute TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeFeeDetails()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsFeeCategory(wb);
			saveCmsBatchData(wb);
			saveCmsFacilityData(wb);
			saveCmsTransportRouteData(wb);
			saveCmsCollegeData(wb);
			saveCmsDepartmentData(wb);
			saveCmsBranchData(wb);
			saveCmsAcademicYearData(wb);
			saveCmsFeeDetails(wb);
			logger.info("FeeDetails TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executePaymentRemainder()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsPaymentRemainderData(wb);
			logger.info("PaymentRemainder TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeHoliday()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsHoliday(wb);
			logger.info("Holiday TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeInvoice()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsInvoice(wb);
			logger.info("Invoice TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeTypeOfGrading()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsTypeOfGrading(wb);
			logger.info("TypeOfGrading TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void executeStudentExamReport()
			throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsStudentExamReport(wb);
			logger.info("StudentExamReport TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private void execute() throws IOException, ParseException, InterruptedException {

		try (InputStream is = new FileInputStream(getFile());
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCountryData();
			saveStateData(wb);
			saveCityData(wb);
			saveCmsData(wb);
			logger.info("TEST DATA LOADING COMPLETED......");
		} catch (Exception e) {
			logger.error("Failed read workbook ", e);
		}
	}

	private File getFile() throws FileNotFoundException {
		String r = Paths.get("").toAbsolutePath() + "/src/test/" + DATA_FILE_XLSX;
		File f = new File(r);
		if (!f.exists()) {
			r = Paths.get("").toAbsolutePath() + "/src/test/" + DATA_FILE_XLS;
			f = new File(r);
			if (!f.exists()) {
				throw new FileNotFoundException("File not found : " + r);
			}
		}
		return f;
	}

	private void saveCountryData() {
		logger.debug("Saving country data started.");
		Country country = pojoBuilder.createCountryPojo();
		this.country = CmsDataRowProcessor.saveOrGet(this.countryRepository, country);
		logger.debug("Saving country data completed.");
	}

	private void saveStateData(ReadableWorkbook wb) {
		logger.debug("Saving state data started.");

		Sheet sheet = wb.findSheet("state").orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<State> states = new ArrayList<>();
				rows.forEach(row -> {

					if (states.size() == BATCH_SIZE) {
						this.stateRepository.saveAll(states);
						states.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						State state = new State();
						String stCode = row.getCellAsString(2).orElse(null);
						state.setStateCode(stCode);
						if (stCode != null
								&& !this.stateRepository.exists(Example.of(state))) {
							state.setStateName(row.getCellAsString(0).orElse(null));
							state.setDivisionType(row.getCellAsString(1).orElse(null));
							states.add(state);
						}
					}
				});
				// Save remaining items
				this.stateRepository.saveAll(states);
				states.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate state sheet rows ", e);
		}
		logger.debug("Saving state data completed.");
	}

	private void saveCityData(ReadableWorkbook wb) {
		logger.debug("Saving city data started.");
		Sheet sheet = wb.findSheet("city").orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<City> cities = new ArrayList<>();
				rows.forEach(row -> {
					if (cities.size() == BATCH_SIZE) {
						this.cityRepository.saveAll(cities);
						cities.clear();
					}
					// Skip first header row
					if (row.getRowNum() > 1) {
						City city = new City();
						String stCode = row.getCellAsString(1).orElse(null);
						city.setCityCode(stCode);
						if (stCode != null
								&& !this.cityRepository.exists(Example.of(city))) {
							city.setCityName(row.getCellAsString(0).orElse(null));
							city.setStdCode(row.getCellAsString(2).orElse(null));
							String state = row.getCellAsString(3).orElse(null);
							city.setState(pojoBuilder.findStateByStateCode(state,
									this.stateRepository));
							cities.add(city);
						}
					}
				});
				// Save remaining items
				this.cityRepository.saveAll(cities);
				cities.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate city sheet rows ", e);
		}
		logger.debug("Saving city data completed.");
	}

	private void saveCmsAcademicExamSettingData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 0, new CmsDataRowProcessor.CallbackHandler() {

			@Override
			public void handle(Cell cell) {
				saveAcademicExamSetting(cell);
			}
		});
	}

	private void saveCmsPaymentRemainderData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 26);
	}

	private void saveCmsAcademicHistoryData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 22);
	}

	private void saveCmsAdmissionApplication(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 23);
	}

	private void saveCmsStudentFacilityLink(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 31);
	}

	private void saveCmsAdmissionEnquiry(ReadableWorkbook wb)
			throws ParseException, InterruptedException {

		this.processCmsDataSheetForCell(wb, 24);

	}

	private void saveCmsData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {

		this.processCmsDataSheetForAllCell(wb);

	}

	private void saveCmsAcademicYearData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 0);
	}

	private void saveCmsDueDate(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 25);
	}

	private void saveCmsTermData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 1);
	}

	private void saveCmsCollegeData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 2);
	}

	private void saveCmsBranchData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 3);
	}

	private void saveCmsDepartmentData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 4);
	}

	private void saveCmsBatchData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 5);
	}

	private void saveCmsSectionData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 6);
	}

	private void saveCmsStudentData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 7);
	}

	private void saveCmsSubjectData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 8);
	}

	private void saveCmsTeacherTeachAttendanceMaster(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 9);
	}

	private void saveCmsLecture(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 10);
		this.processCmsDataSheetForCell(wb, 11);
		this.processCmsDataSheetForCell(wb, 12);
		this.processCmsDataSheetForCell(wb, 13);
		this.processCmsDataSheetForCell(wb, 14);
		this.processCmsDataSheetForCell(wb, 15);
		
	}

	private void saveCmsFeeCategory(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 17);
	}

	private void saveCmsFacilityData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 18);
	}

	private void saveCmsTransportRouteData(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 19);
	}

	private void saveCmsFeeDetails(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 20);
	}

	private void saveCmsHoliday(ReadableWorkbook wb)
			throws ParseException, InterruptedException {

		this.processCmsDataSheetForCell(wb, 27);
	}

	private void saveCmsInvoice(ReadableWorkbook wb)
			throws ParseException, InterruptedException {
		this.processCmsDataSheetForCell(wb, 28);

	}

	private void saveCmsTypeOfGrading(ReadableWorkbook wb)
			throws ParseException, InterruptedException {

		this.processCmsDataSheetForCell(wb, 29);

	}

	private void saveCmsStudentExamReport(ReadableWorkbook wb)
			throws ParseException, InterruptedException {

		this.processCmsDataSheetForCell(wb, 30);

	}

	private void saveAcademicExamSetting(Cell cell) {
		logger.debug("Saving AcademicExamSetting data started.");
		this.academicExamSetting = pojoBuilder.createAcademicExamSettingPojo(cell,
				this.department, this.academicYear, this.section);
		this.academicExamSetting = CmsDataRowProcessor.saveOrGet(
				this.academicExamSettingRepository, this.academicExamSetting);
		logger.debug("Saving AcademicExamSetting data completed.");
	}

}
