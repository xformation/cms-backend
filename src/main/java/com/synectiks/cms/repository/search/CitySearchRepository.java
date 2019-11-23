package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.City;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the City entity.
 */
public interface CitySearchRepository extends JPASearchRepository<City, Long> {
}
