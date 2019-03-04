package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.ReportsService;
import com.synectiks.cms.domain.Reports;
import com.synectiks.cms.repository.ReportsRepository;
import com.synectiks.cms.repository.search.ReportsSearchRepository;
import com.synectiks.cms.service.dto.ReportsDTO;
import com.synectiks.cms.service.mapper.ReportsMapper;
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
 * Service Implementation for managing Reports.
 */
@Service
@Transactional
public class ReportsServiceImpl implements ReportsService {

    private final Logger log = LoggerFactory.getLogger(ReportsServiceImpl.class);

    private final ReportsRepository reportsRepository;

    private final ReportsMapper reportsMapper;

    private final ReportsSearchRepository reportsSearchRepository;

    public ReportsServiceImpl(ReportsRepository reportsRepository, ReportsMapper reportsMapper, ReportsSearchRepository reportsSearchRepository) {
        this.reportsRepository = reportsRepository;
        this.reportsMapper = reportsMapper;
        this.reportsSearchRepository = reportsSearchRepository;
    }

    /**
     * Save a reports.
     *
     * @param reportsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportsDTO save(ReportsDTO reportsDTO) {
        log.debug("Request to save Reports : {}", reportsDTO);

        Reports reports = reportsMapper.toEntity(reportsDTO);
        reports = reportsRepository.save(reports);
        ReportsDTO result = reportsMapper.toDto(reports);
        reportsSearchRepository.save(reports);
        return result;
    }

    /**
     * Get all the reports.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReportsDTO> findAll() {
        log.debug("Request to get all Reports");
        return reportsRepository.findAll().stream()
            .map(reportsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one reports by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportsDTO> findOne(Long id) {
        log.debug("Request to get Reports : {}", id);
        return reportsRepository.findById(id)
            .map(reportsMapper::toDto);
    }

    /**
     * Delete the reports by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Reports : {}", id);
        reportsRepository.deleteById(id);
        reportsSearchRepository.deleteById(id);
    }

    /**
     * Search for the reports corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ReportsDTO> search(String query) {
        log.debug("Request to search Reports for query {}", query);
        return StreamSupport
            .stream(reportsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(reportsMapper::toDto)
            .collect(Collectors.toList());
    }
}
