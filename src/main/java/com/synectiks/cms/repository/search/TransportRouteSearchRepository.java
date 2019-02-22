package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.TransportRoute;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TransportRoute entity.
 */
public interface TransportRouteSearchRepository extends ElasticsearchRepository<TransportRoute, Long> {
}
