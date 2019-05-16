package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.BatchService;
import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.search.BatchSearchRepository;
import com.synectiks.cms.service.dto.BatchDTO;
import com.synectiks.cms.service.mapper.BatchMapper;
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
 * Service Implementation for managing Batch.
 */
@Service
@Transactional
public class BatchServiceImpl implements BatchService {

    private final Logger log = LoggerFactory.getLogger(BatchServiceImpl.class);

    private final BatchRepository batchRepository;

    private final BatchMapper batchMapper;

    private final BatchSearchRepository batchSearchRepository;

    public BatchServiceImpl(BatchRepository batchRepository, BatchMapper batchMapper, BatchSearchRepository batchSearchRepository) {
        this.batchRepository = batchRepository;
        this.batchMapper = batchMapper;
        this.batchSearchRepository = batchSearchRepository;
    }

    /**
     * Save a batch.
     *
     * @param batchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BatchDTO save(BatchDTO batchDTO) {
        log.debug("Request to save Batch : {}", batchDTO);

        Batch batch = batchMapper.toEntity(batchDTO);
        batch = batchRepository.save(batch);
        BatchDTO result = batchMapper.toDto(batch);
        batchSearchRepository.save(batch);
        return result;
    }

    /**
     * Get all the batches.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BatchDTO> findAll() {
        log.debug("Request to get all Batches");
        return batchRepository.findAll().stream()
            .map(batchMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one batch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BatchDTO> findOne(Long id) {
        log.debug("Request to get Batch : {}", id);
        return batchRepository.findById(id)
            .map(batchMapper::toDto);
    }

    /**
     * Delete the batch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Batch : {}", id);
        batchRepository.deleteById(id);
        batchSearchRepository.deleteById(id);
    }

    /**
     * Search for the batch corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BatchDTO> search(String query) {
        log.debug("Request to search Batches for query {}", query);
        return StreamSupport
            .stream(batchSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(batchMapper::toDto)
            .collect(Collectors.toList());
    }
}
