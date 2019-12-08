package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.LibraryService;
import com.synectiks.cms.entities.Library;
import com.synectiks.cms.repositories.LibraryRepository;
//import com.synectiks.cms.commons.repositories.search.LibrarySearchRepository;
import com.synectiks.cms.service.dto.LibraryDTO;
import com.synectiks.cms.service.mapper.LibraryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Library.
 */
@Service
@Transactional
public class LibraryServiceImpl implements LibraryService {

    private final Logger log = LoggerFactory.getLogger(LibraryServiceImpl.class);

    private final LibraryRepository libraryRepository;

    private final LibraryMapper libraryMapper;

    //private final LibrarySearchRepository librarySearchRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository, LibraryMapper libraryMapper/*, LibrarySearchRepository librarySearchRepository*/) {
        this.libraryRepository = libraryRepository;
        this.libraryMapper = libraryMapper;
        //this.librarySearchRepository = librarySearchRepository;
    }

    /**
     * Save a library.
     *
     * @param libraryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LibraryDTO save(LibraryDTO libraryDTO) {
        log.debug("Request to save Library : {}", libraryDTO);

        Library library = libraryMapper.toEntity(libraryDTO);
        library = libraryRepository.save(library);
        LibraryDTO result = libraryMapper.toDto(library);
        //librarySearchRepository.save(library);
        return result;
    }

    /**
     * Get all the libraries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LibraryDTO> findAll() {
        log.debug("Request to get all Libraries");
        return libraryRepository.findAll().stream()
            .map(libraryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one library by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LibraryDTO> findOne(Long id) {
        log.debug("Request to get Library : {}", id);
        return libraryRepository.findById(id)
            .map(libraryMapper::toDto);
    }

    /**
     * Delete the library by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Library : {}", id);
        libraryRepository.deleteById(id);
        //librarySearchRepository.deleteById(id);
    }

    /**
     * Search for the library corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LibraryDTO> search(String query) {
        log.debug("Request to search Libraries for query {}", query);
        /*return StreamSupport
            .stream(librarySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(libraryMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
