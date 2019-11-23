package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.State;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the State entity.
 */
@Repository
public interface StateRepository extends JPASearchRepository<State, Long> {

}
