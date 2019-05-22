package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.TypeOfGrading;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TypeOfGrading entity.
 */
public interface TypeOfGradingSearchRepository extends ElasticsearchRepository<TypeOfGrading, Long> {
}
