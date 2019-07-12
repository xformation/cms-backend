package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Library;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Library entity.
 */
public interface LibrarySearchRepository extends ElasticsearchRepository<Library, Long> {
}
