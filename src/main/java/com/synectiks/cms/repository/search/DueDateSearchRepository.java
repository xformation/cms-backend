package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.DueDate;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DueDate entity.
 */
public interface DueDateSearchRepository extends JPASearchRepository<DueDate, Long> {
}
