package com.synectiks.cms.web.rest;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.entities.CmsStudentTypeVo;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing student type.
 */
@RestController
@RequestMapping("/api")
public class StudentTypeRestController {

    private final Logger logger = LoggerFactory.getLogger(StudentTypeRestController.class);

    private static final String ENTITY_NAME = "studenttype";

    @Autowired
    private CommonService commonService;
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsstudenttype")
    public List<CmsStudentTypeVo> getAllStudentTypes() {
        logger.debug("REST request to get all the "+ENTITY_NAME);
        return this.commonService.getAllStudentTypes();
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsstudenttype/{id}")
    public ResponseEntity<CmsStudentTypeVo> getStudentType(@PathVariable Long id) {
        logger.debug("REST request to get a student type : {}", id);
        CmsStudentTypeVo vo = this.commonService.getStudentType(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsstudenttype/{studentypedescription}")
    public ResponseEntity<CmsStudentTypeVo> getStudentTypeByDescription(@PathVariable String studentypedescription) {
        logger.debug("REST request to get a student type : {}", studentypedescription);
        CmsStudentTypeVo vo = this.commonService.getStudentTypeByDescription(studentypedescription);
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }
    
    
}
