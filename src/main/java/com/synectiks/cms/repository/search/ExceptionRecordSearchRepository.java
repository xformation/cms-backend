package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.ExceptionRecord;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends JPASearchRepository<ExceptionRecord, Long> {
}
