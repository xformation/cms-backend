package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.LegalEntityService;
import com.synectiks.cms.domain.LegalEntity;
import com.synectiks.cms.repository.LegalEntityRepository;
import com.synectiks.cms.repository.search.LegalEntitySearchRepository;
import com.synectiks.cms.service.dto.LegalEntityDTO;
import com.synectiks.cms.service.mapper.LegalEntityMapper;
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
 * Service Implementation for managing LegalEntity.
 */
@Service
@Transactional
public class LegalEntityServiceImpl implements LegalEntityService {

    private final Logger log = LoggerFactory.getLogger(LegalEntityServiceImpl.class);

    private final LegalEntityRepository legalEntityRepository;

    private final LegalEntityMapper legalEntityMapper;

    private final LegalEntitySearchRepository legalEntitySearchRepository;

    public LegalEntityServiceImpl(LegalEntityRepository legalEntityRepository, LegalEntityMapper legalEntityMapper, LegalEntitySearchRepository legalEntitySearchRepository) {
        this.legalEntityRepository = legalEntityRepository;
        this.legalEntityMapper = legalEntityMapper;
        this.legalEntitySearchRepository = legalEntitySearchRepository;
    }

    /**
     * Save a legalEntity.
     *
     * @param legalEntityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public LegalEntityDTO save(LegalEntityDTO legalEntityDTO) {
        log.debug("Request to save LegalEntity : {}", legalEntityDTO);
        LegalEntity legalEntity = legalEntityMapper.toEntity(legalEntityDTO);
        legalEntity = legalEntityRepository.save(legalEntity);
        LegalEntityDTO result = legalEntityMapper.toDto(legalEntity);
        legalEntitySearchRepository.save(legalEntity);
        return result;
    }

    /**
     * Get all the legalEntities.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LegalEntityDTO> findAll() {
        log.debug("Request to get all LegalEntities");
        return legalEntityRepository.findAll().stream()
            .map(legalEntityMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one legalEntity by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LegalEntityDTO> findOne(Long id) {
        log.debug("Request to get LegalEntity : {}", id);
        return legalEntityRepository.findById(id)
            .map(legalEntityMapper::toDto);
    }

    /**
     * Delete the legalEntity by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LegalEntity : {}", id);        legalEntityRepository.deleteById(id);
        legalEntitySearchRepository.deleteById(id);
    }

    /**
     * Search for the legalEntity corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<LegalEntityDTO> search(String query) {
        log.debug("Request to search LegalEntities for query {}", query);
        return StreamSupport
            .stream(legalEntitySearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(legalEntityMapper::toDto)
            .collect(Collectors.toList());
    }
}
