package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Section;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Section entity.
 */
public interface SectionSearchRepository extends JPASearchRepository<Section, Long> {
}
