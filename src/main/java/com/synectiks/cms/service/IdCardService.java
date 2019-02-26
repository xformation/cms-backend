package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.IdCardDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing IdCard.
 */
public interface IdCardService {

    /**
     * Save a idCard.
     *
     * @param idCardDTO the entity to save
     * @return the persisted entity
     */
    IdCardDTO save(IdCardDTO idCardDTO);

    /**
     * Get all the idCards.
     *
     * @return the list of entities
     */
    List<IdCardDTO> findAll();


    /**
     * Get the "id" idCard.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<IdCardDTO> findOne(Long id);

    /**
     * Delete the "id" idCard.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the idCard corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<IdCardDTO> search(String query);
}
