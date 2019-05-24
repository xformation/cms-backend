package com.synectiks.cms.web.rest;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.base64.file.Base64FileProcessor;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsCollegeVo;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.repository.CollegeRepository;

/**
 * REST controller for managing College.
 */
@RestController
@RequestMapping("/api")
public class CollegeRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private Base64FileProcessor base64FileProcessor;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmscollege")
	public int createCollege(@RequestBody CmsCollegeVo cmsCollegeVo) {
		logger.info("REST request to create a new college.");
		int status = 400;
		College college = new College();
		college.setShortName(cmsCollegeVo.getShortName());
        college.setInstructionInformation(cmsCollegeVo.getInstructionInformation());
        college = collegeRepository.save(college);
		try {
			if(cmsCollegeVo.getBgImage() != null) {
				String filePath = Paths.get("", CmsConstants.CMS_IMAGE_FILE_PATH+File.separator+college.getId()).toString();
				college.setBackgroundImagePath(filePath);
				String fileName = CmsConstants.CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME;
				String branchId = null;
				String ext = base64FileProcessor.getFileExtensionFromBase64Srting(cmsCollegeVo.getBgImage().split(",")[0]);
				college.setBackgroundImageFileName(CmsConstants.CMS_COLLEGE_BACKGROUND_IMAGE_FILE_NAME+"."+ext);
				base64FileProcessor.createFileFromBase64String(cmsCollegeVo.getBgImage(), filePath, fileName, branchId, null);
			}
			if(cmsCollegeVo.getLogoImage() != null) {
				String filePath = Paths.get("", CmsConstants.CMS_IMAGE_FILE_PATH+File.separator+college.getId()).toString();
				college.setLogoPath(filePath);
				String fileName = CmsConstants.CMS_COLLEGE_LOGO_FILE_NAME;
				String branchId = null;
				String ext = base64FileProcessor.getFileExtensionFromBase64Srting(cmsCollegeVo.getLogoImage().split(",")[0]);
				college.setLogoFileName(CmsConstants.CMS_COLLEGE_LOGO_FILE_NAME+"."+ext);
				base64FileProcessor.createFileFromBase64String(cmsCollegeVo.getLogoImage(), filePath, fileName, branchId, null);
			}
			
	        logger.info("REST request to create a new college completed successfully.");
	        status = 200;
		}catch(Exception e) {
			logger.error("Exception while saving college data: ",e);
		}
		return status;
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmscollege")
    public List<College> getAllColleges() {
		logger.debug("REST request to get all the college records.");
        return collegeRepository.findAll();
    }
	
	
}
