package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.StudentYearDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StudentYear.
 */
public interface StudentYearService {

    /**
     * Save a studentYear.
     *
     * @param studentYearDTO the entity to save
     * @return the persisted entity
     */
    StudentYearDTO save(StudentYearDTO studentYearDTO);

    /**
     * Get all the studentYears.
     *
     * @return the list of entities
     */
    List<StudentYearDTO> findAll();


    /**
     * Get the "id" studentYear.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StudentYearDTO> findOne(Long id);

    /**
     * Delete the "id" studentYear.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the studentYear corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<StudentYearDTO> search(String query);
}
