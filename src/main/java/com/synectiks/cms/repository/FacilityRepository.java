package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Facility;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Facility entity.
 */
@Repository
public interface FacilityRepository extends JPASearchRepository<Facility, Long> {

}
