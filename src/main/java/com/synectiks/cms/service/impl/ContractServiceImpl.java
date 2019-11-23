package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.ContractService;
import com.synectiks.commons.entities.cms.Contract;
import com.synectiks.cms.repository.ContractRepository;
//import com.synectiks.cms.repository.search.ContractSearchRepository;
import com.synectiks.cms.service.dto.ContractDTO;
import com.synectiks.cms.service.mapper.ContractMapper;
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
 * Service Implementation for managing Contract.
 */
@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final Logger log = LoggerFactory.getLogger(ContractServiceImpl.class);

    private final ContractRepository contractRepository;

    private final ContractMapper contractMapper;

    //private final ContractSearchRepository contractSearchRepository;

    public ContractServiceImpl(ContractRepository contractRepository, ContractMapper contractMapper/*, ContractSearchRepository contractSearchRepository*/) {
        this.contractRepository = contractRepository;
        this.contractMapper = contractMapper;
        //this.contractSearchRepository = contractSearchRepository;
    }

    /**
     * Save a contract.
     *
     * @param contractDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ContractDTO save(ContractDTO contractDTO) {
        log.debug("Request to save Contract : {}", contractDTO);
        Contract contract = contractMapper.toEntity(contractDTO);
        contract = contractRepository.save(contract);
        ContractDTO result = contractMapper.toDto(contract);
        //contractSearchRepository.save(contract);
        return result;
    }

    /**
     * Get all the contracts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> findAll() {
        log.debug("Request to get all Contracts");
        return contractRepository.findAll().stream()
            .map(contractMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one contract by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContractDTO> findOne(Long id) {
        log.debug("Request to get Contract : {}", id);
        return contractRepository.findById(id)
            .map(contractMapper::toDto);
    }

    /**
     * Delete the contract by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Contract : {}", id);
        contractRepository.deleteById(id);
        //contractSearchRepository.deleteById(id);
    }

    /**
     * Search for the contract corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<ContractDTO> search(String query) {
        log.debug("Request to search Contracts for query {}", query);
        /*return StreamSupport
            .stream(contractSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(contractMapper::toDto)
            .collect(Collectors.toList());*/
    	//TODO: Fix it by fetching result from search api
    	return null;
    }
}
