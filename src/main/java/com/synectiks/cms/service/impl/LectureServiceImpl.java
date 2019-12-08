package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.LectureService;
import com.synectiks.cms.entities.Lecture;
import com.synectiks.cms.repositories.LectureRepository;
//import com.synectiks.cms.commons.repositories.search.LectureSearchRepository;
import com.synectiks.cms.service.dto.LectureDTO;
import com.synectiks.cms.service.mapper.LectureMapper;
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
 * Service Implementation for managing Lecture.
 */
@Service
@Transactional
public class LectureServiceImpl implements LectureService {

    private final Logger log = LoggerFactory.getLogger(LectureServiceImpl.class);

    private final LectureRepository lectureRepository;

    private final LectureMapper lectureMapper;

    //private final LectureSearchRepository lectureSearchRepository;

    public LectureServiceImpl(LectureRepository lectureRepository, LectureMapper lectureMapper/*, LectureSearchRepository lectureSearchRepository*/) {
        this.lectureRepository = lectureRepository;
        this.lectureMapper = lectureMapper;
        //this.lectureSearchRepository = lectureSearchRepository;
    }

    /**
     * Save a lecture.
     *
     * @param lectureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LectureDTO save(LectureDTO lectureDTO) {
        log.debug("Request to save Lecture : {}", lectureDTO);
        Lecture lecture = lectureMapper.toEntity(lectureDTO);
        lecture = lectureRepository.save(lecture);
        LectureDTO result = lectureMapper.toDto(lecture);
        //lectureSearchRepository.save(lecture);
        return result;
    }

    /**
     * Get all the lectures.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LectureDTO> findAll() {
        log.debug("Request to get all Lectures");
        return lectureRepository.findAll().stream()
            .map(lectureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one lecture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LectureDTO> findOne(Long id) {
        log.debug("Request to get Lecture : {}", id);
        return lectureRepository.findById(id)
            .map(lectureMapper::toDto);
    }

    /**
     * Delete the lecture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Lecture : {}", id);
        lectureRepository.deleteById(id);
        //lectureSearchRepository.deleteById(id);
    }

    /**
     * Search for the lecture corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LectureDTO> search(String query) {
        log.debug("Request to search Lectures for query {}", query);
        /*return StreamSupport
            .stream(lectureSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(lectureMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
