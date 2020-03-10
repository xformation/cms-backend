package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Facility;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Facility entity.
 */
public interface FacilitySearchRepository extends JPASearchRepository<Facility, Long> {
}
