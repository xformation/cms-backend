package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Employee.
 */
public interface EmployeeService {

    /**
     * Save a employee.
     *
     * @param employeeDTO the entity to save
     * @return the persisted entity
     */
    EmployeeDTO save(EmployeeDTO employeeDTO);

    /**
     * Get all the employees.
     *
     * @return the list of entities
     */
    List<EmployeeDTO> findAll();
    /**
     * Get all the EmployeeDTO where Vehicle is null.
     *
     * @return the list of entities
     */
    List<EmployeeDTO> findAllWhereVehicleIsNull();


    /**
     * Get the "id" employee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<EmployeeDTO> findOne(Long id);

    /**
     * Delete the "id" employee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the employee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<EmployeeDTO> search(String query);
}
