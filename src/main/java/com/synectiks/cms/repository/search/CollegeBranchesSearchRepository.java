package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.CollegeBranches;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CollegeBranches entity.
 */
public interface CollegeBranchesSearchRepository extends ElasticsearchRepository<CollegeBranches, Long> {
}
