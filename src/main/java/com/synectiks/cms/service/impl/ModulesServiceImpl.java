package com.synectiks.cms.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.domain.Modules;
import com.synectiks.cms.repository.ModulesRepository;
import com.synectiks.cms.repository.search.ModulesSearchRepository;
import com.synectiks.cms.service.ModulesService;
import com.synectiks.cms.service.dto.ModulesDTO;
import com.synectiks.cms.service.mapper.ModulesMapper;

/**
 * Service Implementation for managing {@link Modules}.
 */
@Service
@Transactional
public class ModulesServiceImpl implements ModulesService {

    private final Logger log = LoggerFactory.getLogger(ModulesServiceImpl.class);

    private final ModulesRepository modulesRepository;

    private final ModulesMapper modulesMapper;

    private final ModulesSearchRepository modulesSearchRepository;

    public ModulesServiceImpl(ModulesRepository modulesRepository, ModulesMapper modulesMapper, ModulesSearchRepository modulesSearchRepository) {
        this.modulesRepository = modulesRepository;
        this.modulesMapper = modulesMapper;
        this.modulesSearchRepository = modulesSearchRepository;
    }

    /**
     * Save a modules.
     *
     * @param modulesDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ModulesDTO save(ModulesDTO modulesDTO) {
        log.debug("Request to save Modules : {}", modulesDTO);
        Modules modules = modulesMapper.toEntity(modulesDTO);
        modules = modulesRepository.save(modules);
        ModulesDTO result = modulesMapper.toDto(modules);
        modulesSearchRepository.save(modules);
        return result;
    }

    /**
     * Get all the modules.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ModulesDTO> findAll() {
        log.debug("Request to get all Modules");
        return modulesRepository.findAll().stream()
            .map(modulesMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one modules by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ModulesDTO> findOne(Long id) {
        log.debug("Request to get Modules : {}", id);
        return modulesRepository.findById(id)
            .map(modulesMapper::toDto);
    }

    /**
     * Delete the modules by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modules : {}", id);
        modulesRepository.deleteById(id);
        modulesSearchRepository.deleteById(id);
    }

    /**
     * Search for the modules corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<ModulesDTO> search(String query) {
        log.debug("Request to search Modules for query {}", query);
        return null;
    }
}
