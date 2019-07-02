package com.synectiks.cms.service;

import com.synectiks.cms.service.dto.AddNewBookDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing AddNewBook.
 */
public interface AddNewBookService {

    /**
     * Save a addNewBook.
     *
     * @param addNewBookDTO the entity to save
     * @return the persisted entity
     */
    AddNewBookDTO save(AddNewBookDTO addNewBookDTO);

    /**
     * Get all the addNewBooks.
     *
     * @return the list of entities
     */
    List<AddNewBookDTO> findAll();


    /**
     * Get the "id" addNewBook.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<AddNewBookDTO> findOne(Long id);

    /**
     * Delete the "id" addNewBook.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the addNewBook corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<AddNewBookDTO> search(String query);
}
