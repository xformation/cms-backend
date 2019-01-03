package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AcademicDepartmentService;
import com.synectiks.cms.domain.AcademicDepartment;
import com.synectiks.cms.repository.AcademicDepartmentRepository;
import com.synectiks.cms.repository.search.AcademicDepartmentSearchRepository;
import com.synectiks.cms.service.dto.AcademicDepartmentDTO;
import com.synectiks.cms.service.mapper.AcademicDepartmentMapper;
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
 * Service Implementation for managing AcademicDepartment.
 */
@Service
@Transactional
public class AcademicDepartmentServiceImpl implements AcademicDepartmentService {

    private final Logger log = LoggerFactory.getLogger(AcademicDepartmentServiceImpl.class);

    private final AcademicDepartmentRepository academicDepartmentRepository;

    private final AcademicDepartmentMapper academicDepartmentMapper;

    private final AcademicDepartmentSearchRepository academicDepartmentSearchRepository;

    public AcademicDepartmentServiceImpl(AcademicDepartmentRepository academicDepartmentRepository, AcademicDepartmentMapper academicDepartmentMapper, AcademicDepartmentSearchRepository academicDepartmentSearchRepository) {
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.academicDepartmentMapper = academicDepartmentMapper;
        this.academicDepartmentSearchRepository = academicDepartmentSearchRepository;
    }

    /**
     * Save a academicDepartment.
     *
     * @param academicDepartmentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AcademicDepartmentDTO save(AcademicDepartmentDTO academicDepartmentDTO) {
        log.debug("Request to save AcademicDepartment : {}", academicDepartmentDTO);

        AcademicDepartment academicDepartment = academicDepartmentMapper.toEntity(academicDepartmentDTO);
        academicDepartment = academicDepartmentRepository.save(academicDepartment);
        AcademicDepartmentDTO result = academicDepartmentMapper.toDto(academicDepartment);
        academicDepartmentSearchRepository.save(academicDepartment);
        return result;
    }

    /**
     * Get all the academicDepartments.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicDepartmentDTO> findAll() {
        log.debug("Request to get all AcademicDepartments");
        return academicDepartmentRepository.findAll().stream()
            .map(academicDepartmentMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one academicDepartment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicDepartmentDTO> findOne(Long id) {
        log.debug("Request to get AcademicDepartment : {}", id);
        return academicDepartmentRepository.findById(id)
            .map(academicDepartmentMapper::toDto);
    }

    /**
     * Delete the academicDepartment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicDepartment : {}", id);
        academicDepartmentRepository.deleteById(id);
        academicDepartmentSearchRepository.deleteById(id);
    }

    /**
     * Search for the academicDepartment corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicDepartmentDTO> search(String query) {
        log.debug("Request to search AcademicDepartments for query {}", query);
        return StreamSupport
            .stream(academicDepartmentSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(academicDepartmentMapper::toDto)
            .collect(Collectors.toList());
    }
}
