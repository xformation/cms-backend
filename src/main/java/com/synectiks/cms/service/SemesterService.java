package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.SemesterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Semester.
 */
public interface SemesterService {

    /**
     * Save a semester.
     *
     * @param semesterDTO the entity to save
     * @return the persisted entity
     */
    SemesterDTO save(SemesterDTO semesterDTO);

    /**
     * Get all the semesters.
     *
     * @return the list of entities
     */
    List<SemesterDTO> findAll();


    /**
     * Get the "id" semester.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<SemesterDTO> findOne(Long id);

    /**
     * Delete the "id" semester.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the semester corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<SemesterDTO> search(String query);
}
