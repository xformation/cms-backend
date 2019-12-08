package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Reports;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reports entity.
 */
public interface ReportsSearchRepository extends JPASearchRepository<Reports, Long> {
}
