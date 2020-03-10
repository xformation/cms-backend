package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Lecture entity.
 */
public interface LectureSearchRepository extends JPASearchRepository<Lecture, Long> {
}
