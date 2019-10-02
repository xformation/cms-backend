package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.MetaLectureService;
import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.repository.MetaLectureRepository;
//import com.synectiks.cms.repository.search.MetaLectureSearchRepository;
import com.synectiks.cms.service.dto.MetaLectureDTO;
import com.synectiks.cms.service.mapper.MetaLectureMapper;
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
 * Service Implementation for managing MetaLecture.
 */
@Service
@Transactional
public class MetaLectureServiceImpl implements MetaLectureService {

    private final Logger log = LoggerFactory.getLogger(MetaLectureServiceImpl.class);

    private final MetaLectureRepository metaLectureRepository;

    private final MetaLectureMapper metaLectureMapper;

    //private final MetaLectureSearchRepository metaLectureSearchRepository;

    public MetaLectureServiceImpl(MetaLectureRepository metaLectureRepository, MetaLectureMapper metaLectureMapper/*, MetaLectureSearchRepository metaLectureSearchRepository*/) {
        this.metaLectureRepository = metaLectureRepository;
        this.metaLectureMapper = metaLectureMapper;
        //this.metaLectureSearchRepository = metaLectureSearchRepository;
    }

    /**
     * Save a metaLecture.
     *
     * @param metaLectureDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MetaLectureDTO save(MetaLectureDTO metaLectureDTO) {
        log.debug("Request to save MetaLecture : {}", metaLectureDTO);

        MetaLecture metaLecture = metaLectureMapper.toEntity(metaLectureDTO);
        metaLecture = metaLectureRepository.save(metaLecture);
        MetaLectureDTO result = metaLectureMapper.toDto(metaLecture);
        //metaLectureSearchRepository.save(metaLecture);
        return result;
    }

    /**
     * Get all the metaLectures.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MetaLectureDTO> findAll() {
        log.debug("Request to get all MetaLectures");
        return metaLectureRepository.findAll().stream()
            .map(metaLectureMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one metaLecture by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MetaLectureDTO> findOne(Long id) {
        log.debug("Request to get MetaLecture : {}", id);
        return metaLectureRepository.findById(id)
            .map(metaLectureMapper::toDto);
    }

    /**
     * Delete the metaLecture by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MetaLecture : {}", id);
        metaLectureRepository.deleteById(id);
        //metaLectureSearchRepository.deleteById(id);
    }

    /**
     * Search for the metaLecture corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<MetaLectureDTO> search(String query) {
        log.debug("Request to search MetaLectures for query {}", query);
        /*return StreamSupport
            .stream(metaLectureSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(metaLectureMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
