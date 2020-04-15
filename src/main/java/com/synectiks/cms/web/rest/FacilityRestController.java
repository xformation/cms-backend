package com.synectiks.cms.web.rest;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsFacility;
import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.repository.FacilityRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
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
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api")
public class FacilityRestController {

    private final Logger log = LoggerFactory.getLogger(FacilityRestController.class);

    private static final String ENTITY_NAME = "facility";

    @Autowired
    private FacilityRepository facilityRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmsfacilities-bulk-load")
    public List<CmsFacility> bulkLoad(@RequestBody List<CmsFacility> list) throws Exception {
        List<CmsFacility> failedRecords = new ArrayList<>();
        for(CmsFacility cmsFacility: list) {
            try {
                createFacility(cmsFacility);
            }catch(Exception e) {
                failedRecords.add(cmsFacility);
                log.error("Exception. Saving of facility failed. ", e);
            }
        }

        return failedRecords;
    }


    @PostMapping("/cmsfacilities")
    public ResponseEntity<CmsFacility> createFacility(@Valid @RequestBody CmsFacility cmsFacility) throws Exception {
        log.debug("REST request to save a facility : {}", cmsFacility);
        if (cmsFacility.getId() != null) {
            throw new BadRequestAlertException("A new facility cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Facility facility = CommonUtil.createCopyProperties(cmsFacility, Facility.class);
        Facility result = facilityRepository.save(facility);
        CmsFacility vo = CommonUtil.createCopyProperties(facility, CmsFacility.class);
        return ResponseEntity.created(new URI("/api/cmsfacilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }

    @PutMapping("/cmsfacilities")
    public ResponseEntity<CmsFacility> updateFacility(@Valid @RequestBody CmsFacility cmsFacility) throws URISyntaxException {
        log.debug("REST request to update a facility : {}", cmsFacility);
        if (cmsFacility.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Facility facility = CommonUtil.createCopyProperties(cmsFacility, Facility.class);
        Facility result = facilityRepository.save(facility);
        CmsFacility vo = CommonUtil.createCopyProperties(facility, CmsFacility.class);
        return ResponseEntity.created(new URI("/api/cmsfacilities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmsfacilities")
    public List<CmsFacility> getAllFacilities(@RequestParam Map<String, String> dataMap) {
        List<Facility> list = null;
        List<CmsFacility> ls = null;

        Facility obj = new Facility();
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
        if(!CommonUtil.isNullOrEmpty(dataMap.get("academicYearId"))) {

            obj.setAcademicYearId(Long.parseLong(dataMap.get("academicYearId")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("name"))) {
            isFilter = true;
            String name = dataMap.get("name");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setName(token.nextToken());
                }
                cnt++;
            }

        }

        if(isFilter) {
            list = this.facilityRepository.findAll(Example.of(obj), Sort.by(Sort.Direction.DESC, "id"));
        }else {
            list = this.facilityRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(Facility st: list) {
            CmsFacility vo = CommonUtil.createCopyProperties(st, CmsFacility.class);
            ls.add(vo);
        }


        return ls;
    }


    @GetMapping("/cmsfacilities/{id}")
    public ResponseEntity<CmsFacility> getFacility(@PathVariable Long id) {
        log.debug("REST request to get Facility : {}", id);
        Optional<Facility> facilityDTO = facilityRepository.findById(id);
        CmsFacility vo = null;
        if(facilityDTO.isPresent()) {
            Facility st = facilityDTO.get();
            vo = CommonUtil.createCopyProperties(st,  CmsFacility.class);
        }else {
            vo = new CmsFacility();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsFacility> getFacilityListByName(String name) {
        Facility facility = null;
        if(!CommonUtil.isNullOrEmpty(name)) {
            facility = new Facility();
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    facility.setName(token.nextToken());
                }
                cnt++;
            }
        }
        log.debug("REST request to get Facility by name : {}", name);
        List<Facility> list = null;
        if(facility != null) {
            list = facilityRepository.findAll(Example.of(facility));
        }else {
            list = Collections.emptyList();
        }

        List<CmsFacility> ls = new ArrayList<>();
        for(Facility st: list) {
            CmsFacility vo = CommonUtil.createCopyProperties(st, CmsFacility.class);
            ls.add(vo);
        }
        return ls;
    }


    @DeleteMapping("/cmsfacilities/{id}")
    public Integer deleteFacility(@PathVariable Long id) {
        log.debug("REST request to delete a Facility : {}", id);
        try {
            Facility st = new Facility();
            st.setId(id);
            st.setStatus(Status.DEACTIVE);
            this.facilityRepository.save(st);
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();
    }

}
