package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.SignatoryLink;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the SignatoryLink entity.
 */
public interface SignatoryLinkSearchRepository extends ElasticsearchRepository<SignatoryLink, Long> {
}
