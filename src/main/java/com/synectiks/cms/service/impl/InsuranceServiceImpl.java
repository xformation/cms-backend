package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.Insurance;
import com.synectiks.cms.repository.InsuranceRepository;
import com.synectiks.cms.repository.search.InsuranceSearchRepository;
import com.synectiks.cms.service.InsuranceService;
import com.synectiks.cms.service.dto.InsuranceDTO;
import com.synectiks.cms.service.mapper.InsuranceMapper;

/**
 * Service Implementation for managing Insurance.
 */
@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private final Logger log = LoggerFactory.getLogger(InsuranceServiceImpl.class);

    private final InsuranceRepository insuranceRepository;

    private final InsuranceMapper insuranceMapper;

    private final InsuranceSearchRepository insuranceSearchRepository;

    public InsuranceServiceImpl(InsuranceRepository insuranceRepository, InsuranceMapper insuranceMapper, InsuranceSearchRepository insuranceSearchRepository) {
        this.insuranceRepository = insuranceRepository;
        this.insuranceMapper = insuranceMapper;
        this.insuranceSearchRepository = insuranceSearchRepository;
    }

    /**
     * Save a insurance.
     *
     * @param insuranceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InsuranceDTO save(InsuranceDTO insuranceDTO) {
        log.debug("Request to save Insurance : {}", insuranceDTO);
        Insurance insurance = insuranceMapper.toEntity(insuranceDTO);
        insurance = insuranceRepository.save(insurance);
        InsuranceDTO result = insuranceMapper.toDto(insurance);
        insuranceSearchRepository.save(insurance);
        return result;
    }

    /**
     * Get all the insurances.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InsuranceDTO> findAll() {
        log.debug("Request to get all Insurances");
        return insuranceRepository.findAll().stream()
            .map(insuranceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one insurance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InsuranceDTO> findOne(Long id) {
        log.debug("Request to get Insurance : {}", id);
        return insuranceRepository.findById(id)
            .map(insuranceMapper::toDto);
    }

    /**
     * Delete the insurance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Insurance : {}", id);
        insuranceRepository.deleteById(id);
        insuranceSearchRepository.deleteById(id);
    }

    /**
     * Search for the insurance corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<InsuranceDTO> search(String query) {
        log.debug("Request to search Insurances for query {}", query);
        return null;
    }
}
