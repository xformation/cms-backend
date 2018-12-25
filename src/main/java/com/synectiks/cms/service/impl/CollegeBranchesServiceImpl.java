package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.CollegeBranchesService;
import com.synectiks.cms.domain.CollegeBranches;
import com.synectiks.cms.repository.CollegeBranchesRepository;
import com.synectiks.cms.repository.search.CollegeBranchesSearchRepository;
import com.synectiks.cms.service.dto.CollegeBranchesDTO;
import com.synectiks.cms.service.mapper.CollegeBranchesMapper;
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
 * Service Implementation for managing CollegeBranches.
 */
@Service
@Transactional
public class CollegeBranchesServiceImpl implements CollegeBranchesService {

    private final Logger log = LoggerFactory.getLogger(CollegeBranchesServiceImpl.class);

    private final CollegeBranchesRepository collegeBranchesRepository;

    private final CollegeBranchesMapper collegeBranchesMapper;

    private final CollegeBranchesSearchRepository collegeBranchesSearchRepository;

    public CollegeBranchesServiceImpl(CollegeBranchesRepository collegeBranchesRepository, CollegeBranchesMapper collegeBranchesMapper, CollegeBranchesSearchRepository collegeBranchesSearchRepository) {
        this.collegeBranchesRepository = collegeBranchesRepository;
        this.collegeBranchesMapper = collegeBranchesMapper;
        this.collegeBranchesSearchRepository = collegeBranchesSearchRepository;
    }

    /**
     * Save a collegeBranches.
     *
     * @param collegeBranchesDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CollegeBranchesDTO save(CollegeBranchesDTO collegeBranchesDTO) {
        log.debug("Request to save CollegeBranches : {}", collegeBranchesDTO);
        CollegeBranches collegeBranches = collegeBranchesMapper.toEntity(collegeBranchesDTO);
        collegeBranches = collegeBranchesRepository.save(collegeBranches);
        CollegeBranchesDTO result = collegeBranchesMapper.toDto(collegeBranches);
        collegeBranchesSearchRepository.save(collegeBranches);
        return result;
    }

    /**
     * Get all the collegeBranches.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CollegeBranchesDTO> findAll() {
        log.debug("Request to get all CollegeBranches");
        return collegeBranchesRepository.findAll().stream()
            .map(collegeBranchesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one collegeBranches by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CollegeBranchesDTO> findOne(Long id) {
        log.debug("Request to get CollegeBranches : {}", id);
        return collegeBranchesRepository.findById(id)
            .map(collegeBranchesMapper::toDto);
    }

    /**
     * Delete the collegeBranches by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CollegeBranches : {}", id);
        collegeBranchesRepository.deleteById(id);
        collegeBranchesSearchRepository.deleteById(id);
    }

    /**
     * Search for the collegeBranches corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CollegeBranchesDTO> search(String query) {
        log.debug("Request to search CollegeBranches for query {}", query);
        return StreamSupport
            .stream(collegeBranchesSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(collegeBranchesMapper::toDto)
            .collect(Collectors.toList());
    }
}
