package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Branch entity.
 */
public interface BranchSearchRepository extends JPASearchRepository<Branch, Long> {
}
