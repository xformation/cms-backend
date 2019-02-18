package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AdminAttendanceDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AdminAttendance.
 */
public interface AdminAttendanceService {

    /**
     * Save a adminAttendance.
     *
     * @param adminAttendanceDTO the entity to save
     * @return the persisted entity
     */
    AdminAttendanceDTO save(AdminAttendanceDTO adminAttendanceDTO);

    /**
     * Get all the adminAttendances.
     *
     * @return the list of entities
     */
    List<AdminAttendanceDTO> findAll();


    /**
     * Get the "id" adminAttendance.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdminAttendanceDTO> findOne(Long id);

    /**
     * Delete the "id" adminAttendance.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the adminAttendance corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AdminAttendanceDTO> search(String query);
}
