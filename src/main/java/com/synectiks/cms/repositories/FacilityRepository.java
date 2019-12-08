package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Facility;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Facility entity.
 */
@Repository
public interface FacilityRepository extends JPASearchRepository<Facility, Long> {

}
