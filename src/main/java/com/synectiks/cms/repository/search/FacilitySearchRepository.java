package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Facility;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Facility entity.
 */
public interface FacilitySearchRepository extends JPASearchRepository<Facility, Long> {
}
