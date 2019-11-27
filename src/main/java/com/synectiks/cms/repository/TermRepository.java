package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Term;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Term entity.
 */
@Repository
public interface TermRepository extends JPASearchRepository<Term, Long> {

}
