package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AcademicDepartmentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AcademicDepartment.
 */
public interface AcademicDepartmentService {

    /**
     * Save a academicDepartment.
     *
     * @param academicDepartmentDTO the entity to save
     * @return the persisted entity
     */
    AcademicDepartmentDTO save(AcademicDepartmentDTO academicDepartmentDTO);

    /**
     * Get all the academicDepartments.
     *
     * @return the list of entities
     */
    List<AcademicDepartmentDTO> findAll();


    /**
     * Get the "id" academicDepartment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicDepartmentDTO> findOne(Long id);

    /**
     * Delete the "id" academicDepartment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the academicDepartment corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AcademicDepartmentDTO> search(String query);
}
