package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.LateFee;
import com.synectiks.cms.repository.LateFeeRepository;
import com.synectiks.cms.repository.search.LateFeeSearchRepository;
import com.synectiks.cms.service.LateFeeService;
import com.synectiks.cms.service.dto.LateFeeDTO;
import com.synectiks.cms.service.mapper.LateFeeMapper;

/**
 * Service Implementation for managing LateFee.
 */
@Service
@Transactional
public class LateFeeServiceImpl implements LateFeeService {

    private final Logger log = LoggerFactory.getLogger(LateFeeServiceImpl.class);

    private final LateFeeRepository lateFeeRepository;

    private final LateFeeMapper lateFeeMapper;

    private final LateFeeSearchRepository lateFeeSearchRepository;

    public LateFeeServiceImpl(LateFeeRepository lateFeeRepository, LateFeeMapper lateFeeMapper, LateFeeSearchRepository lateFeeSearchRepository) {
        this.lateFeeRepository = lateFeeRepository;
        this.lateFeeMapper = lateFeeMapper;
        this.lateFeeSearchRepository = lateFeeSearchRepository;
    }

    /**
     * Save a lateFee.
     *
     * @param lateFeeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LateFeeDTO save(LateFeeDTO lateFeeDTO) {
        log.debug("Request to save LateFee : {}", lateFeeDTO);
        LateFee lateFee = lateFeeMapper.toEntity(lateFeeDTO);
        lateFee = lateFeeRepository.save(lateFee);
        LateFeeDTO result = lateFeeMapper.toDto(lateFee);
        lateFeeSearchRepository.save(lateFee);
        return result;
    }

    /**
     * Get all the lateFees.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LateFeeDTO> findAll() {
        log.debug("Request to get all LateFees");
        return lateFeeRepository.findAll().stream()
            .map(lateFeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one lateFee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LateFeeDTO> findOne(Long id) {
        log.debug("Request to get LateFee : {}", id);
        return lateFeeRepository.findById(id)
            .map(lateFeeMapper::toDto);
    }

    /**
     * Delete the lateFee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LateFee : {}", id);
        lateFeeRepository.deleteById(id);
        lateFeeSearchRepository.deleteById(id);
    }

    /**
     * Search for the lateFee corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LateFeeDTO> search(String query) {
        log.debug("Request to search LateFees for query {}", query);
        return null;
    }
}
