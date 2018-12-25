package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.LegalEntityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing LegalEntity.
 */
public interface LegalEntityService {

    /**
     * Save a legalEntity.
     *
     * @param legalEntityDTO the entity to save
     * @return the persisted entity
     */
    LegalEntityDTO save(LegalEntityDTO legalEntityDTO);

    /**
     * Get all the legalEntities.
     *
     * @return the list of entities
     */
    List<LegalEntityDTO> findAll();


    /**
     * Get the "id" legalEntity.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<LegalEntityDTO> findOne(Long id);

    /**
     * Delete the "id" legalEntity.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the legalEntity corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<LegalEntityDTO> search(String query);
}
