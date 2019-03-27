package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing Branch.
 */
@RestController
@RequestMapping("/api")
public class BranchRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ENTITY_NAME = "branch";
	
	@Autowired
	private BranchRepository branchRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsbranches")
	public ResponseEntity<Branch> createBranch(@RequestBody Branch branch) throws URISyntaxException {
		logger.info("REST request to create a new branch.", branch);
        if (branch.getId() != null) {
            throw new BadRequestAlertException("A new branch cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        Branch result = branchRepository.save(branch);
        return ResponseEntity.created(new URI("/api/cmsbranches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
	}
	
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsbranches")
    public List<Branch> getAllBranches() {
		logger.debug("REST request to get all the college records.");
        return branchRepository.findAll();
    }

}
