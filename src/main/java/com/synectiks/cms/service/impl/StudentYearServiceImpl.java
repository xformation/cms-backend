package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.StudentYearService;
import com.synectiks.cms.domain.StudentYear;
import com.synectiks.cms.repository.StudentYearRepository;
import com.synectiks.cms.repository.search.StudentYearSearchRepository;
import com.synectiks.cms.service.dto.StudentYearDTO;
import com.synectiks.cms.service.mapper.StudentYearMapper;
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
 * Service Implementation for managing StudentYear.
 */
@Service
@Transactional
public class StudentYearServiceImpl implements StudentYearService {

    private final Logger log = LoggerFactory.getLogger(StudentYearServiceImpl.class);

    private final StudentYearRepository studentYearRepository;

    private final StudentYearMapper studentYearMapper;

    private final StudentYearSearchRepository studentYearSearchRepository;

    public StudentYearServiceImpl(StudentYearRepository studentYearRepository, StudentYearMapper studentYearMapper, StudentYearSearchRepository studentYearSearchRepository) {
        this.studentYearRepository = studentYearRepository;
        this.studentYearMapper = studentYearMapper;
        this.studentYearSearchRepository = studentYearSearchRepository;
    }

    /**
     * Save a studentYear.
     *
     * @param studentYearDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StudentYearDTO save(StudentYearDTO studentYearDTO) {
        log.debug("Request to save StudentYear : {}", studentYearDTO);
        StudentYear studentYear = studentYearMapper.toEntity(studentYearDTO);
        studentYear = studentYearRepository.save(studentYear);
        StudentYearDTO result = studentYearMapper.toDto(studentYear);
        studentYearSearchRepository.save(studentYear);
        return result;
    }

    /**
     * Get all the studentYears.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentYearDTO> findAll() {
        log.debug("Request to get all StudentYears");
        return studentYearRepository.findAll().stream()
            .map(studentYearMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one studentYear by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentYearDTO> findOne(Long id) {
        log.debug("Request to get StudentYear : {}", id);
        return studentYearRepository.findById(id)
            .map(studentYearMapper::toDto);
    }

    /**
     * Delete the studentYear by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentYear : {}", id);
        studentYearRepository.deleteById(id);
        studentYearSearchRepository.deleteById(id);
    }

    /**
     * Search for the studentYear corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentYearDTO> search(String query) {
        log.debug("Request to search StudentYears for query {}", query);
        return StreamSupport
            .stream(studentYearSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(studentYearMapper::toDto)
            .collect(Collectors.toList());
    }
}
