package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.DocumentsDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing Documents.
 */
public interface DocumentsService {

    /**
     * Save a documents.csv.
     *
     * @param documentsDTO the entity to save
     * @return the persisted entity
     */
    DocumentsDTO save(DocumentsDTO documentsDTO);

    /**
     * Get all the documents.csv.
     *
     * @return the list of entities
     */
    List<DocumentsDTO> findAll();


    /**
     * Get the "id" documents.csv.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DocumentsDTO> findOne(Long id);

    /**
     * Delete the "id" documents.csv.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the documents.csv corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<DocumentsDTO> search(String query);
}
