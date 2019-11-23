package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Vehicle;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Vehicle entity.
 */
public interface VehicleSearchRepository extends JPASearchRepository<Vehicle, Long> {
}
