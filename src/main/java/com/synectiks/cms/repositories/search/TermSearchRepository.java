package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Term;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Term entity.
 */
public interface TermSearchRepository extends JPASearchRepository<Term, Long> {
}
