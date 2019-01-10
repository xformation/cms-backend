package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.TeachDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Teach.
 */
public interface TeachService {

    /**
     * Save a teach.
     *
     * @param teachDTO the entity to save
     * @return the persisted entity
     */
    TeachDTO save(TeachDTO teachDTO);

    /**
     * Get all the teaches.
     *
     * @return the list of entities
     */
    List<TeachDTO> findAll();


    /**
     * Get the "id" teach.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeachDTO> findOne(Long id);

    /**
     * Delete the "id" teach.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the teach corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<TeachDTO> search(String query);
}
