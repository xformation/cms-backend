package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Branch entity.
 */
public interface BranchSearchRepository extends JPASearchRepository<Branch, Long> {
}
