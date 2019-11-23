package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Teach;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Teach entity.
 */
@Repository
public interface TeachRepository extends JPASearchRepository<Teach, Long> {

}
