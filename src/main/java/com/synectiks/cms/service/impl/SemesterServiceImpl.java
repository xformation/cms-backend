package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.SemesterService;
import com.synectiks.cms.domain.Semester;
import com.synectiks.cms.repository.SemesterRepository;
import com.synectiks.cms.repository.search.SemesterSearchRepository;
import com.synectiks.cms.service.dto.SemesterDTO;
import com.synectiks.cms.service.mapper.SemesterMapper;
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
 * Service Implementation for managing Semester.
 */
@Service
@Transactional
public class SemesterServiceImpl implements SemesterService {

    private final Logger log = LoggerFactory.getLogger(SemesterServiceImpl.class);

    private final SemesterRepository semesterRepository;

    private final SemesterMapper semesterMapper;

    private final SemesterSearchRepository semesterSearchRepository;

    public SemesterServiceImpl(SemesterRepository semesterRepository, SemesterMapper semesterMapper, SemesterSearchRepository semesterSearchRepository) {
        this.semesterRepository = semesterRepository;
        this.semesterMapper = semesterMapper;
        this.semesterSearchRepository = semesterSearchRepository;
    }

    /**
     * Save a semester.
     *
     * @param semesterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SemesterDTO save(SemesterDTO semesterDTO) {
        log.debug("Request to save Semester : {}", semesterDTO);
        Semester semester = semesterMapper.toEntity(semesterDTO);
        semester = semesterRepository.save(semester);
        SemesterDTO result = semesterMapper.toDto(semester);
        semesterSearchRepository.save(semester);
        return result;
    }

    /**
     * Get all the semesters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SemesterDTO> findAll() {
        log.debug("Request to get all Semesters");
        return semesterRepository.findAll().stream()
            .map(semesterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one semester by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SemesterDTO> findOne(Long id) {
        log.debug("Request to get Semester : {}", id);
        return semesterRepository.findById(id)
            .map(semesterMapper::toDto);
    }

    /**
     * Delete the semester by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Semester : {}", id);
        semesterRepository.deleteById(id);
        semesterSearchRepository.deleteById(id);
    }

    /**
     * Search for the semester corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SemesterDTO> search(String query) {
        log.debug("Request to search Semesters for query {}", query);
        return StreamSupport
            .stream(semesterSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(semesterMapper::toDto)
            .collect(Collectors.toList());
    }
}
