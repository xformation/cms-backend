package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.CityDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing City.
 */
public interface CityService {

    /**
     * Save a city.
     *
     * @param cityDTO the entity to save
     * @return the persisted entity
     */
    CityDTO save(CityDTO cityDTO);

    /**
     * Get all the cities.
     *
     * @return the list of entities
     */
    List<CityDTO> findAll();


    /**
     * Get the "id" city.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CityDTO> findOne(Long id);

    /**
     * Delete the "id" city.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the city corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CityDTO> search(String query);
}
