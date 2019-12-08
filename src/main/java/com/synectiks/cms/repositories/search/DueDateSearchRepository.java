package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.DueDate;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the DueDate entity.
 */
public interface DueDateSearchRepository extends JPASearchRepository<DueDate, Long> {
}
