package com.synectiks.cms.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.synectiks.cms.service.ModulesService;
import com.synectiks.cms.service.dto.ModulesDTO;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

//import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.synectiks.commons.entities.cms.Modules}.
 */
@RestController
@RequestMapping("/api")
public class ModulesResource {

    private final Logger log = LoggerFactory.getLogger(ModulesResource.class);

    private static final String ENTITY_NAME = "modules";

    private final ModulesService modulesService;

    public ModulesResource(ModulesService modulesService) {
        this.modulesService = modulesService;
    }

    /**
     * {@code POST  /modules} : Create a new modules.
     *
     * @param modulesDTO the modulesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modulesDTO, or with status {@code 400 (Bad Request)} if the modules has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modules")
    public ResponseEntity<ModulesDTO> createModules(@RequestBody ModulesDTO modulesDTO) throws URISyntaxException {
        log.debug("REST request to save Modules : {}", modulesDTO);
        if (modulesDTO.getId() != null) {
            throw new BadRequestAlertException("A new modules cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModulesDTO result = modulesService.save(modulesDTO);
        return ResponseEntity.created(new URI("/api/modules/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modules} : Updates an existing modules.
     *
     * @param modulesDTO the modulesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modulesDTO,
     * or with status {@code 400 (Bad Request)} if the modulesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modulesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modules")
    public ResponseEntity<ModulesDTO> updateModules(@RequestBody ModulesDTO modulesDTO) throws URISyntaxException {
        log.debug("REST request to update Modules : {}", modulesDTO);
        if (modulesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModulesDTO result = modulesService.save(modulesDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, modulesDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modules} : get all the modules.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modules in body.
     */
    @GetMapping("/modules")
    public List<ModulesDTO> getAllModules() {
        log.debug("REST request to get all Modules");
        return modulesService.findAll();
    }

    /**
     * {@code GET  /modules/:id} : get the "id" modules.
     *
     * @param id the id of the modulesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modulesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modules/{id}")
    public ResponseEntity<ModulesDTO> getModules(@PathVariable Long id) {
        log.debug("REST request to get Modules : {}", id);
        Optional<ModulesDTO> modulesDTO = modulesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modulesDTO);
    }

    /**
     * {@code DELETE  /modules/:id} : delete the "id" modules.
     *
     * @param id the id of the modulesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modules/{id}")
    public ResponseEntity<Void> deleteModules(@PathVariable Long id) {
        log.debug("REST request to delete Modules : {}", id);
        modulesService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/modules?query=:query} : search for the modules corresponding
     * to the query.
     *
     * @param query the query of the modules search.
     * @return the result of the search.
     */
    @GetMapping("/_search/modules")
    public List<ModulesDTO> searchModules(@RequestParam String query) {
        log.debug("REST request to search Modules for query {}", query);
        return modulesService.search(query);
    }

}
