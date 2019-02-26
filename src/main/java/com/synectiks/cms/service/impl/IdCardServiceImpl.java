package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.IdCardService;
import com.synectiks.cms.domain.IdCard;
import com.synectiks.cms.repository.IdCardRepository;
import com.synectiks.cms.repository.search.IdCardSearchRepository;
import com.synectiks.cms.service.dto.IdCardDTO;
import com.synectiks.cms.service.mapper.IdCardMapper;
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
 * Service Implementation for managing IdCard.
 */
@Service
@Transactional
public class IdCardServiceImpl implements IdCardService {

    private final Logger log = LoggerFactory.getLogger(IdCardServiceImpl.class);

    private final IdCardRepository idCardRepository;

    private final IdCardMapper idCardMapper;

    private final IdCardSearchRepository idCardSearchRepository;

    public IdCardServiceImpl(IdCardRepository idCardRepository, IdCardMapper idCardMapper, IdCardSearchRepository idCardSearchRepository) {
        this.idCardRepository = idCardRepository;
        this.idCardMapper = idCardMapper;
        this.idCardSearchRepository = idCardSearchRepository;
    }

    /**
     * Save a idCard.
     *
     * @param idCardDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public IdCardDTO save(IdCardDTO idCardDTO) {
        log.debug("Request to save IdCard : {}", idCardDTO);

        IdCard idCard = idCardMapper.toEntity(idCardDTO);
        idCard = idCardRepository.save(idCard);
        IdCardDTO result = idCardMapper.toDto(idCard);
        idCardSearchRepository.save(idCard);
        return result;
    }

    /**
     * Get all the idCards.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<IdCardDTO> findAll() {
        log.debug("Request to get all IdCards");
        return idCardRepository.findAll().stream()
            .map(idCardMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one idCard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<IdCardDTO> findOne(Long id) {
        log.debug("Request to get IdCard : {}", id);
        return idCardRepository.findById(id)
            .map(idCardMapper::toDto);
    }

    /**
     * Delete the idCard by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete IdCard : {}", id);
        idCardRepository.deleteById(id);
        idCardSearchRepository.deleteById(id);
    }

    /**
     * Search for the idCard corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<IdCardDTO> search(String query) {
        log.debug("Request to search IdCards for query {}", query);
        return StreamSupport
            .stream(idCardSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(idCardMapper::toDto)
            .collect(Collectors.toList());
    }
}
