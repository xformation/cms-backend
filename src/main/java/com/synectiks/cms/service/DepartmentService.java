package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.DepartmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Department.
 */
public interface DepartmentService {

    /**
     * Save a department.
     *
     * @param departmentDTO the entity to save
     * @return the persisted entity
     */
    DepartmentDTO save(DepartmentDTO departmentDTO);

    /**
     * Get all the departments.
     *
     * @return the list of entities
     */
    List<DepartmentDTO> findAll();


    /**
     * Get the "id" department.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DepartmentDTO> findOne(Long id);

    /**
     * Delete the "id" department.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the department corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<DepartmentDTO> search(String query);
}
