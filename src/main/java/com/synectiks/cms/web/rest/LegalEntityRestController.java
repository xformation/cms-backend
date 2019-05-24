package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codahale.metrics.annotation.Timed;
import com.synectiks.cms.base64.file.Base64FileProcessor;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsLegalEntityVo;
import com.synectiks.cms.domain.LegalEntity;
import com.synectiks.cms.exceptions.BranchIdNotFoundException;
import com.synectiks.cms.exceptions.FileNameNotFoundException;
import com.synectiks.cms.exceptions.FilePathNotFoundException;
import com.synectiks.cms.repository.LegalEntityRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Legal Entity.
 */
@RestController
@RequestMapping("/api")
public class LegalEntityRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ENTITY_NAME = "LegalEntity";
	
	@Autowired
	private LegalEntityRepository legalEntityRepository;
	
	@Autowired
	private Base64FileProcessor base64FileProcessor;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmslegal-entities")
	public ResponseEntity<CmsLegalEntityVo> createLegalEntity(@RequestBody CmsLegalEntityVo cmsLegalEntityVo) throws URISyntaxException, FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
		
		logger.debug("REST request to save LegalEntity : {}", cmsLegalEntityVo);
        if (cmsLegalEntityVo.getId() != null) {
            throw new BadRequestAlertException("A new legalEntity cannot have an ID that already exists", ENTITY_NAME, "idexists");
        }
        
        LegalEntity legalEntity = CommonUtil.createCopyProperties(cmsLegalEntityVo, LegalEntity.class);
        if(cmsLegalEntityVo.getCollegeId() != null && cmsLegalEntityVo.getCollegeId() > 0) {
        	legalEntity.setCollege(this.commonService.getCollegeById(cmsLegalEntityVo.getCollegeId()));
        }
        if(cmsLegalEntityVo.getStateId() != null && cmsLegalEntityVo.getStateId() > 0) {
        	legalEntity.setState(this.commonService.getStateById(cmsLegalEntityVo.getStateId()));
        }
        if(cmsLegalEntityVo.getCityId() != null && cmsLegalEntityVo.getCityId() > 0) {
        	legalEntity.setCity(this.commonService.getCityById(cmsLegalEntityVo.getCityId()));
        }
        if(cmsLegalEntityVo.getBranchId() != null && cmsLegalEntityVo.getBranchId() > 0) {
        	legalEntity.setBranch(this.commonService.getBranchById(cmsLegalEntityVo.getBranchId()));
        }
        String logoFile = null;
        String fileName = null;
        if(cmsLegalEntityVo.getLogoFile() != null) {
        	logoFile = cmsLegalEntityVo.getLogoFile();
        	String ext = base64FileProcessor.getFileExtensionFromBase64Srting(cmsLegalEntityVo.getLogoFile().split(",")[0]);
//        	base64FileProcessor.createFileFromBase64String(cmsLegalEntityVo.getLogoFile(), CmsConstants.CMS_IMAGE_FILE_PATH, CmsConstants.CMS_LEGAL_ENTITY_LOGO_FILE_NAME, null);
        	fileName = CmsConstants.CMS_LEGAL_ENTITY_LOGO_FILE_NAME +"_CollegeId_"+legalEntity.getCollege().getId()+(legalEntity.getBranch() != null ? "_BranchId_"+legalEntity.getBranch().getId() : "");
        	legalEntity.setLogoFileName(fileName+"."+ext);
        	legalEntity.setLogoPath(CmsConstants.CMS_IMAGE_FILE_PATH);
        	legalEntity.setLogoFile(null);
        }
        
        
        legalEntity = legalEntityRepository.save(legalEntity);
        
        if(cmsLegalEntityVo.getLogoFile() != null) {
        	try {
	        		base64FileProcessor.createFileFromBase64String(cmsLegalEntityVo.getLogoFile(), CmsConstants.CMS_IMAGE_FILE_PATH, fileName, null, null);
        	}catch(Exception e) {
        		logger.error("Some error in saving legal entity logo file on disk. Rolling back the transaction.",e);
        		legalEntityRepository.deleteById(legalEntity.getId());
        	}
        }
        cmsLegalEntityVo = CommonUtil.createCopyProperties(legalEntity, CmsLegalEntityVo.class);
        cmsLegalEntityVo.setLogoFile(logoFile);
        
        return ResponseEntity.created(new URI("/api/cmslegal-entities/" + cmsLegalEntityVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsLegalEntityVo.getId().toString()))
            .body(cmsLegalEntityVo);

	}
	
    @RequestMapping(method = RequestMethod.GET, value = "/cmslegal-entities")
    public List<CmsLegalEntityVo> getAllLegalEntities() {
        logger.debug("REST request to get all the LegalEntities");
        List<LegalEntity> le = legalEntityRepository.findAll();
        List<CmsLegalEntityVo> cle = new ArrayList<>();
        CmsLegalEntityVo cvo = null;
        for(LegalEntity l: le) {
        	cvo = CommonUtil.createCopyProperties(l, CmsLegalEntityVo.class);
        	if(l.getCollege() != null) {
        		cvo.setCollegeId(l.getCollege().getId());
        	}
        	cle.add(cvo);
        }
        return cle;
    }
	

    @GetMapping("/legal-entities/{id}")
    @Timed
    @RequestMapping(method = RequestMethod.GET, value = "/cmslegal-entities/{id}")
    public ResponseEntity<CmsLegalEntityVo> getLegalEntity(@PathVariable Long id) {
    	logger.debug("REST request to get a LegalEntity : {}", id);
        Optional<LegalEntity> legalEntity= legalEntityRepository.findById(id);
        CmsLegalEntityVo cvo = new CmsLegalEntityVo();
        if(legalEntity.isPresent()) {
        	LegalEntity le = legalEntity.get();
        	cvo = CommonUtil.createCopyProperties(le, CmsLegalEntityVo.class);
        	cvo.setCollegeId(le.getCollege().getId());
        }
        Optional<CmsLegalEntityVo> ocvo = Optional.of(cvo);
        return ResponseUtil.wrapOrNotFound(ocvo);
    }
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmslegal-entities/{id}")
    public ResponseEntity<Void> deleteLegalEntity(@PathVariable Long id) {
    	logger.debug("REST request to delete a LegalEntity : {}", id);
    	legalEntityRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
	
}
