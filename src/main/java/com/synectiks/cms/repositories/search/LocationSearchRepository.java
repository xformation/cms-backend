package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Location;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Location entity.
 */
public interface LocationSearchRepository extends JPASearchRepository<Location, Long> {
}
