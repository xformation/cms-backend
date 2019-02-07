package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.SignatoryLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing SignatoryLink.
 */
public interface SignatoryLinkService {

    /**
     * Save a signatoryLink.
     *
     * @param signatoryLinkDTO the entity to save
     * @return the persisted entity
     */
    SignatoryLinkDTO save(SignatoryLinkDTO signatoryLinkDTO);

    /**
     * Get all the signatoryLinks.
     *
     * @return the list of entities
     */
    List<SignatoryLinkDTO> findAll();


    /**
     * Get the "id" signatoryLink.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SignatoryLinkDTO> findOne(Long id);

    /**
     * Delete the "id" signatoryLink.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the signatoryLink corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<SignatoryLinkDTO> search(String query);
}
