package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.DueDate;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the DueDate entity.
 */
@Repository
public interface DueDateRepository extends JPASearchRepository<DueDate, Long> {

}
