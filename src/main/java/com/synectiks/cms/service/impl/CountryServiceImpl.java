package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.CountryService;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.search.CountrySearchRepository;
import com.synectiks.cms.service.dto.CountryDTO;
import com.synectiks.cms.service.mapper.CountryMapper;
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
 * Service Implementation for managing Country.
 */
@Service
@Transactional
public class CountryServiceImpl implements CountryService {

    private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

    private final CountryRepository countryRepository;

    private final CountryMapper countryMapper;

    private final CountrySearchRepository countrySearchRepository;

    public CountryServiceImpl(CountryRepository countryRepository, CountryMapper countryMapper, CountrySearchRepository countrySearchRepository) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.countrySearchRepository = countrySearchRepository;
    }

    /**
     * Save a country.
     *
     * @param countryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CountryDTO save(CountryDTO countryDTO) {
        log.debug("Request to save Country : {}", countryDTO);
        Country country = countryMapper.toEntity(countryDTO);
        country = countryRepository.save(country);
        CountryDTO result = countryMapper.toDto(country);
        countrySearchRepository.save(country);
        return result;
    }

    /**
     * Get all the countries.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryDTO> findAll() {
        log.debug("Request to get all Countries");
        return countryRepository.findAll().stream()
            .map(countryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one country by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CountryDTO> findOne(Long id) {
        log.debug("Request to get Country : {}", id);
        return countryRepository.findById(id)
            .map(countryMapper::toDto);
    }

    /**
     * Delete the country by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Country : {}", id);        countryRepository.deleteById(id);
        countrySearchRepository.deleteById(id);
    }

    /**
     * Search for the country corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CountryDTO> search(String query) {
        log.debug("Request to search Countries for query {}", query);
        return StreamSupport
            .stream(countrySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(countryMapper::toDto)
            .collect(Collectors.toList());
    }
}
