package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.StateService;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.search.StateSearchRepository;
import com.synectiks.cms.service.dto.StateDTO;
import com.synectiks.cms.service.mapper.StateMapper;
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
 * Service Implementation for managing State.
 */
@Service
@Transactional
public class StateServiceImpl implements StateService {

    private final Logger log = LoggerFactory.getLogger(StateServiceImpl.class);

    private final StateRepository stateRepository;

    private final StateMapper stateMapper;

//    private final StateSearchRepository stateSearchRepository;

    public StateServiceImpl(StateRepository stateRepository, StateMapper stateMapper, StateSearchRepository stateSearchRepository) {
        this.stateRepository = stateRepository;
        this.stateMapper = stateMapper;
//        this.stateSearchRepository = stateSearchRepository;
    }

    /**
     * Save a state.
     *
     * @param stateDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StateDTO save(StateDTO stateDTO) {
        log.debug("Request to save State : {}", stateDTO);
        State state = stateMapper.toEntity(stateDTO);
        state = stateRepository.save(state);
        StateDTO result = stateMapper.toDto(state);
//        stateSearchRepository.save(state);
        return result;
    }

    /**
     * Get all the states.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StateDTO> findAll() {
        log.debug("Request to get all States");
        return stateRepository.findAll().stream()
            .map(stateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one state by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StateDTO> findOne(Long id) {
        log.debug("Request to get State : {}", id);
        return stateRepository.findById(id)
            .map(stateMapper::toDto);
    }

    /**
     * Delete the state by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete State : {}", id);
        stateRepository.deleteById(id);
//        stateSearchRepository.deleteById(id);
    }

    /**
     * Search for the state corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StateDTO> search(String query) {
        log.debug("Request to search States for query {}", query);
//        return StreamSupport
//            .stream(stateSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .map(stateMapper::toDto)
//            .collect(Collectors.toList());
        return null;
    }
}
