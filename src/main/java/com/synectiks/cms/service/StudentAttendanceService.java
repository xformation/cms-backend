package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.StudentAttendanceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StudentAttendance.
 */
public interface StudentAttendanceService {

    /**
     * Save a studentAttendance.
     *
     * @param studentAttendanceDTO the entity to save
     * @return the persisted entity
     */
    StudentAttendanceDTO save(StudentAttendanceDTO studentAttendanceDTO);

    /**
     * Get all the studentAttendances.
     *
     * @return the list of entities
     */
    List<StudentAttendanceDTO> findAll();


    /**
     * Get the "id" studentAttendance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StudentAttendanceDTO> findOne(Long id);

    /**
     * Delete the "id" studentAttendance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the studentAttendance corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<StudentAttendanceDTO> search(String query);
}
