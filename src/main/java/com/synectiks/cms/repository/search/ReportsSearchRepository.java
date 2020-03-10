package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Reports;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reports entity.
 */
public interface ReportsSearchRepository extends JPASearchRepository<Reports, Long> {
}
