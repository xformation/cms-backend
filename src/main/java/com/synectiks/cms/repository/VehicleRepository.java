package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Vehicle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleRepository extends JPASearchRepository<Vehicle, Long> {

}
