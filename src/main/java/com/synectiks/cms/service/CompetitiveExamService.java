package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.CompetitiveExamDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing CompetitiveExam.
 */
public interface CompetitiveExamService {

    /**
     * Save a competitiveExam.
     *
     * @param competitiveExamDTO the entity to save
     * @return the persisted entity
     */
    CompetitiveExamDTO save(CompetitiveExamDTO competitiveExamDTO);

    /**
     * Get all the competitiveExams.
     *
     * @return the list of entities
     */
    List<CompetitiveExamDTO> findAll();


    /**
     * Get the "id" competitiveExam.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<CompetitiveExamDTO> findOne(Long id);

    /**
     * Delete the "id" competitiveExam.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the competitiveExam corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CompetitiveExamDTO> search(String query);
}
