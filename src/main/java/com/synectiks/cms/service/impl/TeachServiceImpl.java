package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.TeachService;
import com.synectiks.commons.entities.cms.Teach;
import com.synectiks.cms.repository.TeachRepository;
//import com.synectiks.cms.repository.search.TeachSearchRepository;
import com.synectiks.cms.service.dto.TeachDTO;
import com.synectiks.cms.service.mapper.TeachMapper;
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
 * Service Implementation for managing Teach.
 */
@Service
@Transactional
public class TeachServiceImpl implements TeachService {

    private final Logger log = LoggerFactory.getLogger(TeachServiceImpl.class);

    private final TeachRepository teachRepository;

    private final TeachMapper teachMapper;

    //private final TeachSearchRepository teachSearchRepository;

    public TeachServiceImpl(TeachRepository teachRepository, TeachMapper teachMapper/*, TeachSearchRepository teachSearchRepository*/) {
        this.teachRepository = teachRepository;
        this.teachMapper = teachMapper;
        //this.teachSearchRepository = teachSearchRepository;
    }

    /**
     * Save a teach.
     *
     * @param teachDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachDTO save(TeachDTO teachDTO) {
        log.debug("Request to save Teach : {}", teachDTO);
        Teach teach = teachMapper.toEntity(teachDTO);
        teach = teachRepository.save(teach);
        TeachDTO result = teachMapper.toDto(teach);
        //teachSearchRepository.save(teach);
        return result;
    }

    /**
     * Get all the teaches.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TeachDTO> findAll() {
        log.debug("Request to get all Teaches");
        return teachRepository.findAll().stream()
            .map(teachMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one teach by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachDTO> findOne(Long id) {
        log.debug("Request to get Teach : {}", id);
        return teachRepository.findById(id)
            .map(teachMapper::toDto);
    }

    /**
     * Delete the teach by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teach : {}", id);
        teachRepository.deleteById(id);
        //teachSearchRepository.deleteById(id);
    }

    /**
     * Search for the teach corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TeachDTO> search(String query) {
        log.debug("Request to search Teaches for query {}", query);
        /*return StreamSupport
            .stream(teachSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(teachMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
