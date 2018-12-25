package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.TeacherDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Teacher.
 */
public interface TeacherService {

    /**
     * Save a teacher.
     *
     * @param teacherDTO the entity to save
     * @return the persisted entity
     */
    TeacherDTO save(TeacherDTO teacherDTO);

    /**
     * Get all the teachers.
     *
     * @return the list of entities
     */
    List<TeacherDTO> findAll();


    /**
     * Get the "id" teacher.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TeacherDTO> findOne(Long id);

    /**
     * Delete the "id" teacher.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the teacher corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<TeacherDTO> search(String query);
}
