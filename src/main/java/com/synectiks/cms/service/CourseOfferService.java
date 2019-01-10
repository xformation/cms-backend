package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.CourseOfferDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CourseOffer.
 */
public interface CourseOfferService {

    /**
     * Save a courseOffer.
     *
     * @param courseOfferDTO the entity to save
     * @return the persisted entity
     */
    CourseOfferDTO save(CourseOfferDTO courseOfferDTO);

    /**
     * Get all the courseOffers.
     *
     * @return the list of entities
     */
    List<CourseOfferDTO> findAll();


    /**
     * Get the "id" courseOffer.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CourseOfferDTO> findOne(Long id);

    /**
     * Delete the "id" courseOffer.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the courseOffer corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CourseOfferDTO> search(String query);
}
