package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.DepartmentsService;
import com.synectiks.cms.domain.Departments;
import com.synectiks.cms.repository.DepartmentsRepository;
import com.synectiks.cms.repository.search.DepartmentsSearchRepository;
import com.synectiks.cms.service.dto.DepartmentsDTO;
import com.synectiks.cms.service.mapper.DepartmentsMapper;
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
 * Service Implementation for managing Departments.
 */
@Service
@Transactional
public class DepartmentsServiceImpl implements DepartmentsService {

    private final Logger log = LoggerFactory.getLogger(DepartmentsServiceImpl.class);

    private final DepartmentsRepository departmentsRepository;

    private final DepartmentsMapper departmentsMapper;

    private final DepartmentsSearchRepository departmentsSearchRepository;

    public DepartmentsServiceImpl(DepartmentsRepository departmentsRepository, DepartmentsMapper departmentsMapper, DepartmentsSearchRepository departmentsSearchRepository) {
        this.departmentsRepository = departmentsRepository;
        this.departmentsMapper = departmentsMapper;
        this.departmentsSearchRepository = departmentsSearchRepository;
    }

    /**
     * Save a departments.
     *
     * @param departmentsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DepartmentsDTO save(DepartmentsDTO departmentsDTO) {
        log.debug("Request to save Departments : {}", departmentsDTO);
        Departments departments = departmentsMapper.toEntity(departmentsDTO);
        departments = departmentsRepository.save(departments);
        DepartmentsDTO result = departmentsMapper.toDto(departments);
        departmentsSearchRepository.save(departments);
        return result;
    }

    /**
     * Get all the departments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentsDTO> findAll() {
        log.debug("Request to get all Departments");
        return departmentsRepository.findAll().stream()
            .map(departmentsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one departments by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DepartmentsDTO> findOne(Long id) {
        log.debug("Request to get Departments : {}", id);
        return departmentsRepository.findById(id)
            .map(departmentsMapper::toDto);
    }

    /**
     * Delete the departments by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Departments : {}", id);
        departmentsRepository.deleteById(id);
        departmentsSearchRepository.deleteById(id);
    }

    /**
     * Search for the departments corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DepartmentsDTO> search(String query) {
        log.debug("Request to search Departments for query {}", query);
        return StreamSupport
            .stream(departmentsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(departmentsMapper::toDto)
            .collect(Collectors.toList());
    }
}
