package com.synectiks.cms.web.rest;


import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.repository.CountryRepository;
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
public class AdmissionApplicationPersonalDetailsRestController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String ENTITY_NAME = "admissionApplication";

    @Autowired
    private AdmissionApplicationRepository admissionApplicationRepository;

    @Autowired
    private CountryRepository countryRepository;

    @RequestMapping(method = RequestMethod.POST, value = "/cmspersonal-details")
    public ResponseEntity<CmsAdmissionApplicationVo> createPersonaldetails(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws URISyntaxException {
        logger.info("REST request to create a new personal details.", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() != null) {
            throw new BadRequestAlertException("A new personal details cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        AdmissionApplication a = new AdmissionApplication();
        Country c = this.countryRepository.findById(cmsAdmissionApplicationVo.getCountryId()).get();
        a.setStudentName(cmsAdmissionApplicationVo.getStudentName());
        a.setStudentMiddleName(cmsAdmissionApplicationVo.getStudentMiddleName());
        a.setStudentLastName(cmsAdmissionApplicationVo.getStudentLastName());
        a.setFatherName(cmsAdmissionApplicationVo.getFatherName());
        a.setFatherMiddleName(cmsAdmissionApplicationVo.getFatherMiddleName());
        a.setFatherLastName(cmsAdmissionApplicationVo.getFatherLastName());
        a.setMotherName(cmsAdmissionApplicationVo.getMotherName());
        a.setMotherMiddleName(cmsAdmissionApplicationVo.getMotherMiddleName());
        a.setMotherLastName(cmsAdmissionApplicationVo.getMotherLastName());
        a.setContactNumber(cmsAdmissionApplicationVo.getContactNumber());;
        a.setAlternateMobileNumber(cmsAdmissionApplicationVo.getAlternateMobileNumber());
        a.setDateOfBirth(cmsAdmissionApplicationVo.getDateOfBirth());
        a.setEmail(cmsAdmissionApplicationVo.getEmail());
        a.setSex(cmsAdmissionApplicationVo.getSex());
        a.setCountry(c);
        AdmissionApplication result = admissionApplicationRepository.save(a);
        cmsAdmissionApplicationVo.setId(result.getId());
        return ResponseEntity.created(new URI("/api/cmsadmissionApplications" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(cmsAdmissionApplicationVo);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/cmspersonal-details")
    public ResponseEntity<CmsAdmissionApplicationVo> updatePersonaldetails(@Valid @RequestBody CmsAdmissionApplicationVo cmsAdmissionApplicationVo) throws URISyntaxException {
        logger.info("REST request to update existing personal details.", cmsAdmissionApplicationVo);
        if (cmsAdmissionApplicationVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdmissionApplication a = new AdmissionApplication();
        Country c = this.countryRepository.findById(cmsAdmissionApplicationVo.getCountryId()).get();
        a.setStudentName(cmsAdmissionApplicationVo.getStudentName());
        a.setStudentMiddleName(cmsAdmissionApplicationVo.getStudentMiddleName());
        a.setStudentLastName(cmsAdmissionApplicationVo.getStudentLastName());
        a.setFatherName(cmsAdmissionApplicationVo.getFatherName());
        a.setFatherMiddleName(cmsAdmissionApplicationVo.getFatherMiddleName());
        a.setFatherLastName(cmsAdmissionApplicationVo.getFatherLastName());
        a.setMotherName(cmsAdmissionApplicationVo.getMotherName());
        a.setMotherMiddleName(cmsAdmissionApplicationVo.getMotherMiddleName());
        a.setMotherLastName(cmsAdmissionApplicationVo.getMotherLastName());
        a.setContactNumber(cmsAdmissionApplicationVo.getContactNumber());;
        a.setAlternateMobileNumber(cmsAdmissionApplicationVo.getAlternateMobileNumber());
        a.setDateOfBirth(cmsAdmissionApplicationVo.getDateOfBirth());
        a.setEmail(cmsAdmissionApplicationVo.getEmail());
        a.setSex(cmsAdmissionApplicationVo.getSex());
        a.setCountry(c);
        a.setId(cmsAdmissionApplicationVo.getId());
        AdmissionApplication result = admissionApplicationRepository.save(a);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAdmissionApplicationVo.getId().toString()))
            .body(cmsAdmissionApplicationVo);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cmspersonal-details")
    public List<CmsAdmissionApplicationVo> getAllPersonaldetails() {
        logger.debug("REST request to get all the personal details.");
        List<AdmissionApplication> list = admissionApplicationRepository.findAll();
        List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
        for(AdmissionApplication aa : list) {
            CmsAdmissionApplicationVo vo = new CmsAdmissionApplicationVo();
            vo.setStudentName(aa.getStudentName());
            vo.setStudentMiddleName(aa.getStudentMiddleName());
            vo.setStudentLastName(aa.getStudentLastName());
            vo.setFatherName(aa.getFatherName());
            vo.setFatherMiddleName(aa.getFatherMiddleName());
            vo.setFatherLastName(aa.getFatherLastName());
            vo.setMotherName(aa.getMotherName());
            vo.setMotherMiddleName(aa.getMotherMiddleName());
            vo.setMotherLastName(aa.getMotherLastName());
            vo.setContactNumber(aa.getContactNumber());;
            vo.setAlternateMobileNumber(aa.getAlternateMobileNumber());
            vo.setDateOfBirth(aa.getDateOfBirth());
            vo.setEmail(aa.getEmail());
            vo.setSex(aa.getSex());
            vo.setCountry(aa.getCountry());
            vo.setId(aa.getId());
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.GET, value = "/cmspersonal-details/{id}")
    public List<CmsAdmissionApplicationVo> getAllPersonalDetails(@PathVariable Long id){
        AdmissionApplication a = new AdmissionApplication();
        Example<AdmissionApplication> example = Example.of(a);
        List<AdmissionApplication> list = admissionApplicationRepository.findAll(example);
        List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
        for(AdmissionApplication aa : list) {
            CmsAdmissionApplicationVo vo = CommonUtil.createCopyProperties(aa, CmsAdmissionApplicationVo.class);
            if(aa.getCountry() != null) {
                vo.setCountryId(aa.getCountry().getId());
            }
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmspersonal-details/{id}")
    public Integer deletePersonalDetails(@PathVariable Long id) {
        try {
            logger.debug("REST request to delete a PersonalDetails : {}", id);
            admissionApplicationRepository.deleteById(id);
//            return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();

    }

}
