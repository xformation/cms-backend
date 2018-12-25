package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.LegalEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the LegalEntity entity.
 */
public interface LegalEntitySearchRepository extends ElasticsearchRepository<LegalEntity, Long> {
}
