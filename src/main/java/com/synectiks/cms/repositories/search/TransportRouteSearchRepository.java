package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.TransportRoute;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the TransportRoute entity.
 */
public interface TransportRouteSearchRepository extends JPASearchRepository<TransportRoute, Long> {
}
