package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Documents;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Documents entity.
 */
public interface DocumentsSearchRepository extends JPASearchRepository<Documents, Long> {
}
