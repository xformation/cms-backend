package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Semester;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Semester entity.
 */
public interface SemesterSearchRepository extends ElasticsearchRepository<Semester, Long> {
}
