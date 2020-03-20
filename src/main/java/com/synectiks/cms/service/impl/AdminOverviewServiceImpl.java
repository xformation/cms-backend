package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AdminOverview;
import com.synectiks.cms.repository.AdminOverviewRepository;
import com.synectiks.cms.repository.search.AdminOverviewSearchRepository;
import com.synectiks.cms.service.AdminOverviewService;
import com.synectiks.cms.service.dto.AdminOverviewDTO;
import com.synectiks.cms.service.mapper.AdminOverviewMapper;

/**
 * Service Implementation for managing AdminOverview.
 */
@Service
@Transactional
public class AdminOverviewServiceImpl implements AdminOverviewService {

    private final Logger log = LoggerFactory.getLogger(AdminOverviewServiceImpl.class);

    private final AdminOverviewRepository adminOverviewRepository;

    private final AdminOverviewMapper adminOverviewMapper;

    private final AdminOverviewSearchRepository adminOverviewSearchRepository;

    public AdminOverviewServiceImpl(AdminOverviewRepository adminOverviewRepository, AdminOverviewMapper adminOverviewMapper, AdminOverviewSearchRepository adminOverviewSearchRepository) {
        this.adminOverviewRepository = adminOverviewRepository;
        this.adminOverviewMapper = adminOverviewMapper;
        this.adminOverviewSearchRepository = adminOverviewSearchRepository;
    }

    /**
     * Save a adminOverview.
     *
     * @param adminOverviewDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdminOverviewDTO save(AdminOverviewDTO adminOverviewDTO) {
        log.debug("Request to save AdminOverview : {}", adminOverviewDTO);
        AdminOverview adminOverview = adminOverviewMapper.toEntity(adminOverviewDTO);
        adminOverview = adminOverviewRepository.save(adminOverview);
        AdminOverviewDTO result = adminOverviewMapper.toDto(adminOverview);
        adminOverviewSearchRepository.save(adminOverview);
        return result;
    }

    /**
     * Get all the adminOverviews.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdminOverviewDTO> findAll() {
        log.debug("Request to get all AdminOverviews");
        return adminOverviewRepository.findAll().stream()
            .map(adminOverviewMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adminOverview by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdminOverviewDTO> findOne(Long id) {
        log.debug("Request to get AdminOverview : {}", id);
        return adminOverviewRepository.findById(id)
            .map(adminOverviewMapper::toDto);
    }

    /**
     * Delete the adminOverview by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdminOverview : {}", id);
        adminOverviewRepository.deleteById(id);
        adminOverviewSearchRepository.deleteById(id);
    }

    /**
     * Search for the adminOverview corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdminOverviewDTO> search(String query) {
        log.debug("Request to search AdminOverviews for query {}", query);
        return null;
    }
}
