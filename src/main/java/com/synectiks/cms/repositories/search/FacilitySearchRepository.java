package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Facility;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Facility entity.
 */
public interface FacilitySearchRepository extends JPASearchRepository<Facility, Long> {
}
