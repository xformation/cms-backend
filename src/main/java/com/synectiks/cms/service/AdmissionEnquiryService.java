package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AdmissionEnquiry.
 */
public interface AdmissionEnquiryService {

    /**
     * Save a admissionEnquiry.
     *
     * @param admissionEnquiryDTO the entity to save
     * @return the persisted entity
     */
    AdmissionEnquiryDTO save(AdmissionEnquiryDTO admissionEnquiryDTO);

    /**
     * Get all the admissionEnquiries.
     *
     * @return the list of entities
     */
    List<AdmissionEnquiryDTO> findAll();


    /**
     * Get the "id" admissionEnquiry.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AdmissionEnquiryDTO> findOne(Long id);

    /**
     * Delete the "id" admissionEnquiry.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the admissionEnquiry corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AdmissionEnquiryDTO> search(String query);
}
