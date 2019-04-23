package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AcademicExamSettingDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AcademicExamSetting.
 */
public interface AcademicExamSettingService {

    /**
     * Save a academicExamSetting.
     *
     * @param academicExamSettingDTO the entity to save
     * @return the persisted entity
     */
    AcademicExamSettingDTO save(AcademicExamSettingDTO academicExamSettingDTO);

    /**
     * Get all the academicExamSettings.
     *
     * @return the list of entities
     */
    List<AcademicExamSettingDTO> findAll();


    /**
     * Get the "id" academicExamSetting.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AcademicExamSettingDTO> findOne(Long id);

    /**
     * Delete the "id" academicExamSetting.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the academicExamSetting corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AcademicExamSettingDTO> search(String query);
}
