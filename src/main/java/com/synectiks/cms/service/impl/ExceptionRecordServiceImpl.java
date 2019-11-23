package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.ExceptionRecordService;
import com.synectiks.commons.entities.cms.ExceptionRecord;
import com.synectiks.cms.repository.ExceptionRecordRepository;
//import com.synectiks.cms.repository.search.ExceptionRecordSearchRepository;
import com.synectiks.cms.service.dto.ExceptionRecordDTO;
import com.synectiks.cms.service.mapper.ExceptionRecordMapper;
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
 * Service Implementation for managing {@link ExceptionRecord}.
 */
@Service
@Transactional
public class ExceptionRecordServiceImpl implements ExceptionRecordService {

    private final Logger log = LoggerFactory.getLogger(ExceptionRecordServiceImpl.class);

    private final ExceptionRecordRepository exceptionRecordRepository;

    private final ExceptionRecordMapper exceptionRecordMapper;

    //private final ExceptionRecordSearchRepository exceptionRecordSearchRepository;

    public ExceptionRecordServiceImpl(ExceptionRecordRepository exceptionRecordRepository, ExceptionRecordMapper exceptionRecordMapper/*, ExceptionRecordSearchRepository exceptionRecordSearchRepository*/) {
        this.exceptionRecordRepository = exceptionRecordRepository;
        this.exceptionRecordMapper = exceptionRecordMapper;
        //this.exceptionRecordSearchRepository = exceptionRecordSearchRepository;
    }

    /**
     * Save a exceptionRecord.
     *
     * @param exceptionRecordDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ExceptionRecordDTO save(ExceptionRecordDTO exceptionRecordDTO) {
        log.debug("Request to save ExceptionRecord : {}", exceptionRecordDTO);
        ExceptionRecord exceptionRecord = exceptionRecordMapper.toEntity(exceptionRecordDTO);
        exceptionRecord = exceptionRecordRepository.save(exceptionRecord);
        ExceptionRecordDTO result = exceptionRecordMapper.toDto(exceptionRecord);
        //exceptionRecordSearchRepository.save(exceptionRecord);
        return result;
    }

    /**
     * Get all the exceptionRecords.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExceptionRecordDTO> findAll() {
        log.debug("Request to get all ExceptionRecords");
        return exceptionRecordRepository.findAll().stream()
            .map(exceptionRecordMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one exceptionRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ExceptionRecordDTO> findOne(Long id) {
        log.debug("Request to get ExceptionRecord : {}", id);
        return exceptionRecordRepository.findById(id)
            .map(exceptionRecordMapper::toDto);
    }

    /**
     * Delete the exceptionRecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ExceptionRecord : {}", id);
        exceptionRecordRepository.deleteById(id);
        //exceptionRecordSearchRepository.deleteById(id);
    }

    /**
     * Search for the exceptionRecord corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ExceptionRecordDTO> search(String query) {
        log.debug("Request to search ExceptionRecords for query {}", query);
        /*return StreamSupport
            .stream(exceptionRecordSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(exceptionRecordMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
