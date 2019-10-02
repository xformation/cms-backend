package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.TransportRouteService;
import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.repository.TransportRouteRepository;
//import com.synectiks.cms.repository.search.TransportRouteSearchRepository;
import com.synectiks.cms.service.dto.TransportRouteDTO;
import com.synectiks.cms.service.mapper.TransportRouteMapper;
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
 * Service Implementation for managing TransportRoute.
 */
@Service
@Transactional
public class TransportRouteServiceImpl implements TransportRouteService {

    private final Logger log = LoggerFactory.getLogger(TransportRouteServiceImpl.class);

    private final TransportRouteRepository transportRouteRepository;

    private final TransportRouteMapper transportRouteMapper;

    //private final TransportRouteSearchRepository transportRouteSearchRepository;

    public TransportRouteServiceImpl(TransportRouteRepository transportRouteRepository, TransportRouteMapper transportRouteMapper/*, TransportRouteSearchRepository transportRouteSearchRepository*/) {
        this.transportRouteRepository = transportRouteRepository;
        this.transportRouteMapper = transportRouteMapper;
        //this.transportRouteSearchRepository = transportRouteSearchRepository;
    }

    /**
     * Save a transportRoute.
     *
     * @param transportRouteDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TransportRouteDTO save(TransportRouteDTO transportRouteDTO) {
        log.debug("Request to save TransportRoute : {}", transportRouteDTO);
        TransportRoute transportRoute = transportRouteMapper.toEntity(transportRouteDTO);
        transportRoute = transportRouteRepository.save(transportRoute);
        TransportRouteDTO result = transportRouteMapper.toDto(transportRoute);
        //transportRouteSearchRepository.save(transportRoute);
        return result;
    }

    /**
     * Get all the transportRoutes.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportRouteDTO> findAll() {
        log.debug("Request to get all TransportRoutes");
        return transportRouteRepository.findAll().stream()
            .map(transportRouteMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one transportRoute by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TransportRouteDTO> findOne(Long id) {
        log.debug("Request to get TransportRoute : {}", id);
        return transportRouteRepository.findById(id)
            .map(transportRouteMapper::toDto);
    }

    /**
     * Delete the transportRoute by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TransportRoute : {}", id);
        transportRouteRepository.deleteById(id);
        //transportRouteSearchRepository.deleteById(id);
    }

    /**
     * Search for the transportRoute corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TransportRouteDTO> search(String query) {
        log.debug("Request to search TransportRoutes for query {}", query);
        /*return StreamSupport
            .stream(transportRouteSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(transportRouteMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
