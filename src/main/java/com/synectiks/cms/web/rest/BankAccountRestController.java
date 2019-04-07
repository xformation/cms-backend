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

import com.synectiks.cms.domain.BankAccounts;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsBankAccountsVo;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.repository.BankAccountsRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing BankAccount.
 */
@RestController
@RequestMapping("/api")
public class BankAccountRestController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String ENTITY_NAME = "BankAccounts";
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private BankAccountsRepository bankAccountsRepository;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsbank-accounts")
	public ResponseEntity<CmsBankAccountsVo> createBankAccounts(@Valid @RequestBody CmsBankAccountsVo cmsBankAccountsVo) throws URISyntaxException {
		logger.info("REST request to create a new bankaccount.", cmsBankAccountsVo);
        if (cmsBankAccountsVo.getId() != null) {
            throw new BadRequestAlertException("A new bankaccount cannot have an ID which already exits", ENTITY_NAME, "idexists");
        }
        Branch b = this.branchRepository.findById(cmsBankAccountsVo.getBranchId()).get();
        College clg = this.collegeRepository.findById(cmsBankAccountsVo.getCollegeId()).get();
        
        BankAccounts ba = CommonUtil.createCopyProperties(cmsBankAccountsVo, BankAccounts.class);
        ba.setBranch(b);
        ba.setCollege(clg);
        
        ba = bankAccountsRepository.save(ba);
        
        cmsBankAccountsVo.setId(ba.getId());
        return ResponseEntity.created(new URI("/api/cmsbank-accounts/" + ba.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, ba.getId().toString()))
            .body(cmsBankAccountsVo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/cmsbank-accounts")
	public ResponseEntity<CmsBankAccountsVo> updateBankAccounts(@Valid @RequestBody CmsBankAccountsVo cmsBankAccountsVo) throws URISyntaxException {
		logger.info("REST request to update existing bankaccount.", cmsBankAccountsVo);
		if (cmsBankAccountsVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
		Branch b = this.branchRepository.findById(cmsBankAccountsVo.getBranchId()).get();
        College clg = this.collegeRepository.findById(cmsBankAccountsVo.getCollegeId()).get();
        
        BankAccounts ba = CommonUtil.createCopyProperties(cmsBankAccountsVo, BankAccounts.class);
        ba.setBranch(b);
        ba.setCollege(clg);
        
        ba = bankAccountsRepository.save(ba);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, cmsBankAccountsVo.getId().toString()))
                .body(cmsBankAccountsVo);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsbank-accounts")
    public List<CmsBankAccountsVo> getAllBankAccounts() {
		logger.debug("REST request to get all the bankaccounts.");
		List<BankAccounts> list = bankAccountsRepository.findAll();
		List<CmsBankAccountsVo> ls = new ArrayList<>();
		for(BankAccounts ba : list) {
			CmsBankAccountsVo vo = CommonUtil.createCopyProperties(ba, CmsBankAccountsVo.class);
			vo.setBranchId(ba.getBranch().getId());
			vo.setCollegeId(ba.getCollege().getId());
	        ls.add(vo);
		}
        return ls;
    }

	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsbank-accounts-collegeid/{id}")
	public List<CmsBankAccountsVo> getAllBankAccountsByCollegeId(@PathVariable Long id){
		if(!this.collegeRepository.existsById(id)) {
			return Collections.emptyList();
		}
		College college = this.collegeRepository.findById(id).get();
		BankAccounts ba = new BankAccounts();
		ba.setCollege(college);
		Example<BankAccounts> example = Example.of(ba);
		List<BankAccounts> list = bankAccountsRepository.findAll(example);
		List<CmsBankAccountsVo> ls = new ArrayList<>();
		for(BankAccounts bas : list) {
			CmsBankAccountsVo vo = CommonUtil.createCopyProperties(bas, CmsBankAccountsVo.class);
			vo.setBranchId(bas.getBranch().getId());
			vo.setCollegeId(bas.getCollege().getId());
	        ls.add(vo);
		}
        return ls;
	}
	
	
    @RequestMapping(method = RequestMethod.DELETE, value = "/cmsbank-accounts/{id}")
    public ResponseEntity<Void> deleteBankAccounts(@PathVariable Long id) {
    	logger.debug("REST request to delete a bankaccount : {}", id);
    	bankAccountsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
