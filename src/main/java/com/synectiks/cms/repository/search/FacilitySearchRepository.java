package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Facility;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Facility entity.
 */
public interface FacilitySearchRepository extends ElasticsearchRepository<Facility, Long> {
}
