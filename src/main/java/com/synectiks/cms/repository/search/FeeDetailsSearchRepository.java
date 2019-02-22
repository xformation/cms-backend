package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.FeeDetails;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the FeeDetails entity.
 */
public interface FeeDetailsSearchRepository extends ElasticsearchRepository<FeeDetails, Long> {
}
