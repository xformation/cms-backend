package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.TransportRoute;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the TransportRoute entity.
 */
@Repository
public interface TransportRouteRepository
		extends JPASearchRepository<TransportRoute, Long> {

}
