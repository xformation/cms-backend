package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Modules;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Modules} entity.
 */
public interface ModulesSearchRepository extends ElasticsearchRepository<Modules, Long> {
}
