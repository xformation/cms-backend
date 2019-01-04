package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicSubject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicSubject entity.
 */
public interface AcademicSubjectSearchRepository extends ElasticsearchRepository<AcademicSubject, Long> {
}
