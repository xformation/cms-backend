package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.College;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the College entity.
 */
@Repository
public interface CollegeRepository extends JPASearchRepository<College, Long> {

}
