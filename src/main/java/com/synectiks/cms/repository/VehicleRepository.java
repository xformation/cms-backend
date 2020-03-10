package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Vehicle;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Vehicle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VehicleRepository extends JPASearchRepository<Vehicle, Long> {

}
