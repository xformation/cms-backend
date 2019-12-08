package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Term;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Term entity.
 */
@Repository
public interface TermRepository extends JPASearchRepository<Term, Long> {

}
