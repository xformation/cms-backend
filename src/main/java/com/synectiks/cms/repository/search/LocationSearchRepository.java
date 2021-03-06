package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Location;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Location entity.
 */
public interface LocationSearchRepository extends JPASearchRepository<Location, Long> {
}
