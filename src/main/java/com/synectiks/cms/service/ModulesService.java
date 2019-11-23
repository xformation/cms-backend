package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.ModulesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.commons.entities.cms.Modules}.
 */
public interface ModulesService {

    /**
     * Save a modules.
     *
     * @param modulesDTO the entity to save.
     * @return the persisted entity.
     */
    ModulesDTO save(ModulesDTO modulesDTO);

    /**
     * Get all the modules.
     *
     * @return the list of entities.
     */
    List<ModulesDTO> findAll();


    /**
     * Get the "id" modules.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ModulesDTO> findOne(Long id);

    /**
     * Delete the "id" modules.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the modules corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<ModulesDTO> search(String query);
}
