package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AcademicHistory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicHistory entity.
 */
public interface AcademicHistorySearchRepository extends JPASearchRepository<AcademicHistory, Long> {
}
