package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AuthorizedSignatoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AuthorizedSignatory.
 */
public interface AuthorizedSignatoryService {

    /**
     * Save a authorizedSignatory.
     *
     * @param authorizedSignatoryDTO the entity to save
     * @return the persisted entity
     */
    AuthorizedSignatoryDTO save(AuthorizedSignatoryDTO authorizedSignatoryDTO);

    /**
     * Get all the authorizedSignatories.
     *
     * @return the list of entities
     */
    List<AuthorizedSignatoryDTO> findAll();


    /**
     * Get the "id" authorizedSignatory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AuthorizedSignatoryDTO> findOne(Long id);

    /**
     * Delete the "id" authorizedSignatory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the authorizedSignatory corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AuthorizedSignatoryDTO> search(String query);
}
