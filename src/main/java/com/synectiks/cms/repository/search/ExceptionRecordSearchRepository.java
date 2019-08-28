package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.ExceptionRecord;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ExceptionRecord} entity.
 */
public interface ExceptionRecordSearchRepository extends ElasticsearchRepository<ExceptionRecord, Long> {
}
