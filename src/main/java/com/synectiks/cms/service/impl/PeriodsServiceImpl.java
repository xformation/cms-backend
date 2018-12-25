package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.PeriodsService;
import com.synectiks.cms.domain.Periods;
import com.synectiks.cms.repository.PeriodsRepository;
import com.synectiks.cms.repository.search.PeriodsSearchRepository;
import com.synectiks.cms.service.dto.PeriodsDTO;
import com.synectiks.cms.service.mapper.PeriodsMapper;
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
 * Service Implementation for managing Periods.
 */
@Service
@Transactional
public class PeriodsServiceImpl implements PeriodsService {

    private final Logger log = LoggerFactory.getLogger(PeriodsServiceImpl.class);

    private final PeriodsRepository periodsRepository;

    private final PeriodsMapper periodsMapper;

    private final PeriodsSearchRepository periodsSearchRepository;

    public PeriodsServiceImpl(PeriodsRepository periodsRepository, PeriodsMapper periodsMapper, PeriodsSearchRepository periodsSearchRepository) {
        this.periodsRepository = periodsRepository;
        this.periodsMapper = periodsMapper;
        this.periodsSearchRepository = periodsSearchRepository;
    }

    /**
     * Save a periods.
     *
     * @param periodsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PeriodsDTO save(PeriodsDTO periodsDTO) {
        log.debug("Request to save Periods : {}", periodsDTO);
        Periods periods = periodsMapper.toEntity(periodsDTO);
        periods = periodsRepository.save(periods);
        PeriodsDTO result = periodsMapper.toDto(periods);
        periodsSearchRepository.save(periods);
        return result;
    }

    /**
     * Get all the periods.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PeriodsDTO> findAll() {
        log.debug("Request to get all Periods");
        return periodsRepository.findAll().stream()
            .map(periodsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }



    /**
     *  get all the periods where Teacher is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PeriodsDTO> findAllWhereTeacherIsNull() {
        log.debug("Request to get all periods where Teacher is null");
        return StreamSupport
            .stream(periodsRepository.findAll().spliterator(), false)
            .filter(periods -> periods.getTeacher() == null)
            .map(periodsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one periods by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PeriodsDTO> findOne(Long id) {
        log.debug("Request to get Periods : {}", id);
        return periodsRepository.findById(id)
            .map(periodsMapper::toDto);
    }

    /**
     * Delete the periods by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Periods : {}", id);
        periodsRepository.deleteById(id);
        periodsSearchRepository.deleteById(id);
    }

    /**
     * Search for the periods corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PeriodsDTO> search(String query) {
        log.debug("Request to search Periods for query {}", query);
        return StreamSupport
            .stream(periodsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(periodsMapper::toDto)
            .collect(Collectors.toList());
    }
}
