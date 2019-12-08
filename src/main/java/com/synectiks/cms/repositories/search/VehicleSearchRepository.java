package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Vehicle;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Vehicle entity.
 */
public interface VehicleSearchRepository extends JPASearchRepository<Vehicle, Long> {
}
