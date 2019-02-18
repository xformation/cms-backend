package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdminOverview;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminOverview entity.
 */
public interface AdminOverviewSearchRepository extends ElasticsearchRepository<AdminOverview, Long> {
}
