package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.CurrencyService;
import com.synectiks.cms.domain.Currency;
import com.synectiks.cms.repository.CurrencyRepository;
import com.synectiks.cms.repository.search.CurrencySearchRepository;
import com.synectiks.cms.service.dto.CurrencyDTO;
import com.synectiks.cms.service.mapper.CurrencyMapper;
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
 * Service Implementation for managing Currency.
 */
@Service
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    private final Logger log = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final CurrencyRepository currencyRepository;

    private final CurrencyMapper currencyMapper;

    private final CurrencySearchRepository currencySearchRepository;

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, CurrencyMapper currencyMapper, CurrencySearchRepository currencySearchRepository) {
        this.currencyRepository = currencyRepository;
        this.currencyMapper = currencyMapper;
        this.currencySearchRepository = currencySearchRepository;
    }

    /**
     * Save a currency.
     *
     * @param currencyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CurrencyDTO save(CurrencyDTO currencyDTO) {
        log.debug("Request to save Currency : {}", currencyDTO);

        Currency currency = currencyMapper.toEntity(currencyDTO);
        currency = currencyRepository.save(currency);
        CurrencyDTO result = currencyMapper.toDto(currency);
        currencySearchRepository.save(currency);
        return result;
    }

    /**
     * Get all the currencies.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CurrencyDTO> findAll() {
        log.debug("Request to get all Currencies");
        return currencyRepository.findAll().stream()
            .map(currencyMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one currency by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CurrencyDTO> findOne(Long id) {
        log.debug("Request to get Currency : {}", id);
        return currencyRepository.findById(id)
            .map(currencyMapper::toDto);
    }

    /**
     * Delete the currency by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Currency : {}", id);
        currencyRepository.deleteById(id);
        currencySearchRepository.deleteById(id);
    }

    /**
     * Search for the currency corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CurrencyDTO> search(String query) {
        log.debug("Request to search Currencies for query {}", query);
        return StreamSupport
            .stream(currencySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(currencyMapper::toDto)
            .collect(Collectors.toList());
    }
}
