package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;

import com.synectiks.cms.domain.CmsFeeCategory;
import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.repository.FeeCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsStudentVo;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller to manage FeeCategory.
 */
@RestController
@RequestMapping("/api")
public class FeeCategoryRestController {

    private final Logger log = LoggerFactory.getLogger(FeeCategoryRestController.class);

    private static final String ENTITY_NAME = "feeCategory";

    @Autowired
    private FeeCategoryRepository feeCategoryRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmsfeecategory-bulk-load")
    public List<CmsFeeCategory> bulkLoad(@RequestBody List<CmsFeeCategory> list) throws Exception {
        List<CmsFeeCategory> failedRecords = new ArrayList<>();
        for(CmsFeeCategory cmsFeeCategory: list) {
            try {
                createFeeCategory(cmsFeeCategory);
            }catch(Exception e) {
                failedRecords.add(cmsFeeCategory);
                log.error("Exception. Saving of feeCategory failed. ", e);
            }
        }

        return failedRecords;
    }


    @PostMapping("/cmsfee-categories")
    public ResponseEntity<CmsFeeCategory> createFeeCategory(@Valid @RequestBody CmsFeeCategory cmsFeeCategory) throws Exception {
        log.debug("REST request to save a feeCategory : {}", cmsFeeCategory);
        if (cmsFeeCategory.getId() != null) {
            throw new BadRequestAlertException("A new feeCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FeeCategory feeCategory = CommonUtil.createCopyProperties(cmsFeeCategory, FeeCategory.class);
        FeeCategory result = feeCategoryRepository.save(feeCategory);
        CmsFeeCategory vo = CommonUtil.createCopyProperties(feeCategory, CmsFeeCategory.class);
        return ResponseEntity.created(new URI("/api/cmsfee-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @PutMapping("/cmsfee-categories")
    public ResponseEntity<CmsFeeCategory> updateFeeCategory(@Valid @RequestBody CmsFeeCategory cmsFeeCategory) throws URISyntaxException {
        log.debug("REST request to update a feeCategory : {}", cmsFeeCategory);
        if (cmsFeeCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FeeCategory feeCategory = CommonUtil.createCopyProperties(cmsFeeCategory, FeeCategory.class);
        FeeCategory result = feeCategoryRepository.save(feeCategory);
        CmsFeeCategory vo = CommonUtil.createCopyProperties(feeCategory, CmsFeeCategory.class);
        return ResponseEntity.created(new URI("/api/cmsfee-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmsfee-categories")
    public List<CmsFeeCategory> getAllFeeCategories(@RequestParam Map<String, String> dataMap) {
        List<FeeCategory> list = null;
        List<CmsFeeCategory> ls = null;

        FeeCategory obj = new FeeCategory();
        boolean isFilter = false;
        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
            obj.setId(Long.parseLong(dataMap.get("id")));
            isFilter = true;
        }
        if(!CommonUtil.isNullOrEmpty(dataMap.get("branchId"))) {
//    		Branch branch = this.commonService.getBranchById(Long.parseLong(dataMap.get("branchId")));
            obj.setBranchId(Long.parseLong(dataMap.get("branchId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("categoryName"))) {
            isFilter = true;
            String name = dataMap.get("categoryName");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setCategoryName(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.feeCategoryRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
        }else {
            list = this.feeCategoryRepository.findAll(Sort.by(Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(FeeCategory fc: list) {
            CmsFeeCategory vo = CommonUtil.createCopyProperties(fc, CmsFeeCategory.class);
            ls.add(vo);
        }

        return ls;
    }


    @GetMapping("/cmsfee-categories/{id}")
    public ResponseEntity<CmsFeeCategory> getFeeCategory(@PathVariable Long id) {
        log.debug("REST request to get feeCategory : {}", id);
        Optional<FeeCategory> feeCategoryDTO = feeCategoryRepository.findById(id);
        CmsFeeCategory vo = null;
        if(feeCategoryDTO.isPresent()) {
            FeeCategory fc = feeCategoryDTO.get();
            vo = CommonUtil.createCopyProperties(fc,  CmsFeeCategory.class);
        }else {
            vo = new CmsFeeCategory();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsFeeCategory> getFeeCategoryListByName(String name) {
        FeeCategory feeCategory = null;
        if(!CommonUtil.isNullOrEmpty(name)) {
            feeCategory = new FeeCategory();
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    feeCategory.setCategoryName(token.nextToken());
                }
                cnt++;
            }
        }
        log.debug("REST request to get FeeCategory by name : {}", name);
        List<FeeCategory> list = null;
        if(feeCategory != null) {
            list = feeCategoryRepository.findAll(Example.of(feeCategory));
        }else {
            list = Collections.emptyList();
        }

        List<CmsFeeCategory> ls = new ArrayList<>();
        for(FeeCategory st: list) {
            CmsFeeCategory vo = CommonUtil.createCopyProperties(st, CmsFeeCategory.class);
            ls.add(vo);
        }
        return ls;
    }


    @DeleteMapping("/cmsfee-categories/{id}")
    public Integer deleteFeeCategory(@PathVariable Long id) {
        log.debug("REST request to delete a FeeCategory : {}", id);
        try {
            FeeCategory st = new FeeCategory();
            st.setId(id);
            st.setStatus(Status.DEACTIVE);
            this.feeCategoryRepository.save(st);
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();
    }


}
