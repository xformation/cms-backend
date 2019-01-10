package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AttendanceMasterDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AttendanceMaster.
 */
public interface AttendanceMasterService {

    /**
     * Save a attendanceMaster.
     *
     * @param attendanceMasterDTO the entity to save
     * @return the persisted entity
     */
    AttendanceMasterDTO save(AttendanceMasterDTO attendanceMasterDTO);

    /**
     * Get all the attendanceMasters.
     *
     * @return the list of entities
     */
    List<AttendanceMasterDTO> findAll();


    /**
     * Get the "id" attendanceMaster.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AttendanceMasterDTO> findOne(Long id);

    /**
     * Delete the "id" attendanceMaster.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the attendanceMaster corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AttendanceMasterDTO> search(String query);
}
