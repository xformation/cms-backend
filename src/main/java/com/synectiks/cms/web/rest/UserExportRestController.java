package com.synectiks.cms.web.rest;

/**
 * REST controller for managing user export.
 */
//@RestController
//@RequestMapping("/api")
public class UserExportRestController {

//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	@Autowired
//	private StudentRepository studentRepository;
//
////	@Autowired
////	private TeacherRepository teacherRepository;
////
////	@Autowired
////	private EmployeeRepository employeeRepository;
//
//	@Autowired
//	private ApplicationProperties applicationProperties;
//	
//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Autowired
//	private CommonService commonService;
//	
//	//	
////	@Autowired
////    private AcademicYearRepository academicYearRepository;
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/cmsuserexport")
//	public ResponseEntity<Integer> exportUser(@RequestParam Map<String, String> dataMap) throws JSONException, ParseException, JsonProcessingException {
//		boolean isTecher = dataMap.get("chkTeacher") != null ? Boolean.parseBoolean(dataMap.get("chkTeacher")) : false;
//		boolean isStudent = dataMap.get("chkStudent") != null ? Boolean.parseBoolean(dataMap.get("chkStudent")) : false;
//		boolean isEmployee = dataMap.get("chkEmployee") != null ? Boolean.parseBoolean(dataMap.get("chkEmployee")) : false;
//		Long branchId = dataMap.get("branchId") != null ? Long.parseLong(dataMap.get("branchId")) : 0;
//		
//		logger.debug("Branch id :"+branchId+", Teacher selected : "+isTecher+", Student selected : "+isStudent+", Employee selected : "+isEmployee);
//		
//		Set<String> email = new HashSet<>();
//		 
//		if(isTecher) {
//			List<Teacher> teacherList = this.commonService.getAllTeachersByBranch(branchId);
//			for(Teacher t: teacherList) {
//				email.add(t.getTeacherEmailAddress());
//			}
//		}
//		if(isStudent) {
//			Student student = new Student();
//			student.setBranchId(branchId);
//			List<Student> studentList = this.studentRepository.findAll(Example.of(student));
//			for(Student s: studentList) {
//				email.add(s.getStudentPrimaryEmailId());
//			}
//		}
//		if(isEmployee) {
//			List<Employee> employeeList = this.commonService.getAllEmployeesByBranch(branchId);;
//			for(Employee e: employeeList) {
//				email.add(e.getOfficialMailId());
//			}
//		}
//		List<String> finalList = email.stream().collect(Collectors.toList());
//		if(finalList != null && finalList.size() > 0) {
//			int statusCode = exportUserToSecuryService(finalList);
//			return ResponseEntity.status(statusCode)
//		            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
//		            .body(new Integer(statusCode));
//		}
//		return ResponseEntity.status(200)
//	            .headers(HeaderUtil.createEntityUpdateAlert("", ""))
//	            .body(new Integer(200));
//	}
//	
//	private int exportUserToSecuryService(List<String> list) throws JsonProcessingException {
//		
//		String status = restTemplate.postForObject(applicationProperties.getSecSrvUrl()+"/security/public/importUser", 
//				list, String.class, list);
//		
//		logger.info("Export users response status : "+status);
//		return status.equalsIgnoreCase("SUCCESS") ? 200 : 500;
//	}
//	
//	

}
