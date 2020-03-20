package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.AdminAttendance;
import com.synectiks.cms.repository.AdminAttendanceRepository;
import com.synectiks.cms.repository.search.AdminAttendanceSearchRepository;
import com.synectiks.cms.service.AdminAttendanceService;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;
import com.synectiks.cms.service.mapper.AdminAttendanceMapper;

/**
 * Service Implementation for managing AdminAttendance.
 */
@Service
@Transactional
public class AdminAttendanceServiceImpl implements AdminAttendanceService {

    private final Logger log = LoggerFactory.getLogger(AdminAttendanceServiceImpl.class);

    private final AdminAttendanceRepository adminAttendanceRepository;

    private final AdminAttendanceMapper adminAttendanceMapper;

    private final AdminAttendanceSearchRepository adminAttendanceSearchRepository;

    public AdminAttendanceServiceImpl(AdminAttendanceRepository adminAttendanceRepository, AdminAttendanceMapper adminAttendanceMapper, AdminAttendanceSearchRepository adminAttendanceSearchRepository) {
        this.adminAttendanceRepository = adminAttendanceRepository;
        this.adminAttendanceMapper = adminAttendanceMapper;
        this.adminAttendanceSearchRepository = adminAttendanceSearchRepository;
    }

    /**
     * Save a adminAttendance.
     *
     * @param adminAttendanceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AdminAttendanceDTO save(AdminAttendanceDTO adminAttendanceDTO) {
        log.debug("Request to save AdminAttendance : {}", adminAttendanceDTO);

        AdminAttendance adminAttendance = adminAttendanceMapper.toEntity(adminAttendanceDTO);
        adminAttendance = adminAttendanceRepository.save(adminAttendance);
        AdminAttendanceDTO result = adminAttendanceMapper.toDto(adminAttendance);
        adminAttendanceSearchRepository.save(adminAttendance);
        return result;
    }

    /**
     * Get all the adminAttendances.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdminAttendanceDTO> findAll() {
        log.debug("Request to get all AdminAttendances");
        return adminAttendanceRepository.findAll().stream()
            .map(adminAttendanceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one adminAttendance by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AdminAttendanceDTO> findOne(Long id) {
        log.debug("Request to get AdminAttendance : {}", id);
        return adminAttendanceRepository.findById(id)
            .map(adminAttendanceMapper::toDto);
    }

    /**
     * Delete the adminAttendance by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdminAttendance : {}", id);
        adminAttendanceRepository.deleteById(id);
        adminAttendanceSearchRepository.deleteById(id);
    }

    /**
     * Search for the adminAttendance corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AdminAttendanceDTO> search(String query) {
        log.debug("Request to search AdminAttendances for query {}", query);
        return null;
    }
}
