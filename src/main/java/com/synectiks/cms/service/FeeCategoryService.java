package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.FeeCategoryDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FeeCategory.
 */
public interface FeeCategoryService {

    /**
     * Save a feeCategory.
     *
     * @param feeCategoryDTO the entity to save
     * @return the persisted entity
     */
    FeeCategoryDTO save(FeeCategoryDTO feeCategoryDTO);

    /**
     * Get all the feeCategories.
     *
     * @return the list of entities
     */
    List<FeeCategoryDTO> findAll();


    /**
     * Get the "id" feeCategory.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FeeCategoryDTO> findOne(Long id);

    /**
     * Delete the "id" feeCategory.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the feeCategory corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<FeeCategoryDTO> search(String query);
}
