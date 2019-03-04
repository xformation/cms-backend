package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Reports;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Reports entity.
 */
public interface ReportsSearchRepository extends ElasticsearchRepository<Reports, Long> {
}
