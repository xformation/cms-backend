package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.FacilityService;
import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.repository.FacilityRepository;
//import com.synectiks.cms.repository.search.FacilitySearchRepository;
import com.synectiks.cms.service.dto.FacilityDTO;
import com.synectiks.cms.service.mapper.FacilityMapper;
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
 * Service Implementation for managing Facility.
 */
@Service
@Transactional
public class FacilityServiceImpl implements FacilityService {

    private final Logger log = LoggerFactory.getLogger(FacilityServiceImpl.class);

    private final FacilityRepository facilityRepository;

    private final FacilityMapper facilityMapper;

    //private final FacilitySearchRepository facilitySearchRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository, FacilityMapper facilityMapper/*, FacilitySearchRepository facilitySearchRepository*/) {
        this.facilityRepository = facilityRepository;
        this.facilityMapper = facilityMapper;
        //this.facilitySearchRepository = facilitySearchRepository;
    }

    /**
     * Save a facility.
     *
     * @param facilityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FacilityDTO save(FacilityDTO facilityDTO) {
        log.debug("Request to save Facility : {}", facilityDTO);
        Facility facility = facilityMapper.toEntity(facilityDTO);
        facility = facilityRepository.save(facility);
        FacilityDTO result = facilityMapper.toDto(facility);
        //facilitySearchRepository.save(facility);
        return result;
    }

    /**
     * Get all the facilities.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FacilityDTO> findAll() {
        log.debug("Request to get all Facilities");
        return facilityRepository.findAll().stream()
            .map(facilityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one facility by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FacilityDTO> findOne(Long id) {
        log.debug("Request to get Facility : {}", id);
        return facilityRepository.findById(id)
            .map(facilityMapper::toDto);
    }

    /**
     * Delete the facility by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Facility : {}", id);
        facilityRepository.deleteById(id);
        //facilitySearchRepository.deleteById(id);
    }

    /**
     * Search for the facility corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FacilityDTO> search(String query) {
        log.debug("Request to search Facilities for query {}", query);
        /*return StreamSupport
            .stream(facilitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(facilityMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
