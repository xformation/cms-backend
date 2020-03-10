package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.FeeCategoryService;
import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.repository.FeeCategoryRepository;
import com.synectiks.cms.repository.search.FeeCategorySearchRepository;
import com.synectiks.cms.service.dto.FeeCategoryDTO;
import com.synectiks.cms.service.mapper.FeeCategoryMapper;
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
 * Service Implementation for managing FeeCategory.
 */
@Service
@Transactional
public class FeeCategoryServiceImpl implements FeeCategoryService {

    private final Logger log = LoggerFactory.getLogger(FeeCategoryServiceImpl.class);

    private final FeeCategoryRepository feeCategoryRepository;

    private final FeeCategoryMapper feeCategoryMapper;

//    private final FeeCategorySearchRepository feeCategorySearchRepository;

    public FeeCategoryServiceImpl(FeeCategoryRepository feeCategoryRepository, FeeCategoryMapper feeCategoryMapper, FeeCategorySearchRepository feeCategorySearchRepository) {
        this.feeCategoryRepository = feeCategoryRepository;
        this.feeCategoryMapper = feeCategoryMapper;
//        this.feeCategorySearchRepository = feeCategorySearchRepository;
    }

    /**
     * Save a feeCategory.
     *
     * @param feeCategoryDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FeeCategoryDTO save(FeeCategoryDTO feeCategoryDTO) {
        log.debug("Request to save FeeCategory : {}", feeCategoryDTO);
        FeeCategory feeCategory = feeCategoryMapper.toEntity(feeCategoryDTO);
        feeCategory = feeCategoryRepository.save(feeCategory);
        FeeCategoryDTO result = feeCategoryMapper.toDto(feeCategory);
//        feeCategorySearchRepository.save(feeCategory);
        return result;
    }

    /**
     * Get all the feeCategories.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FeeCategoryDTO> findAll() {
        log.debug("Request to get all FeeCategories");
        return feeCategoryRepository.findAll().stream()
            .map(feeCategoryMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one feeCategory by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FeeCategoryDTO> findOne(Long id) {
        log.debug("Request to get FeeCategory : {}", id);
        return feeCategoryRepository.findById(id)
            .map(feeCategoryMapper::toDto);
    }

    /**
     * Delete the feeCategory by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FeeCategory : {}", id);
        feeCategoryRepository.deleteById(id);
//        feeCategorySearchRepository.deleteById(id);
    }

    /**
     * Search for the feeCategory corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FeeCategoryDTO> search(String query) {
        log.debug("Request to search FeeCategories for query {}", query);
//        return StreamSupport
//            .stream(feeCategorySearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .map(feeCategoryMapper::toDto)
//            .collect(Collectors.toList());
        return null;
    }
}
