package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.CourseOffer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CourseOffer entity.
 */
public interface CourseOfferSearchRepository extends ElasticsearchRepository<CourseOffer, Long> {
}
