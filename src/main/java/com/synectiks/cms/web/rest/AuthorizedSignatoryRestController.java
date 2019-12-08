package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.entities.AuthorizedSignatory;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.CmsAuthorizedSignatoryVo;
import com.synectiks.cms.entities.College;
import com.synectiks.cms.repositories.AuthorizedSignatoryRepository;
import com.synectiks.cms.repositories.BranchRepository;
import com.synectiks.cms.repositories.CollegeRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing AuthorizedSignatory.
 */
@RestController
@RequestMapping("/api")
public class AuthorizedSignatoryRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ENTITY_NAME = "AuthorizedSignatory";
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private AuthorizedSignatoryRepository authorizedSignatoryRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsauthorized-signatories")
	public ResponseEntity<CmsAuthorizedSignatoryVo> createAuthorizedSignatory(@Valid @RequestBody CmsAuthorizedSignatoryVo cmsAuthorizedSignatoryVo) throws URISyntaxException {
		logger.info("REST request to create a new AuthorizedSignatory.", cmsAuthorizedSignatoryVo);
        if (cmsAuthorizedSignatoryVo.getId() != null) {
            throw new BadRequestAlertException("A new AuthorizedSignatory cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        Branch b = this.branchRepository.findById(cmsAuthorizedSignatoryVo.getBranchId()).get();
        College clg = this.collegeRepository.findById(cmsAuthorizedSignatoryVo.getCollegeId()).get();
        
        AuthorizedSignatory as = CommonUtil.createCopyProperties(cmsAuthorizedSignatoryVo, AuthorizedSignatory.class);
        as.setBranch(b);
        as.setCollege(clg);
        
        as = authorizedSignatoryRepository.save(as);
        
        cmsAuthorizedSignatoryVo.setId(as.getId());
        return ResponseEntity.created(new URI("/api/cmsauthorized-signatories/" + as.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, as.getId().toString()))
            .body(cmsAuthorizedSignatoryVo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmsauthorized-signatories")
	public ResponseEntity<CmsAuthorizedSignatoryVo> updateAuthorizedSignatory(@Valid @RequestBody CmsAuthorizedSignatoryVo cmsAuthorizedSignatoryVo) throws URISyntaxException {
		logger.info("REST request to update existing authorizedsignatory.", cmsAuthorizedSignatoryVo);
		if (cmsAuthorizedSignatoryVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
		Branch b = this.branchRepository.findById(cmsAuthorizedSignatoryVo.getBranchId()).get();
        College clg = this.collegeRepository.findById(cmsAuthorizedSignatoryVo.getCollegeId()).get();
        
        AuthorizedSignatory as = CommonUtil.createCopyProperties(cmsAuthorizedSignatoryVo, AuthorizedSignatory.class);
        as.setBranch(b);
        as.setCollege(clg);
        
        as = authorizedSignatoryRepository.save(as);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsAuthorizedSignatoryVo.getId().toString()))
                .body(cmsAuthorizedSignatoryVo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsauthorized-signatories")
    public List<CmsAuthorizedSignatoryVo> getAllAuthorizedSignatories() {
		logger.debug("REST request to get all the authorizedsignatories.");
		List<AuthorizedSignatory> list = authorizedSignatoryRepository.findAll();
		List<CmsAuthorizedSignatoryVo> ls = new ArrayList<>();
		for(AuthorizedSignatory as : list) {
			CmsAuthorizedSignatoryVo vo = CommonUtil.createCopyProperties(as, CmsAuthorizedSignatoryVo.class);
			vo.setBranchId(as.getBranch().getId());
			vo.setCollegeId(as.getCollege().getId());
	        ls.add(vo);
		}
        return ls;
    }

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsauthorized-signatories-collegeid/{id}")
	public List<CmsAuthorizedSignatoryVo> getAllAllAuthorizedSignatoriesByCollegeId(@PathVariable Long id){
		if(!this.collegeRepository.existsById(id)) {
			return Collections.emptyList();
		}
		College college = this.collegeRepository.findById(id).get();
		AuthorizedSignatory as = new AuthorizedSignatory();
		as.setCollege(college);
		Example<AuthorizedSignatory> example = Example.of(as);
		List<AuthorizedSignatory> list = authorizedSignatoryRepository.findAll(example);
		List<CmsAuthorizedSignatoryVo> ls = new ArrayList<>();
		for(AuthorizedSignatory aus : list) {
			CmsAuthorizedSignatoryVo vo = CommonUtil.createCopyProperties(aus, CmsAuthorizedSignatoryVo.class);
			vo.setBranchId(aus.getBranch().getId());
			vo.setCollegeId(aus.getCollege().getId());
	        ls.add(vo);
		}
        return ls;
	}
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsauthorized-signatories/{id}")
    public ResponseEntity<Void> deleteAuthorizedSignatory(@PathVariable Long id) {
    	logger.debug("REST request to delete a authorizedsignatory : {}", id);
    	authorizedSignatoryRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
