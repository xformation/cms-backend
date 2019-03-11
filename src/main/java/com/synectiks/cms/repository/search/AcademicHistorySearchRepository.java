package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicHistory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicHistory entity.
 */
public interface AcademicHistorySearchRepository extends ElasticsearchRepository<AcademicHistory, Long> {
}
