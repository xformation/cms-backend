package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.CourseOfferService;
import com.synectiks.cms.domain.CourseOffer;
import com.synectiks.cms.repository.CourseOfferRepository;
import com.synectiks.cms.repository.search.CourseOfferSearchRepository;
import com.synectiks.cms.service.dto.CourseOfferDTO;
import com.synectiks.cms.service.mapper.CourseOfferMapper;
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
 * Service Implementation for managing CourseOffer.
 */
@Service
@Transactional
public class CourseOfferServiceImpl implements CourseOfferService {

    private final Logger log = LoggerFactory.getLogger(CourseOfferServiceImpl.class);

    private final CourseOfferRepository courseOfferRepository;

    private final CourseOfferMapper courseOfferMapper;

    private final CourseOfferSearchRepository courseOfferSearchRepository;

    public CourseOfferServiceImpl(CourseOfferRepository courseOfferRepository, CourseOfferMapper courseOfferMapper, CourseOfferSearchRepository courseOfferSearchRepository) {
        this.courseOfferRepository = courseOfferRepository;
        this.courseOfferMapper = courseOfferMapper;
        this.courseOfferSearchRepository = courseOfferSearchRepository;
    }

    /**
     * Save a courseOffer.
     *
     * @param courseOfferDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseOfferDTO save(CourseOfferDTO courseOfferDTO) {
        log.debug("Request to save CourseOffer : {}", courseOfferDTO);
        CourseOffer courseOffer = courseOfferMapper.toEntity(courseOfferDTO);
        courseOffer = courseOfferRepository.save(courseOffer);
        CourseOfferDTO result = courseOfferMapper.toDto(courseOffer);
        courseOfferSearchRepository.save(courseOffer);
        return result;
    }

    /**
     * Get all the courseOffers.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseOfferDTO> findAll() {
        log.debug("Request to get all CourseOffers");
        return courseOfferRepository.findAll().stream()
            .map(courseOfferMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one courseOffer by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseOfferDTO> findOne(Long id) {
        log.debug("Request to get CourseOffer : {}", id);
        return courseOfferRepository.findById(id)
            .map(courseOfferMapper::toDto);
    }

    /**
     * Delete the courseOffer by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseOffer : {}", id);
        courseOfferRepository.deleteById(id);
        courseOfferSearchRepository.deleteById(id);
    }

    /**
     * Search for the courseOffer corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CourseOfferDTO> search(String query) {
        log.debug("Request to search CourseOffers for query {}", query);
        return StreamSupport
            .stream(courseOfferSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(courseOfferMapper::toDto)
            .collect(Collectors.toList());
    }
}
