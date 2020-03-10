package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.TransportRoute;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TransportRoute entity.
 */
public interface TransportRouteSearchRepository extends JPASearchRepository<TransportRoute, Long> {
}
