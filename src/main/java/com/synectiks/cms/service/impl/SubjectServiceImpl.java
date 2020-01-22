package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.SubjectService;
import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.repository.SubjectRepository;
import com.synectiks.cms.repository.search.SubjectSearchRepository;
import com.synectiks.cms.service.dto.SubjectDTO;
import com.synectiks.cms.service.mapper.SubjectMapper;
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
 * Service Implementation for managing {@link Subject}.
 */
@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final Logger log = LoggerFactory.getLogger(SubjectServiceImpl.class);

    private final SubjectRepository subjectRepository;

    private final SubjectMapper subjectMapper;

    private final SubjectSearchRepository subjectSearchRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper, SubjectSearchRepository subjectSearchRepository) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
        this.subjectSearchRepository = subjectSearchRepository;
    }

    /**
     * Save a subject.
     *
     * @param subjectDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public SubjectDTO save(SubjectDTO subjectDTO) {
        log.debug("Request to save Subject : {}", subjectDTO);
        Subject subject = subjectMapper.toEntity(subjectDTO);
        subject = subjectRepository.save(subject);
        SubjectDTO result = subjectMapper.toDto(subject);
        subjectSearchRepository.save(subject);
        return result;
    }

    /**
     * Get all the subjects.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> findAll() {
        log.debug("Request to get all Subjects");
        return subjectRepository.findAll().stream()
            .map(subjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one subject by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SubjectDTO> findOne(Long id) {
        log.debug("Request to get Subject : {}", id);
        return subjectRepository.findById(id)
            .map(subjectMapper::toDto);
    }

    /**
     * Delete the subject by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Subject : {}", id);
        subjectRepository.deleteById(id);
        subjectSearchRepository.deleteById(id);
    }

    /**
     * Search for the subject corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<SubjectDTO> search(String query) {
        log.debug("Request to search Subjects for query {}", query);
        return StreamSupport
            .stream(subjectSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(subjectMapper::toDto)
            .collect(Collectors.toList());
    }
}
