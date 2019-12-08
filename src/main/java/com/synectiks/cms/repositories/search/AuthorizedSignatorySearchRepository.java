package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AuthorizedSignatory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AuthorizedSignatory entity.
 */
public interface AuthorizedSignatorySearchRepository extends JPASearchRepository<AuthorizedSignatory, Long> {
}
