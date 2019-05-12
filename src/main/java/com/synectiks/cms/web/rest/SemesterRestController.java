package com.synectiks.cms.web.rest;

import java.util.ArrayList;
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
import com.synectiks.cms.domain.CmsSemesterVo;
import com.synectiks.cms.graphql.types.Student.Semester;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Semester.
 */
@RestController
@RequestMapping("/api")
public class SemesterRestController {

    private final Logger logger = LoggerFactory.getLogger(SemesterRestController.class);

    private static final String ENTITY_NAME = "semester";

    @Autowired
    private CommonService commonService;
    
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters")
    public List<CmsSemesterVo> getAllSemesters() throws Exception {
        logger.debug("REST request to get all the "+ENTITY_NAME);
        return this.commonService.getAllSemesters();
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters/{id}")
    public ResponseEntity<CmsSemesterVo> getSemester(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get a semester : {}", id);
        CmsSemesterVo vo = this.commonService.getSemester(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

}
