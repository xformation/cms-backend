package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.TermDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Term.
 */
public interface TermService {

    /**
     * Save a term.
     *
     * @param termDTO the entity to save
     * @return the persisted entity
     */
    TermDTO save(TermDTO termDTO);

    /**
     * Get all the terms.
     *
     * @return the list of entities
     */
    List<TermDTO> findAll();


    /**
     * Get the "id" term.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TermDTO> findOne(Long id);

    /**
     * Delete the "id" term.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the term corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<TermDTO> search(String query);
}
