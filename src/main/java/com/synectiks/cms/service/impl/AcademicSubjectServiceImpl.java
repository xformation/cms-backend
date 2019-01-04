package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AcademicSubjectService;
import com.synectiks.cms.domain.AcademicSubject;
import com.synectiks.cms.repository.AcademicSubjectRepository;
import com.synectiks.cms.repository.search.AcademicSubjectSearchRepository;
import com.synectiks.cms.service.dto.AcademicSubjectDTO;
import com.synectiks.cms.service.mapper.AcademicSubjectMapper;
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
 * Service Implementation for managing AcademicSubject.
 */
@Service
@Transactional
public class AcademicSubjectServiceImpl implements AcademicSubjectService {

    private final Logger log = LoggerFactory.getLogger(AcademicSubjectServiceImpl.class);

    private final AcademicSubjectRepository academicSubjectRepository;

    private final AcademicSubjectMapper academicSubjectMapper;

    private final AcademicSubjectSearchRepository academicSubjectSearchRepository;

    public AcademicSubjectServiceImpl(AcademicSubjectRepository academicSubjectRepository, AcademicSubjectMapper academicSubjectMapper, AcademicSubjectSearchRepository academicSubjectSearchRepository) {
        this.academicSubjectRepository = academicSubjectRepository;
        this.academicSubjectMapper = academicSubjectMapper;
        this.academicSubjectSearchRepository = academicSubjectSearchRepository;
    }

    /**
     * Save a academicSubject.
     *
     * @param academicSubjectDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AcademicSubjectDTO save(AcademicSubjectDTO academicSubjectDTO) {
        log.debug("Request to save AcademicSubject : {}", academicSubjectDTO);

        AcademicSubject academicSubject = academicSubjectMapper.toEntity(academicSubjectDTO);
        academicSubject = academicSubjectRepository.save(academicSubject);
        AcademicSubjectDTO result = academicSubjectMapper.toDto(academicSubject);
        academicSubjectSearchRepository.save(academicSubject);
        return result;
    }

    /**
     * Get all the academicSubjects.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicSubjectDTO> findAll() {
        log.debug("Request to get all AcademicSubjects");
        return academicSubjectRepository.findAll().stream()
            .map(academicSubjectMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one academicSubject by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicSubjectDTO> findOne(Long id) {
        log.debug("Request to get AcademicSubject : {}", id);
        return academicSubjectRepository.findById(id)
            .map(academicSubjectMapper::toDto);
    }

    /**
     * Delete the academicSubject by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicSubject : {}", id);
        academicSubjectRepository.deleteById(id);
        academicSubjectSearchRepository.deleteById(id);
    }

    /**
     * Search for the academicSubject corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicSubjectDTO> search(String query) {
        log.debug("Request to search AcademicSubjects for query {}", query);
        return StreamSupport
            .stream(academicSubjectSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(academicSubjectMapper::toDto)
            .collect(Collectors.toList());
    }
}
