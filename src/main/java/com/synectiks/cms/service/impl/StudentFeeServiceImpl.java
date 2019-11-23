package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.StudentFeeService;
import com.synectiks.commons.entities.cms.StudentFee;
import com.synectiks.cms.repository.StudentFeeRepository;
//import com.synectiks.cms.repository.search.StudentFeeSearchRepository;
import com.synectiks.cms.service.dto.StudentFeeDTO;
import com.synectiks.cms.service.mapper.StudentFeeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

//import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing StudentFee.
 */
@Service
@Transactional
public class StudentFeeServiceImpl implements StudentFeeService {

    private final Logger log = LoggerFactory.getLogger(StudentFeeServiceImpl.class);

    private final StudentFeeRepository studentFeeRepository;

    private final StudentFeeMapper studentFeeMapper;

    //private final StudentFeeSearchRepository studentFeeSearchRepository;

    public StudentFeeServiceImpl(StudentFeeRepository studentFeeRepository, StudentFeeMapper studentFeeMapper/*, StudentFeeSearchRepository studentFeeSearchRepository*/) {
        this.studentFeeRepository = studentFeeRepository;
        this.studentFeeMapper = studentFeeMapper;
        //this.studentFeeSearchRepository = studentFeeSearchRepository;
    }

    /**
     * Save a studentFee.
     *
     * @param studentFeeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public StudentFeeDTO save(StudentFeeDTO studentFeeDTO) {
        log.debug("Request to save StudentFee : {}", studentFeeDTO);

        StudentFee studentFee = studentFeeMapper.toEntity(studentFeeDTO);
        studentFee = studentFeeRepository.save(studentFee);
        StudentFeeDTO result = studentFeeMapper.toDto(studentFee);
        //studentFeeSearchRepository.save(studentFee);
        return result;
    }

    /**
     * Get all the studentFees.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentFeeDTO> findAll() {
        log.debug("Request to get all StudentFees");
        return studentFeeRepository.findAll().stream()
            .map(studentFeeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one studentFee by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<StudentFeeDTO> findOne(Long id) {
        log.debug("Request to get StudentFee : {}", id);
        return studentFeeRepository.findById(id)
            .map(studentFeeMapper::toDto);
    }

    /**
     * Delete the studentFee by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete StudentFee : {}", id);
        studentFeeRepository.deleteById(id);
        //studentFeeSearchRepository.deleteById(id);
    }

    /**
     * Search for the studentFee corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<StudentFeeDTO> search(String query) {
        log.debug("Request to search StudentFees for query {}", query);
        /*return StreamSupport
            .stream(studentFeeSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(studentFeeMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
