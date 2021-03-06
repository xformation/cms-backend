package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Term;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Term entity.
 */
public interface TermSearchRepository extends JPASearchRepository<Term, Long> {
}
