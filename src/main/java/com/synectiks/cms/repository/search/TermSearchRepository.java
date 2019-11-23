package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Term;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Term entity.
 */
public interface TermSearchRepository extends JPASearchRepository<Term, Long> {
}
