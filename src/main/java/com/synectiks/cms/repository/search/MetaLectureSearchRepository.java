package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.MetaLecture;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MetaLecture entity.
 */
public interface MetaLectureSearchRepository extends ElasticsearchRepository<MetaLecture, Long> {
}
