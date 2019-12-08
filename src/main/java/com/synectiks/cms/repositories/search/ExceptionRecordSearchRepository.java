package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.ExceptionRecord;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends JPASearchRepository<ExceptionRecord, Long> {
}
