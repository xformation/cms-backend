package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.FeeDetailsService;
import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.repository.FeeDetailsRepository;
import com.synectiks.cms.repository.search.FeeDetailsSearchRepository;
import com.synectiks.cms.service.dto.FeeDetailsDTO;
import com.synectiks.cms.service.mapper.FeeDetailsMapper;
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
 * Service Implementation for managing FeeDetails.
 */
@Service
@Transactional
public class FeeDetailsServiceImpl implements FeeDetailsService {

    private final Logger log = LoggerFactory.getLogger(FeeDetailsServiceImpl.class);

    private final FeeDetailsRepository feeDetailsRepository;

    private final FeeDetailsMapper feeDetailsMapper;

    private final FeeDetailsSearchRepository feeDetailsSearchRepository;

    public FeeDetailsServiceImpl(FeeDetailsRepository feeDetailsRepository, FeeDetailsMapper feeDetailsMapper, FeeDetailsSearchRepository feeDetailsSearchRepository) {
        this.feeDetailsRepository = feeDetailsRepository;
        this.feeDetailsMapper = feeDetailsMapper;
        this.feeDetailsSearchRepository = feeDetailsSearchRepository;
    }

    /**
     * Save a feeDetails.
     *
     * @param feeDetailsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeeDetailsDTO save(FeeDetailsDTO feeDetailsDTO) {
        log.debug("Request to save FeeDetails : {}", feeDetailsDTO);
        FeeDetails feeDetails = feeDetailsMapper.toEntity(feeDetailsDTO);
        feeDetails = feeDetailsRepository.save(feeDetails);
        FeeDetailsDTO result = feeDetailsMapper.toDto(feeDetails);
        feeDetailsSearchRepository.save(feeDetails);
        return result;
    }

    /**
     * Get all the feeDetails.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FeeDetailsDTO> findAll() {
        log.debug("Request to get all FeeDetails");
        return feeDetailsRepository.findAll().stream()
            .map(feeDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one feeDetails by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeeDetailsDTO> findOne(Long id) {
        log.debug("Request to get FeeDetails : {}", id);
        return feeDetailsRepository.findById(id)
            .map(feeDetailsMapper::toDto);
    }

    /**
     * Delete the feeDetails by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FeeDetails : {}", id);
        feeDetailsRepository.deleteById(id);
        feeDetailsSearchRepository.deleteById(id);
    }

    /**
     * Search for the feeDetails corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FeeDetailsDTO> search(String query) {
        log.debug("Request to search FeeDetails for query {}", query);
        return StreamSupport
            .stream(feeDetailsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(feeDetailsMapper::toDto)
            .collect(Collectors.toList());
    }
}
