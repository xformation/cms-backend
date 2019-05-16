package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.BankAccountsService;
import com.synectiks.cms.domain.BankAccounts;
import com.synectiks.cms.repository.BankAccountsRepository;
import com.synectiks.cms.repository.search.BankAccountsSearchRepository;
import com.synectiks.cms.service.dto.BankAccountsDTO;
import com.synectiks.cms.service.mapper.BankAccountsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing BankAccounts.
 */
@Service
@Transactional
public class BankAccountsServiceImpl implements BankAccountsService {

    private final Logger log = LoggerFactory.getLogger(BankAccountsServiceImpl.class);

    private final BankAccountsRepository bankAccountsRepository;

    private final BankAccountsMapper bankAccountsMapper;

    private final BankAccountsSearchRepository bankAccountsSearchRepository;

    public BankAccountsServiceImpl(BankAccountsRepository bankAccountsRepository, BankAccountsMapper bankAccountsMapper, BankAccountsSearchRepository bankAccountsSearchRepository) {
        this.bankAccountsRepository = bankAccountsRepository;
        this.bankAccountsMapper = bankAccountsMapper;
        this.bankAccountsSearchRepository = bankAccountsSearchRepository;
    }

    /**
     * Save a bankAccounts.
     *
     * @param bankAccountsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BankAccountsDTO save(BankAccountsDTO bankAccountsDTO) {
        log.debug("Request to save BankAccounts : {}", bankAccountsDTO);

        BankAccounts bankAccounts = bankAccountsMapper.toEntity(bankAccountsDTO);
        bankAccounts = bankAccountsRepository.save(bankAccounts);
        BankAccountsDTO result = bankAccountsMapper.toDto(bankAccounts);
        bankAccountsSearchRepository.save(bankAccounts);
        return result;
    }

    /**
     * Get all the bankAccounts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAccountsDTO> findAll() {
        log.debug("Request to get all BankAccounts");
        return bankAccountsRepository.findAll().stream()
            .map(bankAccountsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one bankAccounts by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BankAccountsDTO> findOne(Long id) {
        log.debug("Request to get BankAccounts : {}", id);
        return bankAccountsRepository.findById(id)
            .map(bankAccountsMapper::toDto);
    }

    /**
     * Delete the bankAccounts by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BankAccounts : {}", id);
        bankAccountsRepository.deleteById(id);
        bankAccountsSearchRepository.deleteById(id);
    }

    /**
     * Search for the bankAccounts corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAccountsDTO> search(String query) {
        log.debug("Request to search BankAccounts for query {}", query);
        return StreamSupport
            .stream(bankAccountsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(bankAccountsMapper::toDto)
            .collect(Collectors.toList());
    }
}
