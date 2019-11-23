package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.commons.entities.cms.Batch;
import com.synectiks.commons.entities.cms.CmsSectionVo;
import com.synectiks.commons.entities.cms.Section;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Section.
 */
@RestController
@RequestMapping("/api")
public class SectionRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String ENTITY_NAME = "section";
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private SectionRepository sectionRepository;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmssections")
	public ResponseEntity<CmsSectionVo> createSection(@Valid @RequestBody CmsSectionVo cmsSectionVo) throws URISyntaxException {
		logger.debug("REST request to save a section : {}", cmsSectionVo);
        if (cmsSectionVo.getId() != null) {
            throw new BadRequestAlertException("A new section cannot have an ID which already exists", ENTITY_NAME, "idexists");
        }
        
        Section section = CommonUtil.createCopyProperties(cmsSectionVo, Section.class);
        if(cmsSectionVo.getBatchId()!= null) {
        	Batch batch = null;
        	try {
        		batch = this.batchRepository.getOne(cmsSectionVo.getBatchId());
        		section.setBatch(batch);
        	}catch(EntityNotFoundException e) {
        		logger.warn("Section create. Batch not found. Batch id: "+cmsSectionVo.getBatchId());
        	}
        }
        
        section = this.sectionRepository.save(section);
        cmsSectionVo = CommonUtil.createCopyProperties(section, CmsSectionVo.class);
        cmsSectionVo.setId(section.getBatch() != null ? section.getBatch().getId() : null );
        return ResponseEntity.created(new URI("/api/cmssections/" + cmsSectionVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsSectionVo.getId().toString()))
            .body(cmsSectionVo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmssections")
	public ResponseEntity<CmsSectionVo> updateSection(@Valid @RequestBody CmsSectionVo cmsSectionVo) throws URISyntaxException {
		logger.debug("REST request to update a section : {}", cmsSectionVo);
        if (cmsSectionVo.getId() == null) {
            throw new BadRequestAlertException("Invalid section id", ENTITY_NAME, "idnull");
        }
        
        Section section = CommonUtil.createCopyProperties(cmsSectionVo, Section.class);
        if(cmsSectionVo.getBatchId()!= null) {
        	Batch batch = null;
        	try {
        		batch = this.batchRepository.getOne(cmsSectionVo.getBatchId());
        		section.setBatch(batch);
        	}catch(EntityNotFoundException e) {
        		logger.warn("Section create. Batch not found. Batch id: "+cmsSectionVo.getBatchId());
        	}
        }
        
        section = this.sectionRepository.save(section);
        cmsSectionVo = CommonUtil.createCopyProperties(section, CmsSectionVo.class);
        cmsSectionVo.setId(section.getBatch() != null ? section.getBatch().getId() : null );
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsSectionVo.getId().toString()))
                .body(cmsSectionVo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmssections")
    public List<CmsSectionVo> getAllSections() {
		logger.debug("REST request to get all the sections.");
		List<Section> list = this.sectionRepository.findAll();
		List<CmsSectionVo> ls = new ArrayList<>();
		for(Section sec : list) {
			CmsSectionVo vo = CommonUtil.createCopyProperties(sec, CmsSectionVo.class);
			vo.setBatchId(sec.getBatch() != null ? sec.getBatch().getId() : null);
	        ls.add(vo);
		}
        return ls;
    }

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmssections-batchid/{id}")
	public List<CmsSectionVo> getAllSectionsByBatchId(@PathVariable Long id){
		if(!this.batchRepository.existsById(id)) {
			return Collections.emptyList();
		}
		Batch bth = this.batchRepository.findById(id).get();
		Section section = new Section();
		section.setBatch(bth);
		
		Example<Section> example = Example.of(section);
		List<Section> list = this.sectionRepository.findAll(example);
		
		List<CmsSectionVo> ls = new ArrayList<>();
		for(Section sec : list) {
			CmsSectionVo vo = CommonUtil.createCopyProperties(sec, CmsSectionVo.class);
			vo.setBatchId(sec.getBatch() != null ? sec.getBatch().getId() : null);
	        ls.add(vo);
		}
        return ls;
	}
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmssections/{id}")
    public Integer deleteSection(@PathVariable Long id) {
    	try {
	    		logger.debug("REST request to delete a section : {}", id);
	        	this.sectionRepository.deleteById(id);
    	}catch(Exception e) {
    		return HttpStatus.FAILED_DEPENDENCY.value();
    	}
    	return HttpStatus.OK.value();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssectionenums")
    public List<CmsSectionVo> getAllCmsSectionEnums() {
		logger.debug("Get all sections.");
		List<CmsSectionVo> ls = this.commonService.getAllSections();
        return ls;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssectionenums/{id}")
    public ResponseEntity<CmsSectionVo> getCmsSectionEnums(@PathVariable Long id) throws Exception {
        logger.debug("Get a section", id);
        CmsSectionVo vo = this.commonService.getCmsSectionVo(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }
    
}
