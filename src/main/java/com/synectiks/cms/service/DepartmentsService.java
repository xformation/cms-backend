package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.DepartmentsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Departments.
 */
public interface DepartmentsService {

    /**
     * Save a departments.
     *
     * @param departmentsDTO the entity to save
     * @return the persisted entity
     */
    DepartmentsDTO save(DepartmentsDTO departmentsDTO);

    /**
     * Get all the departments.
     *
     * @return the list of entities
     */
    List<DepartmentsDTO> findAll();


    /**
     * Get the "id" departments.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DepartmentsDTO> findOne(Long id);

    /**
     * Delete the "id" departments.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the departments corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<DepartmentsDTO> search(String query);
}
