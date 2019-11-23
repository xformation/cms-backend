package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.AcademicHistory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicHistory entity.
 */
public interface AcademicHistorySearchRepository extends JPASearchRepository<AcademicHistory, Long> {
}
