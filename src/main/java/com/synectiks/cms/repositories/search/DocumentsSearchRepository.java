package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Documents;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Documents entity.
 */
public interface DocumentsSearchRepository extends JPASearchRepository<Documents, Long> {
}
