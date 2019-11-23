package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Reports;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reports entity.
 */
public interface ReportsSearchRepository extends JPASearchRepository<Reports, Long> {
}
