package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.BranchDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Branch.
 */
public interface BranchService {

    /**
     * Save a branch.
     *
     * @param branchDTO the entity to save
     * @return the persisted entity
     */
    BranchDTO save(BranchDTO branchDTO);

    /**
     * Get all the branches.
     *
     * @return the list of entities
     */
    List<BranchDTO> findAll();


    /**
     * Get the "id" branch.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<BranchDTO> findOne(Long id);

    /**
     * Delete the "id" branch.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the branch corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<BranchDTO> search(String query);
}
