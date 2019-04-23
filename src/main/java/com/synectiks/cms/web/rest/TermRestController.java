package com.synectiks.cms.web.rest;


import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsTermVo;
import com.synectiks.cms.domain.Term;
import com.synectiks.cms.repository.TermRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TermRestController {

    private final Logger logger = LoggerFactory.getLogger(TermRestController.class);

    private static final String ENTITY_NAME = "term";

    @Autowired
    private TermRepository termRepository;


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


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsterms/{id}")
    public ResponseEntity<Void> deleteTerm(@PathVariable Long id) {
        logger.debug("REST request to delete an Term : {}", id);
        termRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}

