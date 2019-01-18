package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.StudentSubjectDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StudentSubject.
 */
public interface StudentSubjectService {

    /**
     * Save a studentSubject.
     *
     * @param studentSubjectDTO the entity to save
     * @return the persisted entity
     */
    StudentSubjectDTO save(StudentSubjectDTO studentSubjectDTO);

    /**
     * Get all the studentSubjects.
     *
     * @return the list of entities
     */
    List<StudentSubjectDTO> findAll();


    /**
     * Get the "id" studentSubject.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StudentSubjectDTO> findOne(Long id);

    /**
     * Delete the "id" studentSubject.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the studentSubject corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<StudentSubjectDTO> search(String query);
}
