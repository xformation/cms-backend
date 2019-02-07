package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.SignatoryLinkService;
import com.synectiks.cms.domain.SignatoryLink;
import com.synectiks.cms.repository.SignatoryLinkRepository;
import com.synectiks.cms.repository.search.SignatoryLinkSearchRepository;
import com.synectiks.cms.service.dto.SignatoryLinkDTO;
import com.synectiks.cms.service.mapper.SignatoryLinkMapper;
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
 * Service Implementation for managing SignatoryLink.
 */
@Service
@Transactional
public class SignatoryLinkServiceImpl implements SignatoryLinkService {

    private final Logger log = LoggerFactory.getLogger(SignatoryLinkServiceImpl.class);

    private final SignatoryLinkRepository signatoryLinkRepository;

    private final SignatoryLinkMapper signatoryLinkMapper;

    private final SignatoryLinkSearchRepository signatoryLinkSearchRepository;

    public SignatoryLinkServiceImpl(SignatoryLinkRepository signatoryLinkRepository, SignatoryLinkMapper signatoryLinkMapper, SignatoryLinkSearchRepository signatoryLinkSearchRepository) {
        this.signatoryLinkRepository = signatoryLinkRepository;
        this.signatoryLinkMapper = signatoryLinkMapper;
        this.signatoryLinkSearchRepository = signatoryLinkSearchRepository;
    }

    /**
     * Save a signatoryLink.
     *
     * @param signatoryLinkDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SignatoryLinkDTO save(SignatoryLinkDTO signatoryLinkDTO) {
        log.debug("Request to save SignatoryLink : {}", signatoryLinkDTO);

        SignatoryLink signatoryLink = signatoryLinkMapper.toEntity(signatoryLinkDTO);
        signatoryLink = signatoryLinkRepository.save(signatoryLink);
        SignatoryLinkDTO result = signatoryLinkMapper.toDto(signatoryLink);
        signatoryLinkSearchRepository.save(signatoryLink);
        return result;
    }

    /**
     * Get all the signatoryLinks.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SignatoryLinkDTO> findAll() {
        log.debug("Request to get all SignatoryLinks");
        return signatoryLinkRepository.findAll().stream()
            .map(signatoryLinkMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one signatoryLink by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<SignatoryLinkDTO> findOne(Long id) {
        log.debug("Request to get SignatoryLink : {}", id);
        return signatoryLinkRepository.findById(id)
            .map(signatoryLinkMapper::toDto);
    }

    /**
     * Delete the signatoryLink by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete SignatoryLink : {}", id);
        signatoryLinkRepository.deleteById(id);
        signatoryLinkSearchRepository.deleteById(id);
    }

    /**
     * Search for the signatoryLink corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SignatoryLinkDTO> search(String query) {
        log.debug("Request to search SignatoryLinks for query {}", query);
        return StreamSupport
            .stream(signatoryLinkSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(signatoryLinkMapper::toDto)
            .collect(Collectors.toList());
    }
}
