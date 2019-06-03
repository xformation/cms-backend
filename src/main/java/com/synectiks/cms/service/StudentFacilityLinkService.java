package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.StudentFacilityLinkDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StudentFacilityLink.
 */
public interface StudentFacilityLinkService {

    /**
     * Save a studentFacilityLink.
     *
     * @param studentFacilityLinkDTO the entity to save
     * @return the persisted entity
     */
    StudentFacilityLinkDTO save(StudentFacilityLinkDTO studentFacilityLinkDTO);

    /**
     * Get all the studentFacilityLinks.
     *
     * @return the list of entities
     */
    List<StudentFacilityLinkDTO> findAll();


    /**
     * Get the "id" studentFacilityLink.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StudentFacilityLinkDTO> findOne(Long id);

    /**
     * Delete the "id" studentFacilityLink.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the studentFacilityLink corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<StudentFacilityLinkDTO> search(String query);
}
