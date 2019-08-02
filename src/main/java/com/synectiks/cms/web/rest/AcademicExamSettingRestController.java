package com.synectiks.cms.web.rest;


import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.domain.CmsAcademicExamSettingVo;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
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

import javax.mail.Header;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AcademicExamSettingRestController {

    private final Logger logger = LoggerFactory.getLogger(AcademicExamSettingRestController.class);

    private static final String ENTITY_NAME = "academicExamSetting";

    @Autowired
    private AcademicExamSettingRepository academicExamSettingRepository;

    @RequestMapping(method = RequestMethod.POST,value = "/cmsacdemic-exam-settings")
    public ResponseEntity<CmsAcademicExamSettingVo> createAcademicExamSetting(@Valid @RequestBody CmsAcademicExamSettingVo cmsAcademicExamSettingVo) throws Exception{
        logger.debug("REST request to save an AcademicExamSetting : {}", cmsAcademicExamSettingVo);
        if (cmsAcademicExamSettingVo.getId() !=null){
            throw new BadRequestAlertException("A new academicExamSetting cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        AcademicExamSetting aes = CommonUtil.createCopyProperties(cmsAcademicExamSettingVo, AcademicExamSetting.class);

        aes = academicExamSettingRepository.save(aes);

        cmsAcademicExamSettingVo.setId(aes.getId());
        cmsAcademicExamSettingVo.setStrexamDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aes.getExamDate()))));
        return ResponseEntity.created(new URI("/api/academi-exam-settings/" + cmsAcademicExamSettingVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsAcademicExamSettingVo.getId().toString()))
            .body(cmsAcademicExamSettingVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmsacademic-exam-settings")
    public ResponseEntity<CmsAcademicExamSettingVo> updateAcdemicExamSetting(@Valid @RequestBody CmsAcademicExamSettingVo cmsAcademicExamSettingVo) throws Exception{
        logger.debug("REST request to update an AcademicExamSetting : {}", cmsAcademicExamSettingVo);
        if (cmsAcademicExamSettingVo.getId() == null){
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        AcademicExamSetting aes = CommonUtil.createCopyProperties(cmsAcademicExamSettingVo, AcademicExamSetting.class);
        aes = academicExamSettingRepository.save(aes);

        cmsAcademicExamSettingVo.setStrexamDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aes.getExamDate()))));
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAcademicExamSettingVo.getId().toString()))
            .body(cmsAcademicExamSettingVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-exam-settings")
    public List<CmsAcademicExamSettingVo> getAllAcademicExamSettings() throws Exception {
        logger.debug("REST request to get all the AcademicExamSettings");
        List<AcademicExamSetting> list = academicExamSettingRepository.findAll();
        List<CmsAcademicExamSettingVo> ls = new ArrayList<>();
        for(AcademicExamSetting aes: list) {
            CmsAcademicExamSettingVo caes = CommonUtil.createCopyProperties(aes, CmsAcademicExamSettingVo.class);
            caes.setStrexamDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aes.getExamDate()))));
            ls.add(caes);
        }
        return ls;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-exam-settings/{id}")
    public ResponseEntity<CmsAcademicExamSettingVo> getAcademicExamSetting(@PathVariable Long id) throws Exception {
        logger.debug("REST request to get an AcademicYear : {}", id);
        Optional<AcademicExamSetting> aes = academicExamSettingRepository.findById(id);
        CmsAcademicExamSettingVo caes = new CmsAcademicExamSettingVo();
        if(aes.isPresent()) {
            caes = CommonUtil.createCopyProperties(aes.get(), CmsAcademicExamSettingVo.class);
            caes.setStrexamDate(DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_dd_MM_yyyy, CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.changeDateFormat(CmsConstants.DATE_FORMAT_yyyy_MM_dd, DateFormatUtil.converUtilDateFromLocaDate(aes.get().getExamDate()))));

        }
        return ResponseUtil.wrapOrNotFound(Optional.of(caes));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsacademic-exam-settings/{id}")
    public ResponseEntity<Void> deleteAcademicExamSetting(@PathVariable Long id) {
        logger.debug("REST request to delete an AcademicExamSetting : {}", id);
        academicExamSettingRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
