package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AuthorizedSignatory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AuthorizedSignatory entity.
 */
public interface AuthorizedSignatorySearchRepository extends ElasticsearchRepository<AuthorizedSignatory, Long> {
}
