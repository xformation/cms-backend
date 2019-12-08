package com.synectiks.cms.web.rest;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.entities.Config;
import com.synectiks.cms.entities.Student;
import com.synectiks.cms.entities.Teacher;
import com.synectiks.cms.repositories.StudentRepository;
import com.synectiks.cms.repositories.TeacherRepository;

/**
 * REST controller for managing user login.
 */
@RestController
@RequestMapping("/api")
public class LoginRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ApplicationProperties applicationProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmslogin")
	public ResponseEntity<Object> cmsLogin(@RequestParam  String username, @RequestParam String password) {
		String url = applicationProperties.getSecSrvUrl()+"/security/public/login?username=" + username + "&password=" + password;
		Object status = null;
		
		try {
			status = restTemplate.getForObject(url, Object.class);
			Config config = null;
			if("admin".equals(username) || "cmsadmin".equals(username)) {
    			config = commonService.createUserConfigForAdmin(username);
			}else {
				config = commonService.createUserConfig(username);
			}
			
			config.setLoginResponse(status);
			CmsConstants.USERS_CACHE.put(username, config);
		}catch(Exception e) {
			logger.error("Loging authentication failed :", e);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e);
		}
		logger.info("Users response status : "+status);
		return ResponseEntity.status(HttpStatus.OK).body(status);
	}

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmslogout")
	public ResponseEntity<Object> cmsLogout(@RequestParam  String username) {
		logger.info("Logging out user : "+username);
		if(CmsConstants.USERS_CACHE.containsKey(username)) {
			CmsConstants.USERS_CACHE.remove(username);
		}
		return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK);
	}
	
}
