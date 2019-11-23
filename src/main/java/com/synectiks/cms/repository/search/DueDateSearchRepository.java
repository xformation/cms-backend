package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.DueDate;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the DueDate entity.
 */
public interface DueDateSearchRepository extends JPASearchRepository<DueDate, Long> {
}
