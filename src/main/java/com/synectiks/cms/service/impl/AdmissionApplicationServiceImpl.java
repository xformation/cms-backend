package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.repository.search.AdmissionApplicationSearchRepository;
import com.synectiks.cms.service.AdmissionApplicationService;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;
import com.synectiks.cms.service.mapper.AdmissionApplicationMapper;

/**
 * Service Implementation for managing AdmissionApplication.
 */
@Service
@Transactional
public class AdmissionApplicationServiceImpl implements AdmissionApplicationService {

    private final Logger log = LoggerFactory.getLogger(AdmissionApplicationServiceImpl.class);

    private final AdmissionApplicationRepository admissionApplicationRepository;

    private final AdmissionApplicationMapper admissionApplicationMapper;

    private final AdmissionApplicationSearchRepository admissionApplicationSearchRepository;

    public AdmissionApplicationServiceImpl(AdmissionApplicationRepository admissionApplicationRepository, AdmissionApplicationMapper admissionApplicationMapper, AdmissionApplicationSearchRepository admissionApplicationSearchRepository) {
        this.admissionApplicationRepository = admissionApplicationRepository;
        this.admissionApplicationMapper = admissionApplicationMapper;
        this.admissionApplicationSearchRepository = admissionApplicationSearchRepository;
    }

    /**
     * Save a admissionApplication.
     *
     * @param admissionApplicationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdmissionApplicationDTO save(AdmissionApplicationDTO admissionApplicationDTO) {
        log.debug("Request to save AdmissionApplication : {}", admissionApplicationDTO);
        AdmissionApplication admissionApplication = admissionApplicationMapper.toEntity(admissionApplicationDTO);
        admissionApplication = admissionApplicationRepository.save(admissionApplication);
        AdmissionApplicationDTO result = admissionApplicationMapper.toDto(admissionApplication);
        admissionApplicationSearchRepository.save(admissionApplication);
        return result;
    }

    /**
     * Get all the admissionApplications.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdmissionApplicationDTO> findAll() {
        log.debug("Request to get all AdmissionApplications");
        return admissionApplicationRepository.findAll().stream()
            .map(admissionApplicationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one admissionApplication by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdmissionApplicationDTO> findOne(Long id) {
        log.debug("Request to get AdmissionApplication : {}", id);
        return admissionApplicationRepository.findById(id)
            .map(admissionApplicationMapper::toDto);
    }

    /**
     * Delete the admissionApplication by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdmissionApplication : {}", id);        admissionApplicationRepository.deleteById(id);
        admissionApplicationSearchRepository.deleteById(id);
    }

    /**
     * Search for the admissionApplication corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdmissionApplicationDTO> search(String query) {
        log.debug("Request to search AdmissionApplications for query {}", query);
        return null;
    }
}
