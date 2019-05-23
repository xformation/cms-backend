package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.TypeOfGradingService;
import com.synectiks.cms.domain.TypeOfGrading;
import com.synectiks.cms.repository.TypeOfGradingRepository;
import com.synectiks.cms.repository.search.TypeOfGradingSearchRepository;
import com.synectiks.cms.service.dto.TypeOfGradingDTO;
import com.synectiks.cms.service.mapper.TypeOfGradingMapper;
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
 * Service Implementation for managing TypeOfGrading.
 */
@Service
@Transactional
public class TypeOfGradingServiceImpl implements TypeOfGradingService {

    private final Logger log = LoggerFactory.getLogger(TypeOfGradingServiceImpl.class);

    private final TypeOfGradingRepository typeOfGradingRepository;

    private final TypeOfGradingMapper typeOfGradingMapper;

    private final TypeOfGradingSearchRepository typeOfGradingSearchRepository;

    public TypeOfGradingServiceImpl(TypeOfGradingRepository typeOfGradingRepository, TypeOfGradingMapper typeOfGradingMapper, TypeOfGradingSearchRepository typeOfGradingSearchRepository) {
        this.typeOfGradingRepository = typeOfGradingRepository;
        this.typeOfGradingMapper = typeOfGradingMapper;
        this.typeOfGradingSearchRepository = typeOfGradingSearchRepository;
    }

    /**
     * Save a typeOfGrading.
     *
     * @param typeOfGradingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TypeOfGradingDTO save(TypeOfGradingDTO typeOfGradingDTO) {
        log.debug("Request to save TypeOfGrading : {}", typeOfGradingDTO);

        TypeOfGrading typeOfGrading = typeOfGradingMapper.toEntity(typeOfGradingDTO);
        typeOfGrading = typeOfGradingRepository.save(typeOfGrading);
        TypeOfGradingDTO result = typeOfGradingMapper.toDto(typeOfGrading);
        typeOfGradingSearchRepository.save(typeOfGrading);
        return result;
    }

    /**
     * Get all the typeOfGradings.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeOfGradingDTO> findAll() {
        log.debug("Request to get all TypeOfGradings");
        return typeOfGradingRepository.findAll().stream()
            .map(typeOfGradingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one typeOfGrading by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TypeOfGradingDTO> findOne(Long id) {
        log.debug("Request to get TypeOfGrading : {}", id);
        return typeOfGradingRepository.findById(id)
            .map(typeOfGradingMapper::toDto);
    }

    /**
     * Delete the typeOfGrading by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TypeOfGrading : {}", id);
        typeOfGradingRepository.deleteById(id);
        typeOfGradingSearchRepository.deleteById(id);
    }

    /**
     * Search for the typeOfGrading corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<TypeOfGradingDTO> search(String query) {
        log.debug("Request to search TypeOfGradings for query {}", query);
        return StreamSupport
            .stream(typeOfGradingSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(typeOfGradingMapper::toDto)
            .collect(Collectors.toList());
    }
}
