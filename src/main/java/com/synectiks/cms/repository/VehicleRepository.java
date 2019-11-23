package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Vehicle;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Vehicle entity.
 */
@Repository
public interface VehicleRepository extends JPASearchRepository<Vehicle, Long> {

}
