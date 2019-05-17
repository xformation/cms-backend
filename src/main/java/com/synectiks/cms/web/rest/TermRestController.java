package com.synectiks.cms.web.rest;


import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.CmsTermVo;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class TermRestController {

    private final Logger logger = LoggerFactory.getLogger(TermRestController.class);

    private static final String ENTITY_NAME = "term";

    @Autowired
    private TermRepository termRepository;

    @Autowired
    private AcademicYearRepository academicYearRepository;
    
    @RequestMapping(method = RequestMethod.POST, value = "/cmsterms")
    public ResponseEntity<CmsTermVo> createTerm(@Valid @RequestBody CmsTermVo cmsTermVo) throws Exception {
        logger.debug("REST request to save an Term : {}", cmsTermVo);
        if (cmsTermVo.getId() != null) {
            throw new BadRequestAlertException("A new term cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        Term tm = CommonUtil.createCopyProperties(cmsTermVo, Term.class);

        tm = termRepository.save(tm);
        String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getStartDate());
        String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getEndDate());

        cmsTermVo.setId(tm.getId());
        cmsTermVo.setStrStartDate(stDt);
        cmsTermVo.setStrEndDate(enDt);
        return ResponseEntity.created(new URI("/api/terms/" + cmsTermVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsTermVo.getId().toString()))
            .body(cmsTermVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsterms")
    public ResponseEntity<CmsTermVo> updateTerm(@Valid @RequestBody CmsTermVo cmsTermVo) throws Exception {
        logger.debug("REST request to update an Term : {}", cmsTermVo);
        if (cmsTermVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Term tm = CommonUtil.createCopyProperties(cmsTermVo, Term.class);
        tm = termRepository.save(tm);
        String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getStartDate());
        String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getEndDate());

        cmsTermVo.setStrStartDate(stDt);
        cmsTermVo.setStrEndDate(enDt);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsTermVo.getId().toString()))
            .body(cmsTermVo);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsterms")
    public List<CmsTermVo> getAllTerms() throws Exception {
        logger.debug("REST request to get all the Terms");
        List<Term> list = termRepository.findAll();
        List<CmsTermVo> ls = new ArrayList<>();
        for(Term tm: list) {
            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getStartDate());
            String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getEndDate());
            CmsTermVo ctm = CommonUtil.createCopyProperties(tm, CmsTermVo.class);
            ctm.setStrStartDate(stDt);
            ctm.setStrEndDate(enDt);
            ls.add(ctm);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmsterms/{id}")
    public ResponseEntity<CmsTermVo> getTerm(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an Term : {}", id);
        Optional<Term> tm = termRepository.findById(id);
        CmsTermVo ctm = new CmsTermVo();
        if(tm.isPresent()) {
            String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.get().getStartDate());
            String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.get().getEndDate());
            ctm = CommonUtil.createCopyProperties(tm.get(), CmsTermVo.class);
            ctm.setStrStartDate(stDt);
            ctm.setStrEndDate(enDt);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(ctm));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsterms-by_academicyearid")
    public List<CmsTermVo> getTermByAcademicYearId(@RequestParam Map<String, String> dataMap) throws Exception{
    	if(!dataMap.containsKey("academicYearId")) {
    		logger.warn("Academic year id is not provided. Returning empty terms list");
    		return Collections.emptyList();
    	}
    	List<CmsTermVo> ls = new ArrayList<>();
    	Long id = Long.valueOf(dataMap.get("academicYearId"));
    	Optional<AcademicYear> oay = this.academicYearRepository.findById(id); 
    	
    	if(oay.isPresent()) {
//    		AcademicYear ay = this.academicYearRepository.findById(id).get();
    		logger.debug("Terms based on academic year. AcademicYear :"+oay.get());
    		Term term = new Term();
    		term.setAcademicyear(oay.get());
    		Example<Term> exm = Example.of(term);
    		List<Term> list = this.termRepository.findAll(exm);
    		for(Term tm: list) {
                String stDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getStartDate());
                String enDt = DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, tm.getEndDate());
                CmsTermVo ctm = CommonUtil.createCopyProperties(tm, CmsTermVo.class);
                ctm.setStrStartDate(stDt);
                ctm.setStrEndDate(enDt);
                ls.add(ctm);
            }
    	}
    	logger.debug("Total terms retrieved: "+ls);
    	return ls;
    	
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsterms/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable Long id) {
        logger.debug("REST request to delete an Term : {}", id);
        termRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

