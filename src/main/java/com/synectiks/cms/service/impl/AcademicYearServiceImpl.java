package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AcademicYearService;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.search.AcademicYearSearchRepository;
import com.synectiks.cms.service.dto.AcademicYearDTO;
import com.synectiks.cms.service.mapper.AcademicYearMapper;
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
 * Service Implementation for managing AcademicYear.
 */
@Service
@Transactional
public class AcademicYearServiceImpl implements AcademicYearService {

    private final Logger log = LoggerFactory.getLogger(AcademicYearServiceImpl.class);

    private final AcademicYearRepository academicYearRepository;

    private final AcademicYearMapper academicYearMapper;

//    private final AcademicYearSearchRepository academicYearSearchRepository;

    public AcademicYearServiceImpl(AcademicYearRepository academicYearRepository, AcademicYearMapper academicYearMapper, AcademicYearSearchRepository academicYearSearchRepository) {
        this.academicYearRepository = academicYearRepository;
        this.academicYearMapper = academicYearMapper;
//        this.academicYearSearchRepository = academicYearSearchRepository;
    }

    /**
     * Save a academicYear.
     *
     * @param academicYearDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AcademicYearDTO save(AcademicYearDTO academicYearDTO) {
        log.debug("Request to save AcademicYear : {}", academicYearDTO);

        AcademicYear academicYear = academicYearMapper.toEntity(academicYearDTO);
        academicYear = academicYearRepository.save(academicYear);
        AcademicYearDTO result = academicYearMapper.toDto(academicYear);
//        academicYearSearchRepository.save(academicYear);
        return result;
    }

    /**
     * Get all the academicYears.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicYearDTO> findAll() {
        log.debug("Request to get all AcademicYears");
        return academicYearRepository.findAll().stream()
            .map(academicYearMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one academicYear by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicYearDTO> findOne(Long id) {
        log.debug("Request to get AcademicYear : {}", id);
        return academicYearRepository.findById(id)
            .map(academicYearMapper::toDto);
    }

    /**
     * Delete the academicYear by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicYear : {}", id);
        academicYearRepository.deleteById(id);
//        academicYearSearchRepository.deleteById(id);
    }

    /**
     * Search for the academicYear corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicYearDTO> search(String query) {
        log.debug("Request to search AcademicYears for query {}", query);
//        return StreamSupport
//            .stream(academicYearSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .map(academicYearMapper::toDto)
//            .collect(Collectors.toList());
        return null;
    }
}
