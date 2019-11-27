package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.ExceptionRecordDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.synectiks.cms.domain.ExceptionRecord}.
 */
public interface ExceptionRecordService {

    /**
     * Save a exceptionRecord.
     *
     * @param exceptionRecordDTO the entity to save.
     * @return the persisted entity.
     */
    ExceptionRecordDTO save(ExceptionRecordDTO exceptionRecordDTO);

    /**
     * Get all the exceptionRecords.
     *
     * @return the list of entities.
     */
    List<ExceptionRecordDTO> findAll();


    /**
     * Get the "id" exceptionRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ExceptionRecordDTO> findOne(Long id);

    /**
     * Delete the "id" exceptionRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the exceptionRecord corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<ExceptionRecordDTO> search(String query);
}
