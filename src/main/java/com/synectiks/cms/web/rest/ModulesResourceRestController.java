package com.synectiks.cms.web.rest;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.entities.Modules;
import com.synectiks.cms.entities.enumeration.Status;
import com.synectiks.cms.repositories.ModulesRepository;
import com.synectiks.cms.service.dto.ModulesDTO;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.util.HeaderUtil;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.cms.domain.Modules}.
 */
@RestController
@RequestMapping("/api")
public class ModulesResourceRestController {

    private final Logger logger = LoggerFactory.getLogger(ModulesResourceRestController.class);

    private static final String ENTITY_NAME = "modules";
    private static boolean isUiModulesUpdated = false;
    
    @Autowired
    private ModulesRepository modulesRepository;

    /**
     * {@code POST  /cmsmodules} : Create new modules in cms application.
     *
     * @param list the modulesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modulesDTO, or with status {@code 400 (Bad Request)} if the modules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cmsmodules")
    public ResponseEntity<Void> createModules(@RequestBody List<ModulesDTO> list) throws URISyntaxException {
        logger.debug("REST request to save UI Modules : {}", list);
        if(!isUiModulesUpdated) {
        	try {
        		for(ModulesDTO dto : list) {
                	Modules module = CommonUtil.createCopyProperties(dto, Modules.class);
                	if(!modulesRepository.exists(Example.of(module))) {
                		module = modulesRepository.save(module);
                	}
                }
        		isUiModulesUpdated = true;
        	}catch(Exception e) {
        		logger.error("UI modules could not be created in cms. "+e.getMessage(), e);
        		throw e;
        	}
        	
        }
        
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME,"")).build();
    }

    /**
     * {@code PUT  /cmsmodules} : Updates an existing modules.
     *
     * @param modulesDTO the modulesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modulesDTO,
     * or with status {@code 400 (Bad Request)} if the modulesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modulesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cmsmodules")
    public ResponseEntity<String> updateModules(@RequestBody ModulesDTO modulesDTO) throws URISyntaxException {
        logger.debug("REST request to update UI Modules : {}", modulesDTO);
        Modules modules = new Modules();
        modules.setModuleName(modulesDTO.getModuleName());
        List<Modules> list = modulesRepository.findAll(Example.of(modules));
        for (Modules m: list) {
        	logger.debug("Updating module : "+m);
        	m.setStatus(modulesDTO.getStatus());
        	modulesRepository.save(m);
        }
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body("Modules updated successfully");
    }

    /**
     * {@code GET  /cmsmodules} : get all the modules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modules in body.
     */
    @GetMapping("/cmsmodules")
    public List<Modules> getAllModules() {
    	Modules module = new Modules();
    	module.setStatus(Status.ACTIVE);
        logger.debug("REST request to get all UI Modules");
        return modulesRepository.findAll(Example.of(module));
    }

    /**
     * getAllParentModules() gives a unique list of all the active modules/plugins
     * @return
     */
    @GetMapping("/cmsparentmodules")
    public List<String> getAllParentModules() {
    	Modules module = new Modules();
    	module.setStatus(Status.ACTIVE);
        logger.debug("REST request to get all UI Modules");
        List<Modules> list = modulesRepository.findAll(Example.of(module));
        Set<String> parentModules = new HashSet<>();
        for(Modules md : list) {
        	parentModules.add(md.getModuleName());
        }
        return parentModules.stream().collect(Collectors.toList());
    }
    
    /**
     * {@code GET  /cmsmodules/:id} : get the "id" modules.
     *
     * @param id the id of the modulesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modulesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cmsmodules/{id}")
    public ResponseEntity<Modules> getModules(@PathVariable Long id) {
        logger.debug("REST request to get a UI Module : {}", id);
        Optional<Modules> modules = modulesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(modules);
    }

    /**
     * {@code DELETE  /cmsmodules/:id} : delete the "id" modules.
     *
     * @param id the id of the modulesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cmsmodules/{id}")
    public ResponseEntity<Void> deleteModules(@PathVariable Long id) {
        logger.debug("REST request to delete a UI Module : {}", id);
        modulesRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    

}
