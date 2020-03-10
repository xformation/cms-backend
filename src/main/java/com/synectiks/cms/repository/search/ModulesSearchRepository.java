package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Modules;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Modules} entity.
 */
public interface ModulesSearchRepository extends JPASearchRepository<Modules, Long> {
}
