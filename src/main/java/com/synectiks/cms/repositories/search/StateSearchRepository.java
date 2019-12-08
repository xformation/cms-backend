package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.State;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the State entity.
 */
public interface StateSearchRepository extends JPASearchRepository<State, Long> {
}
