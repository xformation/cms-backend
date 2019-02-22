package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.TransportRouteDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing TransportRoute.
 */
public interface TransportRouteService {

    /**
     * Save a transportRoute.
     *
     * @param transportRouteDTO the entity to save
     * @return the persisted entity
     */
    TransportRouteDTO save(TransportRouteDTO transportRouteDTO);

    /**
     * Get all the transportRoutes.
     *
     * @return the list of entities
     */
    List<TransportRouteDTO> findAll();


    /**
     * Get the "id" transportRoute.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<TransportRouteDTO> findOne(Long id);

    /**
     * Delete the "id" transportRoute.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the transportRoute corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<TransportRouteDTO> search(String query);
}
