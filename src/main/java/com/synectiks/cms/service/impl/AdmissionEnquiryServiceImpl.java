package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.repository.AdmissionEnquiryRepository;
import com.synectiks.cms.repository.search.AdmissionEnquirySearchRepository;
import com.synectiks.cms.service.AdmissionEnquiryService;
import com.synectiks.cms.service.dto.AdmissionEnquiryDTO;
import com.synectiks.cms.service.mapper.AdmissionEnquiryMapper;

/**
 * Service Implementation for managing AdmissionEnquiry.
 */
@Service
@Transactional
public class AdmissionEnquiryServiceImpl implements AdmissionEnquiryService {

    private final Logger log = LoggerFactory.getLogger(AdmissionEnquiryServiceImpl.class);

    private final AdmissionEnquiryRepository admissionEnquiryRepository;

    private final AdmissionEnquiryMapper admissionEnquiryMapper;

    private final AdmissionEnquirySearchRepository admissionEnquirySearchRepository;

    public AdmissionEnquiryServiceImpl(AdmissionEnquiryRepository admissionEnquiryRepository, AdmissionEnquiryMapper admissionEnquiryMapper, AdmissionEnquirySearchRepository admissionEnquirySearchRepository) {
        this.admissionEnquiryRepository = admissionEnquiryRepository;
        this.admissionEnquiryMapper = admissionEnquiryMapper;
        this.admissionEnquirySearchRepository = admissionEnquirySearchRepository;
    }

    /**
     * Save a admissionEnquiry.
     *
     * @param admissionEnquiryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdmissionEnquiryDTO save(AdmissionEnquiryDTO admissionEnquiryDTO) {
        log.debug("Request to save AdmissionEnquiry : {}", admissionEnquiryDTO);
        AdmissionEnquiry admissionEnquiry = admissionEnquiryMapper.toEntity(admissionEnquiryDTO);
        admissionEnquiry = admissionEnquiryRepository.save(admissionEnquiry);
        AdmissionEnquiryDTO result = admissionEnquiryMapper.toDto(admissionEnquiry);
        admissionEnquirySearchRepository.save(admissionEnquiry);
        return result;
    }

    /**
     * Get all the admissionEnquiries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdmissionEnquiryDTO> findAll() {
        log.debug("Request to get all AdmissionEnquiries");
        return admissionEnquiryRepository.findAll().stream()
            .map(admissionEnquiryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one admissionEnquiry by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdmissionEnquiryDTO> findOne(Long id) {
        log.debug("Request to get AdmissionEnquiry : {}", id);
        return admissionEnquiryRepository.findById(id)
            .map(admissionEnquiryMapper::toDto);
    }

    /**
     * Delete the admissionEnquiry by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdmissionEnquiry : {}", id);        admissionEnquiryRepository.deleteById(id);
        admissionEnquirySearchRepository.deleteById(id);
    }

    /**
     * Search for the admissionEnquiry corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdmissionEnquiryDTO> search(String query) {
        log.debug("Request to search AdmissionEnquiries for query {}", query);
        return null;
    }
}
