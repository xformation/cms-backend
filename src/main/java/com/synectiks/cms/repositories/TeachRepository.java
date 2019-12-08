package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Teach;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Teach entity.
 */
@Repository
public interface TeachRepository extends JPASearchRepository<Teach, Long> {

}
