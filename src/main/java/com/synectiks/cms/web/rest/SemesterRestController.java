package com.synectiks.cms.web.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String ENTITY_NAME = "Semester";

    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters")
    public List<CmsSemesterVo> getAllSemesterss() throws Exception {
        logger.debug("REST request to get all the "+ENTITY_NAME);
        List<CmsSemesterVo> ls = new ArrayList<>();
        for(Semester sm: Semester.values()) {
        	CmsSemesterVo vo = new CmsSemesterVo();
        	vo.setId(sm.value());
        	vo.setDescription(sm.getDescription());
        	ls.add(vo);
        }
        return ls;
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssemesters/{id}")
    public ResponseEntity<CmsSemesterVo> getSemester(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an Semester : {}", id);
        Semester sm = Semester.valueOf(id.intValue());
        CmsSemesterVo vo = new CmsSemesterVo();
        vo.setId(sm.value());
        vo.setDescription(sm.getDescription());
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

}
