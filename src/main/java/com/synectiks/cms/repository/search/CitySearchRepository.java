package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.City;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the City entity.
 */
public interface CitySearchRepository extends JPASearchRepository<City, Long> {
}
