package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentYear;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentYear entity.
 */
public interface StudentYearSearchRepository extends ElasticsearchRepository<StudentYear, Long> {
}
