package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.VehicleDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Vehicle.
 */
public interface VehicleService {

    /**
     * Save a vehicle.
     *
     * @param vehicleDTO the entity to save
     * @return the persisted entity
     */
    VehicleDTO save(VehicleDTO vehicleDTO);

    /**
     * Get all the vehicles.
     *
     * @return the list of entities
     */
    List<VehicleDTO> findAll();
    /**
     * Get all the VehicleDTO where Insurance is null.
     *
     * @return the list of entities
     */
    List<VehicleDTO> findAllWhereInsuranceIsNull();


    /**
     * Get the "id" vehicle.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<VehicleDTO> findOne(Long id);

    /**
     * Delete the "id" vehicle.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the vehicle corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<VehicleDTO> search(String query);
}
