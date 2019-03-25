package com.synectiks.cms.service.impl;

import com.synectiks.cms.domain.College;
import com.synectiks.cms.service.BranchService;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.search.BranchSearchRepository;
import com.synectiks.cms.service.dto.BranchDTO;
import com.synectiks.cms.service.mapper.BranchMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing Branch.
 */
@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final Logger log = LoggerFactory.getLogger(BranchServiceImpl.class);

    private final BranchRepository branchRepository;

    private final BranchMapper branchMapper;

    private final BranchSearchRepository branchSearchRepository;

    public BranchServiceImpl(BranchRepository branchRepository, BranchMapper branchMapper, BranchSearchRepository branchSearchRepository) {
        this.branchRepository = branchRepository;
        this.branchMapper = branchMapper;
        this.branchSearchRepository = branchSearchRepository;
    }

    /**
     * Save a branch.
     *
     * @param branchDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BranchDTO save(BranchDTO branchDTO) {
        log.debug("Request to save Branch : {}", branchDTO);
        Branch branch = branchMapper.toEntity(branchDTO);
        branch = branchRepository.save(branch);
        BranchDTO result = branchMapper.toDto(branch);
        branchSearchRepository.save(branch);
        return result;
    }

    /**
     * Get all the branches.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BranchDTO> findAll() {
        log.debug("Request to get all Branches");
        return branchRepository.findAll().stream()
            .map(branchMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one branch by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BranchDTO> findOne(Long id) {
        log.debug("Request to get Branch : {}", id);
        return branchRepository.findById(id)
            .map(branchMapper::toDto);
    }

    /**
     * Delete the branch by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Branch : {}", id);
        branchRepository.deleteById(id);
        branchSearchRepository.deleteById(id);
    }

    /**
     * Search for the branch corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<BranchDTO> search(String query) {
        log.debug("Request to search Branches for query {}", query);
        return StreamSupport
            .stream(branchSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(branchMapper::toDto)
            .collect(Collectors.toList());
    }

    public List<Branch> getAllBranches(String branchName, Long collegeId) {
        Branch branch = new Branch();
        if (branchName != null) {
            branch.setBranchName(branchName);
        }
        if(collegeId != null) {
            College college = new College();
            college.setId(collegeId);
            branch.setCollege(college);
        }
        Example<Branch> example = Example.of(branch);
        List<Branch> list = this.branchRepository.findAll(example);
        return list;
    }
}
