package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.MetaLectureDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing MetaLecture.
 */
public interface MetaLectureService {

    /**
     * Save a metaLecture.
     *
     * @param metaLectureDTO the entity to save
     * @return the persisted entity
     */
    MetaLectureDTO save(MetaLectureDTO metaLectureDTO);

    /**
     * Get all the metaLectures.
     *
     * @return the list of entities
     */
    List<MetaLectureDTO> findAll();


    /**
     * Get the "id" metaLecture.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<MetaLectureDTO> findOne(Long id);

    /**
     * Delete the "id" metaLecture.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the metaLecture corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<MetaLectureDTO> search(String query);
}
