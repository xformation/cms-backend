package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.CompetitiveExamService;
import com.synectiks.cms.domain.CompetitiveExam;
import com.synectiks.cms.repository.CompetitiveExamRepository;
//import com.synectiks.cms.repository.search.CompetitiveExamSearchRepository;
import com.synectiks.cms.service.dto.CompetitiveExamDTO;
import com.synectiks.cms.service.mapper.CompetitiveExamMapper;
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
 * Service Implementation for managing CompetitiveExam.
 */
@Service
@Transactional
public class CompetitiveExamServiceImpl implements CompetitiveExamService {

    private final Logger log = LoggerFactory.getLogger(CompetitiveExamServiceImpl.class);

    private final CompetitiveExamRepository competitiveExamRepository;

    private final CompetitiveExamMapper competitiveExamMapper;

    //private final CompetitiveExamSearchRepository competitiveExamSearchRepository;

    public CompetitiveExamServiceImpl(CompetitiveExamRepository competitiveExamRepository, CompetitiveExamMapper competitiveExamMapper/*, CompetitiveExamSearchRepository competitiveExamSearchRepository*/) {
        this.competitiveExamRepository = competitiveExamRepository;
        this.competitiveExamMapper = competitiveExamMapper;
        //this.competitiveExamSearchRepository = competitiveExamSearchRepository;
    }

    /**
     * Save a competitiveExam.
     *
     * @param competitiveExamDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CompetitiveExamDTO save(CompetitiveExamDTO competitiveExamDTO) {
        log.debug("Request to save CompetitiveExam : {}", competitiveExamDTO);
        CompetitiveExam competitiveExam = competitiveExamMapper.toEntity(competitiveExamDTO);
        competitiveExam = competitiveExamRepository.save(competitiveExam);
        CompetitiveExamDTO result = competitiveExamMapper.toDto(competitiveExam);
        //competitiveExamSearchRepository.save(competitiveExam);
        return result;
    }

    /**
     * Get all the competitiveExams.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompetitiveExamDTO> findAll() {
        log.debug("Request to get all CompetitiveExams");
        return competitiveExamRepository.findAll().stream()
            .map(competitiveExamMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one competitiveExam by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CompetitiveExamDTO> findOne(Long id) {
        log.debug("Request to get CompetitiveExam : {}", id);
        return competitiveExamRepository.findById(id)
            .map(competitiveExamMapper::toDto);
    }

    /**
     * Delete the competitiveExam by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CompetitiveExam : {}", id);
        competitiveExamRepository.deleteById(id);
        //competitiveExamSearchRepository.deleteById(id);
    }

    /**
     * Search for the competitiveExam corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CompetitiveExamDTO> search(String query) {
        log.debug("Request to search CompetitiveExams for query {}", query);
        /*return StreamSupport
            .stream(competitiveExamSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(competitiveExamMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
