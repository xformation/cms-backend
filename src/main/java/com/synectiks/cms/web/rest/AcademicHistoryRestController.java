package com.synectiks.cms.web.rest;


import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.*;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AcademicHistoryRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ENTITY_NAME = "academicHistory";

    @Autowired
    private AcademicHistoryRepository academicHistoryRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/cmsacademicHistory")
    public ResponseEntity<AcademicHistory> createAcademicHistory(@Valid @RequestBody AcademicHistory academicHistory) throws URISyntaxException {
        logger.info("REST request to create a new academicHistory.", academicHistory);
        if (academicHistory.getId() != null) {
            throw new BadRequestAlertException("A new academicHistory cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        AcademicHistory ah = new AcademicHistory();
        ah.setQualification(academicHistory.getQualification());
        ah.setYearOfPassing(academicHistory.getYearOfPassing());
        ah.setInstitution(academicHistory.getInstitution());
        ah.setUniversity(academicHistory.getUniversity());
        ah.setEnrollmentNo(academicHistory.getEnrollmentNo());
        ah.setScore(academicHistory.getScore());
        ah.setPercentage(academicHistory.getPercentage());
        AcademicHistory result = academicHistoryRepository.save(ah);
        academicHistory.setId(result.getId());
        return ResponseEntity.created(new URI("/api/cmsacademicHistory/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(academicHistory);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsacademicHistory")
    public ResponseEntity<AcademicHistory> updateAcademicHistory(@Valid @RequestBody AcademicHistory academicHistory) throws URISyntaxException {
        logger.info("REST request to update existing academicHistory.", academicHistory);
        if (academicHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicHistory ah = new AcademicHistory();
        ah.setQualification(academicHistory.getQualification());
        ah.setYearOfPassing(academicHistory.getYearOfPassing());
        ah.setInstitution(academicHistory.getInstitution());
        ah.setUniversity(academicHistory.getUniversity());
        ah.setEnrollmentNo(academicHistory.getEnrollmentNo());
        ah.setScore(academicHistory.getScore());
        ah.setPercentage(academicHistory.getPercentage());
        ah.setId(academicHistory.getId());
        AcademicHistory result = academicHistoryRepository.save(ah);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, academicHistory.getId().toString()))
            .body(academicHistory);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademicHistory")
    public List<AcademicHistory> getAllAcademicHistory() {
        logger.debug("REST request to get all the academicHistory.");
        List<AcademicHistory> list = academicHistoryRepository.findAll();
        List<AcademicHistory> ls = new ArrayList<>();
        for(AcademicHistory ah : list) {
            AcademicHistory aa = new AcademicHistory();
            aa.setQualification(ah.getQualification());
            aa.setYearOfPassing(ah.getYearOfPassing());
            aa.setInstitution(ah.getInstitution());
            aa.setUniversity(ah.getUniversity());
            aa.setEnrollmentNo(ah.getEnrollmentNo());
            aa.setScore(ah.getScore());
            aa.setPercentage(ah.getPercentage());
            aa.setId(ah.getId());
            ls.add(aa);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademicHistory/{id}")
    public List<AcademicHistory> getAllAcademicHistory(@PathVariable Long id){
        AcademicHistory ah = new AcademicHistory();
        Example<AcademicHistory> example = Example.of(ah);
        List<AcademicHistory> list = academicHistoryRepository.findAll(example);
        List<AcademicHistory> ls = new ArrayList<>();
        for(AcademicHistory aa : list) {
            AcademicHistory academicHistory = CommonUtil.createCopyProperties(aa, AcademicHistory.class);
            ls.add(academicHistory);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsacademicHistory/{id}")
    public Integer deleteAcademicHistory(@PathVariable Long id) {
        try {
            logger.debug("REST request to delete a AcademicHistory : {}", id);
            academicHistoryRepository.deleteById(id);
//            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();

    }
}
