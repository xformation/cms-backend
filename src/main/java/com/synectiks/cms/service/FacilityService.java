package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.FacilityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Facility.
 */
public interface FacilityService {

    /**
     * Save a facility.
     *
     * @param facilityDTO the entity to save
     * @return the persisted entity
     */
    FacilityDTO save(FacilityDTO facilityDTO);

    /**
     * Get all the facilities.
     *
     * @return the list of entities
     */
    List<FacilityDTO> findAll();


    /**
     * Get the "id" facility.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FacilityDTO> findOne(Long id);

    /**
     * Delete the "id" facility.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the facility corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<FacilityDTO> search(String query);
}
