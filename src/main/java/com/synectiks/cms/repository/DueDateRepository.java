package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.DueDate;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the DueDate entity.
 */
@Repository
public interface DueDateRepository extends JPASearchRepository<DueDate, Long> {

}
