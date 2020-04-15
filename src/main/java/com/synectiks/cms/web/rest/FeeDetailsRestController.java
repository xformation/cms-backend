package com.synectiks.cms.web.rest;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.domain.CmsFeeDetails;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.FeeDetailsRepository;
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
public class FeeDetailsRestController {

    private final Logger log = LoggerFactory.getLogger(FeeDetailsRestController.class);

    private static final String ENTITY_NAME = "feeDetails";

    @Autowired
    private FeeDetailsRepository feeDetailsRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmsfeedetails-bulk-load")
    public List<CmsFeeDetails> bulkLoad(@RequestBody List<CmsFeeDetails> list) throws Exception {
        List<CmsFeeDetails> failedRecords = new ArrayList<>();
        for(CmsFeeDetails cmsFeeDetails: list) {
            try {
                createFeeDetails(cmsFeeDetails);
            }catch(Exception e) {
                failedRecords.add(cmsFeeDetails);
                log.error("Exception. Saving of feeDetails failed. ", e);
            }
        }

        return failedRecords;
    }


    @PostMapping("/cmsfee-details")
    public ResponseEntity<CmsFeeDetails> createFeeDetails(@Valid @RequestBody CmsFeeDetails cmsFeeDetails) throws Exception {
        log.debug("REST request to save a feeDetails : {}", cmsFeeDetails);
        if (cmsFeeDetails.getId() != null) {
            throw new BadRequestAlertException("A new feeDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeDetails feeDetails = CommonUtil.createCopyProperties(cmsFeeDetails, FeeDetails.class);
        FeeDetails result = feeDetailsRepository.save(feeDetails);
        CmsFeeDetails vo = CommonUtil.createCopyProperties(feeDetails, CmsFeeDetails.class);
        return ResponseEntity.created(new URI("/api/cmsfee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @PutMapping("/cmsfee-details")
    public ResponseEntity<CmsFeeDetails> updateFeeDetails(@Valid @RequestBody CmsFeeDetails cmsFeeDetails) throws URISyntaxException {
        log.debug("REST request to update a feeDetails : {}", cmsFeeDetails);
        if (cmsFeeDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeDetails feeDetails = CommonUtil.createCopyProperties(cmsFeeDetails, FeeDetails.class);
        FeeDetails result = feeDetailsRepository.save(feeDetails);
        CmsFeeDetails vo = CommonUtil.createCopyProperties(feeDetails, CmsFeeDetails.class);
        return ResponseEntity.created(new URI("/api/cmsfee-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmsfee-details")
    public List<CmsFeeDetails> getAllFeeDetails(@RequestParam Map<String, String> dataMap) {
        List<FeeDetails> list = null;
        List<CmsFeeDetails> ls = null;

        FeeDetails obj = new FeeDetails();
        boolean isFilter = false;
        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
            obj.setId(Long.parseLong(dataMap.get("id")));
            isFilter = true;
        }
        if(!CommonUtil.isNullOrEmpty(dataMap.get("batchId"))) {
            obj.setBatchId(Long.parseLong(dataMap.get("batchId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("departmentId"))) {
            obj.setDepartmentId(Long.parseLong(dataMap.get("departmentId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("feeParticularName"))) {
            isFilter = true;
            String name = dataMap.get("feeParticularName");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setFeeParticularsName(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.feeDetailsRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.feeDetailsRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(FeeDetails fc: list) {
            CmsFeeDetails vo = CommonUtil.createCopyProperties(fc, CmsFeeDetails.class);
            ls.add(vo);
        }

        return ls;
    }


    @GetMapping("/cmsfee-details/{id}")
    public ResponseEntity<CmsFeeDetails> getFeeDetails(@PathVariable Long id) {
        log.debug("REST request to get feeDetails : {}", id);
        Optional<FeeDetails> feeDetailsDTO = feeDetailsRepository.findById(id);
        CmsFeeDetails vo = null;
        if(feeDetailsDTO.isPresent()) {
            FeeDetails fc = feeDetailsDTO.get();
            vo = CommonUtil.createCopyProperties(fc,  CmsFeeDetails.class);
        }else {
            vo = new CmsFeeDetails();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsFeeDetails> getFeeDetailsListByName(String name) {
        FeeDetails feeDetails = null;
        if(!CommonUtil.isNullOrEmpty(name)) {
            feeDetails = new FeeDetails();
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    feeDetails.setFeeParticularsName(token.nextToken());
                }
                cnt++;
            }
        }
        log.debug("REST request to get FeeDetails by name : {}", name);
        List<FeeDetails> list = null;
        if(feeDetails != null) {
            list = feeDetailsRepository.findAll(Example.of(feeDetails));
        }else {
            list = Collections.emptyList();
        }

        List<CmsFeeDetails> ls = new ArrayList<>();
        for(FeeDetails st: list) {
            CmsFeeDetails vo = CommonUtil.createCopyProperties(st, CmsFeeDetails.class);
            ls.add(vo);
        }
        return ls;
    }


    @DeleteMapping("/cmsfee-details/{id}")
    public Integer deleteFeeDetails(@PathVariable Long id) {
        log.debug("REST request to delete a FeeDetails : {}", id);
        try {
            FeeDetails st = new FeeDetails();
            st.setId(id);
            st.setStatus(Status.DEACTIVE);
            this.feeDetailsRepository.save(st);
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();
    }
}
