package com.synectiks.cms.web.rest;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.commons.entities.cms.CmsDepartmentVo;
import com.synectiks.commons.entities.cms.Config;
import com.synectiks.cms.service.util.CommonUtil;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class GlobalConfigRestController {

    private final Logger logger = LoggerFactory.getLogger(GlobalConfigRestController.class);

    @Autowired
	private CommonService commonService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmssettings")
    public ResponseEntity<Config> getGlobalSetting(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("REST request to get global settings");
        String userName = dataMap.get("userName") != null ? dataMap.get("userName").trim() : null;
        
        if (!CommonUtil.isNullOrEmpty(userName)) {
        	Config config = CmsConstants.USERS_CACHE.get(userName);
        	if(config == null) {
        		if("admin".equals(userName) || "cmsadmin".equals(userName)) {
        			config = commonService.createUserConfigForAdmin(userName);
        			CmsConstants.USERS_CACHE.put(userName, config);
            		return ResponseUtil.wrapOrNotFound(Optional.of(config));
        		}
        	}
        	return ResponseUtil.wrapOrNotFound(Optional.of(config));
        }
        
        return ResponseUtil.wrapOrNotFound(Optional.of(new Config()));
    }
   
    @RequestMapping(method = RequestMethod.POST, value = "/cmssettings")
    public ResponseEntity<Config> applyUserSetting(@RequestParam Map<String, String> dataMap) throws Exception {
        logger.debug("REST request to apply user specific global settings");
        String userName = dataMap.get("userName") != null ? dataMap.get("userName").trim() : null;
        String ayId = dataMap.get("academicYearId") != null ? dataMap.get("academicYearId").trim() : null;
        String branchId = dataMap.get("branchId") != null ? dataMap.get("branchId").trim() : null;
        String departmentId = dataMap.get("departmentId") != null ? dataMap.get("departmentId").trim() : null;
        
        Config config = CmsConstants.USERS_CACHE.get(userName);
        config.setSelectedAcademicYearId(Long.parseLong(ayId));
        config.setSelectedBranchId(Long.parseLong(branchId));
        List<CmsDepartmentVo> deptList = this.commonService.getDepartmentListByBranch(Long.parseLong(branchId));
        config.setDepartmentList(deptList);
        config.setSelectedDepartmentId(Long.parseLong(departmentId));
        CmsConstants.USERS_CACHE.put(userName, config);
        logger.debug("User specific global settings applied successfully");
        return ResponseUtil.wrapOrNotFound(Optional.of(config));
    }
    
}
