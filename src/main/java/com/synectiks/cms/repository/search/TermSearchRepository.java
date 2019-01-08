package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Term;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Term entity.
 */
public interface TermSearchRepository extends ElasticsearchRepository<Term, Long> {
}
