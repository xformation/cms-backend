package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AttendanceMasterService;
import com.synectiks.cms.entities.AttendanceMaster;
import com.synectiks.cms.repositories.AttendanceMasterRepository;
//import com.synectiks.cms.commons.repositories.search.AttendanceMasterSearchRepository;
import com.synectiks.cms.service.dto.AttendanceMasterDTO;
import com.synectiks.cms.service.mapper.AttendanceMasterMapper;
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
 * Service Implementation for managing AttendanceMaster.
 */
@Service
@Transactional
public class AttendanceMasterServiceImpl implements AttendanceMasterService {

    private final Logger log = LoggerFactory.getLogger(AttendanceMasterServiceImpl.class);

    private final AttendanceMasterRepository attendanceMasterRepository;

    private final AttendanceMasterMapper attendanceMasterMapper;

    //private final AttendanceMasterSearchRepository attendanceMasterSearchRepository;

    public AttendanceMasterServiceImpl(AttendanceMasterRepository attendanceMasterRepository, AttendanceMasterMapper attendanceMasterMapper/*, AttendanceMasterSearchRepository attendanceMasterSearchRepository*/) {
        this.attendanceMasterRepository = attendanceMasterRepository;
        this.attendanceMasterMapper = attendanceMasterMapper;
        //this.attendanceMasterSearchRepository = attendanceMasterSearchRepository;
    }

    /**
     * Save a attendanceMaster.
     *
     * @param attendanceMasterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AttendanceMasterDTO save(AttendanceMasterDTO attendanceMasterDTO) {
        log.debug("Request to save AttendanceMaster : {}", attendanceMasterDTO);
        AttendanceMaster attendanceMaster = attendanceMasterMapper.toEntity(attendanceMasterDTO);
        attendanceMaster = attendanceMasterRepository.save(attendanceMaster);
        AttendanceMasterDTO result = attendanceMasterMapper.toDto(attendanceMaster);
        //attendanceMasterSearchRepository.save(attendanceMaster);
        return result;
    }

    /**
     * Get all the attendanceMasters.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttendanceMasterDTO> findAll() {
        log.debug("Request to get all AttendanceMasters");
        return attendanceMasterRepository.findAll().stream()
            .map(attendanceMasterMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one attendanceMaster by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AttendanceMasterDTO> findOne(Long id) {
        log.debug("Request to get AttendanceMaster : {}", id);
        return attendanceMasterRepository.findById(id)
            .map(attendanceMasterMapper::toDto);
    }

    /**
     * Delete the attendanceMaster by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AttendanceMaster : {}", id);
        attendanceMasterRepository.deleteById(id);
        //attendanceMasterSearchRepository.deleteById(id);
    }

    /**
     * Search for the attendanceMaster corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AttendanceMasterDTO> search(String query) {
        log.debug("Request to search AttendanceMasters for query {}", query);
        /*return StreamSupport
            .stream(attendanceMasterSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(attendanceMasterMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
