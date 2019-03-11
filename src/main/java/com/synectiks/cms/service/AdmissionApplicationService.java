package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AdmissionApplicationDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AdmissionApplication.
 */
public interface AdmissionApplicationService {

    /**
     * Save a admissionApplication.
     *
     * @param admissionApplicationDTO the entity to save
     * @return the persisted entity
     */
    AdmissionApplicationDTO save(AdmissionApplicationDTO admissionApplicationDTO);

    /**
     * Get all the admissionApplications.
     *
     * @return the list of entities
     */
    List<AdmissionApplicationDTO> findAll();


    /**
     * Get the "id" admissionApplication.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdmissionApplicationDTO> findOne(Long id);

    /**
     * Delete the "id" admissionApplication.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the admissionApplication corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AdmissionApplicationDTO> search(String query);
}
