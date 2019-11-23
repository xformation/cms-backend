package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AcademicHistoryService;
import com.synectiks.commons.entities.cms.AcademicHistory;
import com.synectiks.cms.repository.AcademicHistoryRepository;
//import com.synectiks.cms.repository.search.AcademicHistorySearchRepository;
import com.synectiks.cms.service.dto.AcademicHistoryDTO;
import com.synectiks.cms.service.mapper.AcademicHistoryMapper;
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
 * Service Implementation for managing AcademicHistory.
 */
@Service
@Transactional
public class AcademicHistoryServiceImpl implements AcademicHistoryService {

    private final Logger log = LoggerFactory.getLogger(AcademicHistoryServiceImpl.class);

    private final AcademicHistoryRepository academicHistoryRepository;

    private final AcademicHistoryMapper academicHistoryMapper;

    //private final AcademicHistorySearchRepository academicHistorySearchRepository;

    public AcademicHistoryServiceImpl(AcademicHistoryRepository academicHistoryRepository, AcademicHistoryMapper academicHistoryMapper/*, AcademicHistorySearchRepository academicHistorySearchRepository*/) {
        this.academicHistoryRepository = academicHistoryRepository;
        this.academicHistoryMapper = academicHistoryMapper;
        //this.academicHistorySearchRepository = academicHistorySearchRepository;
    }

    /**
     * Save a academicHistory.
     *
     * @param academicHistoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AcademicHistoryDTO save(AcademicHistoryDTO academicHistoryDTO) {
        log.debug("Request to save AcademicHistory : {}", academicHistoryDTO);
        AcademicHistory academicHistory = academicHistoryMapper.toEntity(academicHistoryDTO);
        academicHistory = academicHistoryRepository.save(academicHistory);
        AcademicHistoryDTO result = academicHistoryMapper.toDto(academicHistory);
        //academicHistorySearchRepository.save(academicHistory);
        return result;
    }

    /**
     * Get all the academicHistories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicHistoryDTO> findAll() {
        log.debug("Request to get all AcademicHistories");
        return academicHistoryRepository.findAll().stream()
            .map(academicHistoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one academicHistory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicHistoryDTO> findOne(Long id) {
        log.debug("Request to get AcademicHistory : {}", id);
        return academicHistoryRepository.findById(id)
            .map(academicHistoryMapper::toDto);
    }

    /**
     * Delete the academicHistory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicHistory : {}", id);
        academicHistoryRepository.deleteById(id);
        //academicHistorySearchRepository.deleteById(id);
    }

    /**
     * Search for the academicHistory corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicHistoryDTO> search(String query) {
        log.debug("Request to search AcademicHistories for query {}", query);
        /*return StreamSupport
            .stream(academicHistorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(academicHistoryMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
