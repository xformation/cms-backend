package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FeeCategory entity.
 */
public interface FeeCategorySearchRepository extends JPASearchRepository<FeeCategory, Long> {
}
