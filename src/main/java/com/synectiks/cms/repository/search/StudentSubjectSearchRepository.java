package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentSubject;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentSubject entity.
 */
public interface StudentSubjectSearchRepository extends ElasticsearchRepository<StudentSubject, Long> {
}
