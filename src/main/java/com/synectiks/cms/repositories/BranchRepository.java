package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Branch entity.
 */
@Repository
public interface BranchRepository extends JPASearchRepository<Branch, Long> {

}
