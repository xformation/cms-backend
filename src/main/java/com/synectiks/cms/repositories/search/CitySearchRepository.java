package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.City;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the City entity.
 */
public interface CitySearchRepository extends JPASearchRepository<City, Long> {
}
