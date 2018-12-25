package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.CollegeBranchesDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CollegeBranches.
 */
public interface CollegeBranchesService {

    /**
     * Save a collegeBranches.
     *
     * @param collegeBranchesDTO the entity to save
     * @return the persisted entity
     */
    CollegeBranchesDTO save(CollegeBranchesDTO collegeBranchesDTO);

    /**
     * Get all the collegeBranches.
     *
     * @return the list of entities
     */
    List<CollegeBranchesDTO> findAll();


    /**
     * Get the "id" collegeBranches.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CollegeBranchesDTO> findOne(Long id);

    /**
     * Delete the "id" collegeBranches.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the collegeBranches corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CollegeBranchesDTO> search(String query);
}
