package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.DocumentsService;
import com.synectiks.cms.domain.Documents;
import com.synectiks.cms.repository.DocumentsRepository;
import com.synectiks.cms.repository.search.DocumentsSearchRepository;
import com.synectiks.cms.service.dto.DocumentsDTO;
import com.synectiks.cms.service.mapper.DocumentsMapper;
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
 * Service Implementation for managing Documents.
 */
@Service
@Transactional
public class DocumentsServiceImpl implements DocumentsService {

    private final Logger log = LoggerFactory.getLogger(DocumentsServiceImpl.class);

    private final DocumentsRepository documentsRepository;

    private final DocumentsMapper documentsMapper;

    private final DocumentsSearchRepository documentsSearchRepository;

    public DocumentsServiceImpl(DocumentsRepository documentsRepository, DocumentsMapper documentsMapper, DocumentsSearchRepository documentsSearchRepository) {
        this.documentsRepository = documentsRepository;
        this.documentsMapper = documentsMapper;
        this.documentsSearchRepository = documentsSearchRepository;
    }

    /**
     * Save a documents.csv.
     *
     * @param documentsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DocumentsDTO save(DocumentsDTO documentsDTO) {
        log.debug("Request to save Documents : {}", documentsDTO);
        Documents documents = documentsMapper.toEntity(documentsDTO);
        documents = documentsRepository.save(documents);
        DocumentsDTO result = documentsMapper.toDto(documents);
        documentsSearchRepository.save(documents);
        return result;
    }

    /**
     * Get all the documents.csv.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentsDTO> findAll() {
        log.debug("Request to get all Documents");
        return documentsRepository.findAll().stream()
            .map(documentsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one documents.csv by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentsDTO> findOne(Long id) {
        log.debug("Request to get Documents : {}", id);
        return documentsRepository.findById(id)
            .map(documentsMapper::toDto);
    }

    /**
     * Delete the documents.csv by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Documents : {}", id);
        documentsRepository.deleteById(id);
        documentsSearchRepository.deleteById(id);
    }

    /**
     * Search for the documents.csv corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<DocumentsDTO> search(String query) {
        log.debug("Request to search Documents for query {}", query);
        return StreamSupport
            .stream(documentsSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(documentsMapper::toDto)
            .collect(Collectors.toList());
    }
}
