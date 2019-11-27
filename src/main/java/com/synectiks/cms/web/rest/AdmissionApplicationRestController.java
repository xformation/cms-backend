package com.synectiks.cms.web.rest;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.domain.CmsAdmissionApplicationVo;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
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
public class AdmissionApplicationRestController {

    private final Logger logger = LoggerFactory.getLogger(AdmissionApplicationRestController.class);

    private static final String ENTITY_NAME = "admissionApplication";

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/cmsadmission-applications")
    public ResponseEntity<CmsAdmissionApplicationVo> createAdmissionApplication(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws Exception {
        logger.debug("REST request to save an AdmissionApplication : {}", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() != null) {
            throw new BadRequestAlertException("A new admissionApplication cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        AdmissionApplication aap = CommonUtil.createCopyProperties(cmsAdmissionApplicationVo, AdmissionApplication.class);

        aap = admissionApplicationRepository.save(aap);

        cmsAdmissionApplicationVo.setId(aap.getId());
        cmsAdmissionApplicationVo.setStrAdmissionDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aap.getAdmissionDate()))));
        return ResponseEntity.created(new URI("/api/admission-applications/" + cmsAdmissionApplicationVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsAdmissionApplicationVo.getId().toString()))
            .body(cmsAdmissionApplicationVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsadmission-applications")
    public ResponseEntity<CmsAdmissionApplicationVo> updateAdmissionApplication(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws Exception{
        logger.debug("REST request to update an AdmissionApplication : {}", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() == null){
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        AdmissionApplication aap = CommonUtil.createCopyProperties(cmsAdmissionApplicationVo, AdmissionApplication.class);
        aap = admissionApplicationRepository.save(aap);

        cmsAdmissionApplicationVo.setStrAdmissionDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aap.getAdmissionDate()))));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAdmissionApplicationVo.getId().toString()))
            .body(cmsAdmissionApplicationVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-applications")
    public List<CmsAdmissionApplicationVo> getAllAdmissionApplications() throws Exception {
        logger.debug("REST request to get all the AdmissionApplications");
        List<AdmissionApplication> list = admissionApplicationRepository.findAll();
        List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
        for(AdmissionApplication aap: list) {
            CmsAdmissionApplicationVo caap = CommonUtil.createCopyProperties(aap, CmsAdmissionApplicationVo.class);
            caap.setStrAdmissionDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aap.getAdmissionDate()))));;
            ls.add(caap);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsadmission-applications/{id}")
    public ResponseEntity<CmsAdmissionApplicationVo> getAdmissionApplication(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an AcademicYear : {}", id);
        Optional<AdmissionApplication> aap = admissionApplicationRepository.findById(id);
        CmsAdmissionApplicationVo caap = new CmsAdmissionApplicationVo();
        if(aap.isPresent()) {
            caap = CommonUtil.createCopyProperties(aap.get(), CmsAdmissionApplicationVo.class);
            caap.setStrAdmissionDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aap.get().getAdmissionDate()))));;

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(caap));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsadmission-applications/{id}")
    public ResponseEntity<Void> deleteAdmissionApplication(@PathVariable Long id) {
        logger.debug("REST request to delete an AdmissionApplication : {}", id);
        admissionApplicationRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
