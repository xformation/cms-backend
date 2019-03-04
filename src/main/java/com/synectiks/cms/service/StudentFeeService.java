package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.StudentFeeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing StudentFee.
 */
public interface StudentFeeService {

    /**
     * Save a studentFee.
     *
     * @param studentFeeDTO the entity to save
     * @return the persisted entity
     */
    StudentFeeDTO save(StudentFeeDTO studentFeeDTO);

    /**
     * Get all the studentFees.
     *
     * @return the list of entities
     */
    List<StudentFeeDTO> findAll();


    /**
     * Get the "id" studentFee.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<StudentFeeDTO> findOne(Long id);

    /**
     * Delete the "id" studentFee.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the studentFee corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<StudentFeeDTO> search(String query);
}
