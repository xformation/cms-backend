package com.synectiks.cms.web.rest;


import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.domain.CmsAdmissionEnquiryVo;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
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
public class AdmissionEnquiryRestController {

    private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryRestController.class);

    private static final String ENTITY_NAME = "admissionEnquiry";

    @Autowired
    private AdmissionEnquiryRepository admissionEnquiryRepository;

    @RequestMapping(method = RequestMethod.POST,value = "/cmsadmission-enquiries")
    public ResponseEntity<CmsAdmissionEnquiryVo> createAdmissionEnquiry(@Valid @RequestBody CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) throws Exception{
        logger.debug("REST request to save an AdmissionEnquiry : {}", cmsAdmissionEnquiryVo);
        if (cmsAdmissionEnquiryVo.getId() !=null){
            throw new BadRequestAlertException("A new admissionEnquiry cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        AdmissionEnquiry ae = CommonUtil.createCopyProperties(cmsAdmissionEnquiryVo, AdmissionEnquiry.class);

        ae = admissionEnquiryRepository.save(ae);
        cmsAdmissionEnquiryVo.setId(ae.getId());
        cmsAdmissionEnquiryVo.setStrEnquiryDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getEnquiryDate()))));
        cmsAdmissionEnquiryVo.setStrUpdatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getUpdatedOn()))));
        return ResponseEntity.created(new URI("/api/admission-applications/" + cmsAdmissionEnquiryVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsAdmissionEnquiryVo.getId().toString()))
            .body(cmsAdmissionEnquiryVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsadmission-enquiries")
    public ResponseEntity<CmsAdmissionEnquiryVo> updateAdmissionEnquiry(@Valid @RequestBody CmsAdmissionEnquiryVo cmsAdmissionEnquiryVo) throws Exception{
        logger.debug("REST request to update an AdmissionEnquiry : {}", cmsAdmissionEnquiryVo);
        if (cmsAdmissionEnquiryVo.getId() == null){
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        AdmissionEnquiry ae = CommonUtil.createCopyProperties(cmsAdmissionEnquiryVo, AdmissionEnquiry.class);
        ae = admissionEnquiryRepository.save(ae);
        cmsAdmissionEnquiryVo.setStrEnquiryDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getEnquiryDate()))));
        cmsAdmissionEnquiryVo.setStrUpdatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getUpdatedOn()))));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAdmissionEnquiryVo.getId().toString()))
            .body(cmsAdmissionEnquiryVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-enquiries")
    public List<CmsAdmissionEnquiryVo> getAllAdmissionEnquiries() throws Exception {
        logger.debug("REST request to get all the AdmissionEnquiries");
        List<AdmissionEnquiry> list = admissionEnquiryRepository.findAll();
        List<CmsAdmissionEnquiryVo> ls = new ArrayList<>();
        for(AdmissionEnquiry ae: list) {
            CmsAdmissionEnquiryVo cae = CommonUtil.createCopyProperties(ae, CmsAdmissionEnquiryVo.class);
            cae.setStrEnquiryDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getEnquiryDate()))));
            cae.setStrUpdatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.getUpdatedOn()))));
            ls.add(cae);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-enquiries/{id}")
    public ResponseEntity<CmsAdmissionEnquiryVo> getAdmissionEnquiry(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an AcademicYear : {}", id);
        Optional<AdmissionEnquiry> ae = admissionEnquiryRepository.findById(id);
        CmsAdmissionEnquiryVo cae = new CmsAdmissionEnquiryVo();
        if(ae.isPresent()) {
            cae.setStrEnquiryDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.get().getEnquiryDate()))));
            cae.setStrUpdatedOn(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(ae.get().getUpdatedOn()))));
            cae = CommonUtil.createCopyProperties(ae.get(), CmsAdmissionEnquiryVo.class);

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cae));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsadmission-enquiries/{id}")
    public ResponseEntity<Void> deleteAdmissionEnquiry(@PathVariable Long id) {
        logger.debug("REST request to delete an AdmissionEnquiry : {}", id);
        admissionEnquiryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
