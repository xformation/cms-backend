package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Holiday entity.
 */
public interface HolidaySearchRepository extends JPASearchRepository<Holiday, Long> {
}
