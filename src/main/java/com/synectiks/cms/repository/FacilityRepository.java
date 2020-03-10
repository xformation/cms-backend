package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Facility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityRepository extends JPASearchRepository<Facility, Long> {

}
