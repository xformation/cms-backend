package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.DueDateService;
import com.synectiks.cms.entities.DueDate;
import com.synectiks.cms.repositories.DueDateRepository;
//import com.synectiks.cms.commons.repositories.search.DueDateSearchRepository;
import com.synectiks.cms.service.dto.DueDateDTO;
import com.synectiks.cms.service.mapper.DueDateMapper;
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
 * Service Implementation for managing DueDate.
 */
@Service
@Transactional
public class DueDateServiceImpl implements DueDateService {

    private final Logger log = LoggerFactory.getLogger(DueDateServiceImpl.class);

    private final DueDateRepository dueDateRepository;

    private final DueDateMapper dueDateMapper;

    //private final DueDateSearchRepository dueDateSearchRepository;

    public DueDateServiceImpl(DueDateRepository dueDateRepository, DueDateMapper dueDateMapper/*, DueDateSearchRepository dueDateSearchRepository*/) {
        this.dueDateRepository = dueDateRepository;
        this.dueDateMapper = dueDateMapper;
        //this.dueDateSearchRepository = dueDateSearchRepository;
    }

    /**
     * Save a dueDate.
     *
     * @param dueDateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DueDateDTO save(DueDateDTO dueDateDTO) {
        log.debug("Request to save DueDate : {}", dueDateDTO);
        DueDate dueDate = dueDateMapper.toEntity(dueDateDTO);
        dueDate = dueDateRepository.save(dueDate);
        DueDateDTO result = dueDateMapper.toDto(dueDate);
        //dueDateSearchRepository.save(dueDate);
        return result;
    }

    /**
     * Get all the dueDates.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DueDateDTO> findAll() {
        log.debug("Request to get all DueDates");
        return dueDateRepository.findAll().stream()
            .map(dueDateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one dueDate by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DueDateDTO> findOne(Long id) {
        log.debug("Request to get DueDate : {}", id);
        return dueDateRepository.findById(id)
            .map(dueDateMapper::toDto);
    }

    /**
     * Delete the dueDate by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DueDate : {}", id);
        dueDateRepository.deleteById(id);
        //dueDateSearchRepository.deleteById(id);
    }

    /**
     * Search for the dueDate corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DueDateDTO> search(String query) {
        log.debug("Request to search DueDates for query {}", query);
        /*return StreamSupport
            .stream(dueDateSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(dueDateMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
