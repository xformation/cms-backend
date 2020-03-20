package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Facility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityRepository extends JPASearchRepository<Facility, Long> {

}
