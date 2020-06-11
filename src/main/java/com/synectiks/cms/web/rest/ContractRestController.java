package com.synectiks.cms.web.rest;


import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.CmsContractVo;
import com.synectiks.cms.domain.Contract;
import com.synectiks.cms.repository.ContractRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@RestController
@RequestMapping("/api")
public class ContractRestController {

    private final Logger log = LoggerFactory.getLogger(ContractRestController.class);

    private static final String ENTITY_NAME = "contract";

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmscontract-bulk-load")
    public List<CmsContractVo> bulkLoad(@RequestBody List<CmsContractVo> list) throws Exception {
        List<CmsContractVo> failedRecords = new ArrayList<>();
        for(CmsContractVo cmsContractVo: list) {
            try {
                createContract(cmsContractVo);
            }catch(Exception e) {
                failedRecords.add(cmsContractVo);
                log.error("Exception. Saving of contract failed. ", e);
            }
        }

        return failedRecords;
    }


    @PostMapping("/cmscontracts")
    public ResponseEntity<CmsContractVo> createContract(@Valid @RequestBody CmsContractVo cmsContractVo) throws Exception {
        log.debug("REST request to save a contract : {}", cmsContractVo);
        if (cmsContractVo.getId() != null) {
            throw new BadRequestAlertException("A new contract cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Contract contract = CommonUtil.createCopyProperties(cmsContractVo, Contract.class);
        Contract result = contractRepository.save(contract);
        CmsContractVo vo = CommonUtil.createCopyProperties(contract, CmsContractVo.class);
        return ResponseEntity.created(new URI("/api/cmscontracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @PutMapping("/cmscontracts")
    public ResponseEntity<CmsContractVo> updateContract(@Valid @RequestBody CmsContractVo cmsContractVo) throws URISyntaxException {
        log.debug("REST request to update a contract : {}", cmsContractVo);
        if (cmsContractVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Contract contract = CommonUtil.createCopyProperties(cmsContractVo, Contract.class);
        Contract result = contractRepository.save(contract);
        CmsContractVo vo = CommonUtil.createCopyProperties(contract, CmsContractVo.class);
        return ResponseEntity.created(new URI("/api/cmscontracts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmscontracts")
    public List<CmsContractVo> getAllContracts(@RequestParam Map<String, String> dataMap) {
        List<Contract> list = null;
        List<CmsContractVo> ls = null;

        Contract obj = new Contract();
        boolean isFilter = false;
        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
            obj.setId(Long.parseLong(dataMap.get("id")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("vendorName"))) {
            isFilter = true;
            String name = dataMap.get("vendorName");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setVendorName(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.contractRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.contractRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(Contract tr: list) {
            CmsContractVo vo = CommonUtil.createCopyProperties(tr, CmsContractVo.class);
            ls.add(vo);
        }

        return ls;
    }


    @GetMapping("/cmscontracts/{id}")
    public ResponseEntity<CmsContractVo> getContract(@PathVariable Long id) {
        log.debug("REST request to get contract : {}", id);
        Optional<Contract> contractDTO = contractRepository.findById(id);
        CmsContractVo vo = null;
        if(contractDTO.isPresent()) {
            Contract tr = contractDTO.get();
            vo = CommonUtil.createCopyProperties(tr,  CmsContractVo.class);
        }else {
            vo = new CmsContractVo();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsContractVo> getContractListByName(String name) {
        Contract contract = null;
        if(!CommonUtil.isNullOrEmpty(name)) {
            contract = new Contract();
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    contract.setVendorName(token.nextToken());
                }
                cnt++;
            }
        }
        log.debug("REST request to get Contract by name : {}", name);
        List<Contract> list = null;
        if(contract != null) {
            list = contractRepository.findAll(Example.of(contract));
        }else {
            list = Collections.emptyList();
        }

        List<CmsContractVo> ls = new ArrayList<>();
        for(Contract st: list) {
            CmsContractVo vo = CommonUtil.createCopyProperties(st, CmsContractVo.class);
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmscontracts/{id}")
    public Integer deleteContract(@PathVariable Long id) {
        try {
            log.debug("REST request to delete a contract : {}", id);
            this.contractRepository.deleteById(id);
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();
    }
}
