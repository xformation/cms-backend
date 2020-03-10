package com.synectiks.cms.service.impl;

import com.synectiks.cms.service.AcademicExamSettingService;
import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.repository.AcademicExamSettingRepository;
import com.synectiks.cms.repository.search.AcademicExamSettingSearchRepository;
import com.synectiks.cms.service.dto.AcademicExamSettingDTO;
import com.synectiks.cms.service.mapper.AcademicExamSettingMapper;
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
 * Service Implementation for managing {@link AcademicExamSetting}.
 */
@Service
@Transactional
public class AcademicExamSettingServiceImpl implements AcademicExamSettingService {

    private final Logger log = LoggerFactory.getLogger(AcademicExamSettingServiceImpl.class);

    private final AcademicExamSettingRepository academicExamSettingRepository;

    private final AcademicExamSettingMapper academicExamSettingMapper;

//    private final AcademicExamSettingSearchRepository academicExamSettingSearchRepository;

    public AcademicExamSettingServiceImpl(AcademicExamSettingRepository academicExamSettingRepository, AcademicExamSettingMapper academicExamSettingMapper, AcademicExamSettingSearchRepository academicExamSettingSearchRepository) {
        this.academicExamSettingRepository = academicExamSettingRepository;
        this.academicExamSettingMapper = academicExamSettingMapper;
//        this.academicExamSettingSearchRepository = academicExamSettingSearchRepository;
    }

    /**
     * Save a academicExamSetting.
     *
     * @param academicExamSettingDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public AcademicExamSettingDTO save(AcademicExamSettingDTO academicExamSettingDTO) {
        log.debug("Request to save AcademicExamSetting : {}", academicExamSettingDTO);
        AcademicExamSetting academicExamSetting = academicExamSettingMapper.toEntity(academicExamSettingDTO);
        academicExamSetting = academicExamSettingRepository.save(academicExamSetting);
        AcademicExamSettingDTO result = academicExamSettingMapper.toDto(academicExamSetting);
//        academicExamSettingSearchRepository.save(academicExamSetting);
        return result;
    }

    /**
     * Get all the academicExamSettings.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicExamSettingDTO> findAll() {
        log.debug("Request to get all AcademicExamSettings");
        return academicExamSettingRepository.findAll().stream()
            .map(academicExamSettingMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one academicExamSetting by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicExamSettingDTO> findOne(Long id) {
        log.debug("Request to get AcademicExamSetting : {}", id);
        return academicExamSettingRepository.findById(id)
            .map(academicExamSettingMapper::toDto);
    }

    /**
     * Delete the academicExamSetting by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicExamSetting : {}", id);
        academicExamSettingRepository.deleteById(id);
//        academicExamSettingSearchRepository.deleteById(id);
    }

    /**
     * Search for the academicExamSetting corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<AcademicExamSettingDTO> search(String query) {
        log.debug("Request to search AcademicExamSettings for query {}", query);
//        return StreamSupport
//            .stream(academicExamSettingSearchRepository.search(queryStringQuery(query)).spliterator(), false)
//            .map(academicExamSettingMapper::toDto)
//            .collect(Collectors.toList());
        return null;
    }
}
