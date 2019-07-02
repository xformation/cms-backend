package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AddNewBookService;
import com.synectiks.cms.domain.AddNewBook;
import com.synectiks.cms.repository.AddNewBookRepository;
import com.synectiks.cms.repository.search.AddNewBookSearchRepository;
import com.synectiks.cms.service.dto.AddNewBookDTO;
import com.synectiks.cms.service.mapper.AddNewBookMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing AddNewBook.
 */
@Service
@Transactional
public class AddNewBookServiceImpl implements AddNewBookService {

    private final Logger log = LoggerFactory.getLogger(AddNewBookServiceImpl.class);

    private final AddNewBookRepository addNewBookRepository;

    private final AddNewBookMapper addNewBookMapper;

    private final AddNewBookSearchRepository addNewBookSearchRepository;

    public AddNewBookServiceImpl(AddNewBookRepository addNewBookRepository, AddNewBookMapper addNewBookMapper, AddNewBookSearchRepository addNewBookSearchRepository) {
        this.addNewBookRepository = addNewBookRepository;
        this.addNewBookMapper = addNewBookMapper;
        this.addNewBookSearchRepository = addNewBookSearchRepository;
    }

    /**
     * Save a addNewBook.
     *
     * @param addNewBookDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AddNewBookDTO save(AddNewBookDTO addNewBookDTO) {
        log.debug("Request to save AddNewBook : {}", addNewBookDTO);

        AddNewBook addNewBook = addNewBookMapper.toEntity(addNewBookDTO);
        addNewBook = addNewBookRepository.save(addNewBook);
        AddNewBookDTO result = addNewBookMapper.toDto(addNewBook);
        addNewBookSearchRepository.save(addNewBook);
        return result;
    }

    /**
     * Get all the addNewBooks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddNewBookDTO> findAll() {
        log.debug("Request to get all AddNewBooks");
        return addNewBookRepository.findAll().stream()
            .map(addNewBookMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one addNewBook by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AddNewBookDTO> findOne(Long id) {
        log.debug("Request to get AddNewBook : {}", id);
        return addNewBookRepository.findById(id)
            .map(addNewBookMapper::toDto);
    }

    /**
     * Delete the addNewBook by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AddNewBook : {}", id);
        addNewBookRepository.deleteById(id);
        addNewBookSearchRepository.deleteById(id);
    }

    /**
     * Search for the addNewBook corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AddNewBookDTO> search(String query) {
        log.debug("Request to search AddNewBooks for query {}", query);
        return StreamSupport
            .stream(addNewBookSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(addNewBookMapper::toDto)
            .collect(Collectors.toList());
    }
}
