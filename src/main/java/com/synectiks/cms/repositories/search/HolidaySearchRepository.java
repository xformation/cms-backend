package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Holiday entity.
 */
public interface HolidaySearchRepository extends JPASearchRepository<Holiday, Long> {
}
