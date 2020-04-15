package com.synectiks.cms.web.rest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.validation.Valid;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.TransportRouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller to manage Student.
 */
@RestController
@RequestMapping("/api")
public class TransportRouteRestController {

    private final Logger log = LoggerFactory.getLogger(TransportRouteRestController.class);

    private static final String ENTITY_NAME = "transportRoute";

    @Autowired
    private TransportRouteRepository transportRouteRepository;

    @Autowired
    private CommonService commonService;

    @PostMapping("/cmstransport-bulk-load")
    public List<CmsTransportVo> bulkLoad(@RequestBody List<CmsTransportVo> list) throws Exception {
        List<CmsTransportVo> failedRecords = new ArrayList<>();
        for(CmsTransportVo cmsTransportVo: list) {
            try {
                createTransportRoute(cmsTransportVo);
            }catch(Exception e) {
                failedRecords.add(cmsTransportVo);
                log.error("Exception. Saving of transportRoute failed. ", e);
            }
        }

        return failedRecords;
    }


    @PostMapping("/cmstransport-routes")
    public ResponseEntity<CmsTransportVo> createTransportRoute(@Valid @RequestBody CmsTransportVo cmsTransportVo) throws Exception {
        log.debug("REST request to save a transportRoute : {}", cmsTransportVo);
        if (cmsTransportVo.getId() != null) {
            throw new BadRequestAlertException("A new transportRoute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TransportRoute transportRoute = CommonUtil.createCopyProperties(cmsTransportVo, TransportRoute.class);
        TransportRoute result = transportRouteRepository.save(transportRoute);
        CmsTransportVo vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportVo.class);
        return ResponseEntity.created(new URI("/api/cmstransport-routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @PutMapping("/cmstransport-routes")
    public ResponseEntity<CmsTransportVo> updateTransportRoute(@Valid @RequestBody CmsTransportVo cmsTransportVo) throws URISyntaxException {
        log.debug("REST request to update a transportRoute : {}", cmsTransportVo);
        if (cmsTransportVo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TransportRoute transportRoute = CommonUtil.createCopyProperties(cmsTransportVo, TransportRoute.class);
        TransportRoute result = transportRouteRepository.save(transportRoute);
        CmsTransportVo vo = CommonUtil.createCopyProperties(transportRoute, CmsTransportVo.class);
        return ResponseEntity.created(new URI("/api/cmstransport-routes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(vo);
    }


    @GetMapping("/cmstransport-routes")
    public List<CmsTransportVo> getAllTransportRoutes(@RequestParam Map<String, String> dataMap) {
        List<TransportRoute> list = null;
        List<CmsTransportVo> ls = null;

        TransportRoute obj = new TransportRoute();
        boolean isFilter = false;
        if(!CommonUtil.isNullOrEmpty(dataMap.get("id"))) {
            obj.setId(Long.parseLong(dataMap.get("id")));
            isFilter = true;
        }

        if(!CommonUtil.isNullOrEmpty(dataMap.get("routeName"))) {
            isFilter = true;
            String name = dataMap.get("routeName");
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    obj.setRouteName(token.nextToken());
                }
                cnt++;
            }
//        	ls = getStudentListByName(name) ;
        }
//        List<Teacher> list = null;
        if(isFilter) {
            list = this.transportRouteRepository.findAll(Example.of(obj), Sort.by(Direction.DESC, "id"));
        }else {
            list = this.transportRouteRepository.findAll(Sort.by(Direction.DESC, "id"));
        }
        ls = new ArrayList<>();
        for(TransportRoute tr: list) {
            CmsTransportVo vo = CommonUtil.createCopyProperties(tr, CmsTransportVo.class);
            ls.add(vo);
        }

        return ls;
    }


    @GetMapping("/cmstransport-routes/{id}")
    public ResponseEntity<CmsTransportVo> getTransportRoute(@PathVariable Long id) {
        log.debug("REST request to get transportRoute : {}", id);
        Optional<TransportRoute> transportRouteDTO = transportRouteRepository.findById(id);
        CmsTransportVo vo = null;
        if(transportRouteDTO.isPresent()) {
            TransportRoute tr = transportRouteDTO.get();
            vo = CommonUtil.createCopyProperties(tr,  CmsTransportVo.class);
        }else {
            vo = new CmsTransportVo();
        }
        return ResponseUtil.wrapOrNotFound(Optional.of(vo));
    }

    public List<CmsTransportVo> getTransportRouteListByName(String name) {
        TransportRoute transportRoute = null;
        if(!CommonUtil.isNullOrEmpty(name)) {
            transportRoute = new TransportRoute();
            StringTokenizer token = new StringTokenizer(name, " ");
            int cnt = 0;
            while(token.hasMoreTokens()) {
                if(cnt == 0) {
                    transportRoute.setRouteName(token.nextToken());
                }
                cnt++;
            }
        }
        log.debug("REST request to get TransportRoute by name : {}", name);
        List<TransportRoute> list = null;
        if(transportRoute != null) {
            list = transportRouteRepository.findAll(Example.of(transportRoute));
        }else {
            list = Collections.emptyList();
        }

        List<CmsTransportVo> ls = new ArrayList<>();
        for(TransportRoute st: list) {
            CmsTransportVo vo = CommonUtil.createCopyProperties(st, CmsTransportVo.class);
            ls.add(vo);
        }
        return ls;
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "/cmstransport-routes/{id}")
    public Integer deleteTransportRoute(@PathVariable Long id) {
        try {
            log.debug("REST request to delete a section : {}", id);
            this.transportRouteRepository.deleteById(id);
        }catch(Exception e) {
            return HttpStatus.FAILED_DEPENDENCY.value();
        }
        return HttpStatus.OK.value();
    }


}
