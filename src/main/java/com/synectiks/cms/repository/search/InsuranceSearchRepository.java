package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Insurance entity.
 */
public interface InsuranceSearchRepository extends JPASearchRepository<Insurance, Long> {
}
