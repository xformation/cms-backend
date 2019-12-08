package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Modules;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Modules} entity.
 */
public interface ModulesSearchRepository extends JPASearchRepository<Modules, Long> {
}
