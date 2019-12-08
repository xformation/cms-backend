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
import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.entities.CmsBatchVo;
import com.synectiks.cms.entities.Department;
import com.synectiks.cms.repositories.BatchRepository;
import com.synectiks.cms.repositories.DepartmentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Batch.
 */
@RestController
@RequestMapping("/api")
public class BatchRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final String ENTITY_NAME = "batch";
	
	@Autowired
	private BatchRepository batchRepository;
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsbatches")
	public ResponseEntity<CmsBatchVo> createBatch(@Valid @RequestBody CmsBatchVo cmsBatchVo) throws URISyntaxException {
		logger.debug("REST request to save a batch : {}", cmsBatchVo);
        if (cmsBatchVo.getId() != null) {
            throw new BadRequestAlertException("A new batch cannot have an ID which already exists", ENTITY_NAME, "idexists");
        }
        
        Batch batch = CommonUtil.createCopyProperties(cmsBatchVo, Batch.class);
        if(cmsBatchVo.getDepartmentId()!= null) {
        	Department dt = null;
        	try {
        		dt = this.departmentRepository.getOne(cmsBatchVo.getDepartmentId());
        		batch.setDepartment(dt);
        	}catch(EntityNotFoundException e) {
        		logger.warn("Batch create. Department not found. Department id: "+cmsBatchVo.getDepartmentId());
        	}
        }
        
        batch = batchRepository.save(batch);
        cmsBatchVo = CommonUtil.createCopyProperties(batch, CmsBatchVo.class);
        return ResponseEntity.created(new URI("/api/cmsbranches/" + cmsBatchVo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, cmsBatchVo.getId().toString()))
            .body(cmsBatchVo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmsbatches")
	public ResponseEntity<CmsBatchVo> updateBatch(@Valid @RequestBody CmsBatchVo cmsBatchVo) throws URISyntaxException {
		logger.debug("REST request to update a batch : {}", cmsBatchVo);
        if (cmsBatchVo.getId() == null) {
            throw new BadRequestAlertException("Invalid batch id", ENTITY_NAME, "idnull");
        }
        
        Batch batch = CommonUtil.createCopyProperties(cmsBatchVo, Batch.class);
        if(cmsBatchVo.getDepartmentId()!= null) {
        	Department dt = null;
        	try {
        		dt = this.departmentRepository.getOne(cmsBatchVo.getDepartmentId());
        		batch.setDepartment(dt);
        	}catch(EntityNotFoundException e) {
        		logger.warn("Batch update. Department not found. Department id: "+cmsBatchVo.getDepartmentId());
        	}
        }
        
        batch = batchRepository.save(batch);
        cmsBatchVo = CommonUtil.createCopyProperties(batch, CmsBatchVo.class);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsBatchVo.getId().toString()))
                .body(cmsBatchVo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsbatches")
    public List<CmsBatchVo> getAllBatches() {
		logger.debug("REST request to get all the batches.");
		List<Batch> list = this.batchRepository.findAll();
		List<CmsBatchVo> ls = new ArrayList<>();
		for(Batch bth : list) {
			CmsBatchVo vo = CommonUtil.createCopyProperties(bth, CmsBatchVo.class);
			vo.setDepartmentId(bth.getDepartment() != null ? bth.getDepartment().getId() : null);
	        ls.add(vo);
		}
        return ls;
    }

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsbatches-departmentid/{id}")
	public List<CmsBatchVo> getAllBatchesByDepartmentId(@PathVariable Long id){
		if(!this.departmentRepository.existsById(id)) {
			return Collections.emptyList();
		}
		Department dt = this.departmentRepository.findById(id).get();
		
		Batch batch = new Batch();
		batch.setDepartment(dt);
		
		Example<Batch> example = Example.of(batch);
		List<Batch> list = this.batchRepository.findAll(example);
		
		List<CmsBatchVo> ls = new ArrayList<>();
		for(Batch bth : list) {
			CmsBatchVo vo = CommonUtil.createCopyProperties(bth, CmsBatchVo.class);
			vo.setDepartmentId(bth.getDepartment() != null ? bth.getDepartment().getId() : null);
	        ls.add(vo);
		}
        return ls;
	}
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsbatches/{id}")
    public Integer deleteBatch(@PathVariable Long id) {
    	try {
	    		logger.debug("REST request to delete a batch : {}", id);
	        	this.batchRepository.deleteById(id);
    	}catch(Exception e) {
    		return HttpStatus.FAILED_DEPENDENCY.value();
    	}
    	return HttpStatus.OK.value();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatchenums")
    public List<CmsBatchVo> getAllCmsBatchEnums() {
		logger.debug("Get all batches/years.");
		List<CmsBatchVo> ls = this.commonService.getAllBatches();
        return ls;
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmsbatchenums/{id}")
    public ResponseEntity<CmsBatchVo> getCmsBatchEnums(@PathVariable Long id) throws Exception {
        logger.debug("Get a batch/year of given id : "+id);
        CmsBatchVo vo = this.commonService.getCmsBatchVo(id);
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }
    
}
