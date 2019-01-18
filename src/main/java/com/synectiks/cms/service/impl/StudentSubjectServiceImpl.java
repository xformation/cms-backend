package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.StudentSubjectService;
import com.synectiks.cms.domain.StudentSubject;
import com.synectiks.cms.repository.StudentSubjectRepository;
import com.synectiks.cms.repository.search.StudentSubjectSearchRepository;
import com.synectiks.cms.service.dto.StudentSubjectDTO;
import com.synectiks.cms.service.mapper.StudentSubjectMapper;
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
 * Service Implementation for managing StudentSubject.
 */
@Service
@Transactional
public class StudentSubjectServiceImpl implements StudentSubjectService {

    private final Logger log = LoggerFactory.getLogger(StudentSubjectServiceImpl.class);

    private final StudentSubjectRepository studentSubjectRepository;

    private final StudentSubjectMapper studentSubjectMapper;

    private final StudentSubjectSearchRepository studentSubjectSearchRepository;

    public StudentSubjectServiceImpl(StudentSubjectRepository studentSubjectRepository, StudentSubjectMapper studentSubjectMapper, StudentSubjectSearchRepository studentSubjectSearchRepository) {
        this.studentSubjectRepository = studentSubjectRepository;
        this.studentSubjectMapper = studentSubjectMapper;
        this.studentSubjectSearchRepository = studentSubjectSearchRepository;
    }

    /**
     * Save a studentSubject.
     *
     * @param studentSubjectDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StudentSubjectDTO save(StudentSubjectDTO studentSubjectDTO) {
        log.debug("Request to save StudentSubject : {}", studentSubjectDTO);
        StudentSubject studentSubject = studentSubjectMapper.toEntity(studentSubjectDTO);
        studentSubject = studentSubjectRepository.save(studentSubject);
        StudentSubjectDTO result = studentSubjectMapper.toDto(studentSubject);
        studentSubjectSearchRepository.save(studentSubject);
        return result;
    }

    /**
     * Get all the studentSubjects.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentSubjectDTO> findAll() {
        log.debug("Request to get all StudentSubjects");
        return studentSubjectRepository.findAll().stream()
            .map(studentSubjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one studentSubject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentSubjectDTO> findOne(Long id) {
        log.debug("Request to get StudentSubject : {}", id);
        return studentSubjectRepository.findById(id)
            .map(studentSubjectMapper::toDto);
    }

    /**
     * Delete the studentSubject by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentSubject : {}", id);
        studentSubjectRepository.deleteById(id);
        studentSubjectSearchRepository.deleteById(id);
    }

    /**
     * Search for the studentSubject corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentSubjectDTO> search(String query) {
        log.debug("Request to search StudentSubjects for query {}", query);
        return StreamSupport
            .stream(studentSubjectSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(studentSubjectMapper::toDto)
            .collect(Collectors.toList());
    }
}
