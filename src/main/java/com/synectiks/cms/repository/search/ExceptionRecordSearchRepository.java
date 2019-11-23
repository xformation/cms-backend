package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.ExceptionRecord;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends JPASearchRepository<ExceptionRecord, Long> {
}
