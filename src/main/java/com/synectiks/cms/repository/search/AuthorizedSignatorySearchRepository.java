package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AuthorizedSignatory;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AuthorizedSignatory entity.
 */
public interface AuthorizedSignatorySearchRepository extends JPASearchRepository<AuthorizedSignatory, Long> {
}
