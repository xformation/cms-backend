package com.synectiks.cms.web.rest;


import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.config.GlobalConfig;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.Config;
import com.synectiks.cms.service.util.CommonUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class GlobalConfigRestController {

    private final Logger logger = LoggerFactory.getLogger(GlobalConfigRestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/cmssettings")
    public ResponseEntity<Config> getGlobalSetting(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("REST request to get global settings");
        String userName = dataMap.get("userName") != null ? dataMap.get("userName").trim() : null;
        
        if(CommonUtil.isNullOrEmpty(userName)) {
        	Config config = CmsConstants.USERS_CACHE.get(userName);
        	return ResponseUtil.wrapOrNotFound(Optional.of(config));
        }
        
        return ResponseUtil.wrapOrNotFound(Optional.of(GlobalConfig.CONFIG));
    }
   
}
