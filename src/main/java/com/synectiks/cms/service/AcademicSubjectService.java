package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AcademicSubjectDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AcademicSubject.
 */
public interface AcademicSubjectService {

    /**
     * Save a academicSubject.
     *
     * @param academicSubjectDTO the entity to save
     * @return the persisted entity
     */
    AcademicSubjectDTO save(AcademicSubjectDTO academicSubjectDTO);

    /**
     * Get all the academicSubjects.
     *
     * @return the list of entities
     */
    List<AcademicSubjectDTO> findAll();


    /**
     * Get the "id" academicSubject.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicSubjectDTO> findOne(Long id);

    /**
     * Delete the "id" academicSubject.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the academicSubject corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AcademicSubjectDTO> search(String query);
}
