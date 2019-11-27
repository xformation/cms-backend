package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.VehicleService;
import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.repository.VehicleRepository;
//import com.synectiks.cms.repository.search.VehicleSearchRepository;
import com.synectiks.cms.service.dto.VehicleDTO;
import com.synectiks.cms.service.mapper.VehicleMapper;
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
 * Service Implementation for managing Vehicle.
 */
@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final Logger log = LoggerFactory.getLogger(VehicleServiceImpl.class);

    private final VehicleRepository vehicleRepository;

    private final VehicleMapper vehicleMapper;

    //private final VehicleSearchRepository vehicleSearchRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper/*, VehicleSearchRepository vehicleSearchRepository*/) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        //this.vehicleSearchRepository = vehicleSearchRepository;
    }

    /**
     * Save a vehicle.
     *
     * @param vehicleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public VehicleDTO save(VehicleDTO vehicleDTO) {
        log.debug("Request to save Vehicle : {}", vehicleDTO);

        Vehicle vehicle = vehicleMapper.toEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        VehicleDTO result = vehicleMapper.toDto(vehicle);
        //vehicleSearchRepository.save(vehicle);
        return result;
    }

    /**
     * Get all the vehicles.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> findAll() {
        log.debug("Request to get all Vehicles");
        return vehicleRepository.findAll().stream()
            .map(vehicleMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one vehicle by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<VehicleDTO> findOne(Long id) {
        log.debug("Request to get Vehicle : {}", id);
        return vehicleRepository.findById(id)
            .map(vehicleMapper::toDto);
    }

    /**
     * Delete the vehicle by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Vehicle : {}", id);
        vehicleRepository.deleteById(id);
        ///vehicleSearchRepository.deleteById(id);
    }

    /**
     * Search for the vehicle corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<VehicleDTO> search(String query) {
        log.debug("Request to search Vehicles for query {}", query);
        /*return StreamSupport
            .stream(vehicleSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(vehicleMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
