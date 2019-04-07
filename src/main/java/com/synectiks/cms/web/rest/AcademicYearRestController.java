package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.CmsAcademicYearVo;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing AcademicYear.
 */
@RestController
@RequestMapping("/api")
public class AcademicYearRestController {

    private final Logger logger = LoggerFactory.getLogger(AcademicYearRestController.class);

    private static final String ENTITY_NAME = "academicYear";

    @Autowired
    private AcademicYearRepository academicYearRepository;

    /**
     * POST  /academic-years : Create a new academicYear.
     *
     * @param cmsAcademicYearVo the cmsAcademicYearVo to create
     * @return the ResponseEntity with status 201 (Created) and with body the new cmsAcademicYearVo, or with status 400 (Bad Request) if the academicYear has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(method = RequestMethod.POST, value = "/cmsacademic-years")
    public ResponseEntity<CmsAcademicYearVo> createAcademicYear(@Valid @RequestBody CmsAcademicYearVo cmsAcademicYearVo) throws URISyntaxException {
        logger.debug("REST request to save an AcademicYear : {}", cmsAcademicYearVo);
        if (cmsAcademicYearVo.getId() != null) {
            throw new BadRequestAlertException("A new academicYear cannot have an ID which already exists.", ENTITY_NAME, "idexists");
        }
        AcademicYear ay = CommonUtil.createCopyProperties(cmsAcademicYearVo, AcademicYear.class);
        ay = academicYearRepository.save(ay);
        cmsAcademicYearVo.setId(ay.getId());
        return ResponseEntity.created(new URI("/api/academic-years/" + cmsAcademicYearVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsAcademicYearVo.getId().toString()))
            .body(cmsAcademicYearVo);
    }

    /**
     * PUT  /academic-years : Updates an existing academicYear.
     *
     * @param cmsAcademicYearVo the cmsAcademicYearVo to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated cmsAcademicYearVo,
     * or with status 400 (Bad Request) if the cmsAcademicYearVo is not valid,
     * or with status 500 (Internal Server Error) if the cmsAcademicYearVo couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/cmsacademic-years")
    public ResponseEntity<CmsAcademicYearVo> updateAcademicYear(@Valid @RequestBody CmsAcademicYearVo cmsAcademicYearVo) throws URISyntaxException {
        logger.debug("REST request to update an AcademicYear : {}", cmsAcademicYearVo);
        if (cmsAcademicYearVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AcademicYear ay = CommonUtil.createCopyProperties(cmsAcademicYearVo, AcademicYear.class);
        ay = academicYearRepository.save(ay);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAcademicYearVo.getId().toString()))
            .body(cmsAcademicYearVo);
    }

    /**
     * GET  /cmsacademic-years : get all the academicYears.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of academicYears in body
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years")
    public List<CmsAcademicYearVo> getAllAcademicYears() {
        logger.debug("REST request to get all the AcademicYears");
        List<AcademicYear> list = academicYearRepository.findAll();
        List<CmsAcademicYearVo> ls = new ArrayList<>();
        for(AcademicYear ay: list) {
        	CmsAcademicYearVo cay = CommonUtil.createCopyProperties(ay, CmsAcademicYearVo.class);
        	ls.add(cay);
        }
        return ls;
    }

    /**
     * GET  /academic-years/:id : get the "id" academicYear.
     *
     * @param id the id of the cmsAcademicYearVo to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the cmsAcademicYearVo, or with status 404 (Not Found)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/cmsacademic-years/{id}")
    public ResponseEntity<CmsAcademicYearVo> getAcademicYear(@PathVariable Long id) {
        logger.debug("REST request to get an AcademicYear : {}", id);
        Optional<AcademicYear> ay = academicYearRepository.findById(id);
        CmsAcademicYearVo cay = new CmsAcademicYearVo();
        if(ay.isPresent()) {
        	cay = CommonUtil.createCopyProperties(ay, CmsAcademicYearVo.class);
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(cay));
    }

    /**
     * DELETE  /academic-years/:id : delete the "id" academicYear.
     *
     * @param id the id of the cmsAcademicYearVo to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsacademic-years/{id}")
    public ResponseEntity<Void> deleteAcademicYear(@PathVariable Long id) {
        logger.debug("REST request to delete an AcademicYear : {}", id);
        academicYearRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

}
