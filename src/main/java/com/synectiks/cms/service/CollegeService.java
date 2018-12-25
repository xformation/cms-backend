package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.CollegeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing College.
 */
public interface CollegeService {

    /**
     * Save a college.
     *
     * @param collegeDTO the entity to save
     * @return the persisted entity
     */
    CollegeDTO save(CollegeDTO collegeDTO);

    /**
     * Get all the colleges.
     *
     * @return the list of entities
     */
    List<CollegeDTO> findAll();


    /**
     * Get the "id" college.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CollegeDTO> findOne(Long id);

    /**
     * Delete the "id" college.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the college corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CollegeDTO> search(String query);
}
