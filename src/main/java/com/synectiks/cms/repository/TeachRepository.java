package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Teach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachRepository extends JPASearchRepository<Teach, Long> {

}
