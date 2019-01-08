package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AcademicYearDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AcademicYear.
 */
public interface AcademicYearService {

    /**
     * Save a academicYear.
     *
     * @param academicYearDTO the entity to save
     * @return the persisted entity
     */
    AcademicYearDTO save(AcademicYearDTO academicYearDTO);

    /**
     * Get all the academicYears.
     *
     * @return the list of entities
     */
    List<AcademicYearDTO> findAll();


    /**
     * Get the "id" academicYear.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicYearDTO> findOne(Long id);

    /**
     * Delete the "id" academicYear.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the academicYear corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AcademicYearDTO> search(String query);
}
