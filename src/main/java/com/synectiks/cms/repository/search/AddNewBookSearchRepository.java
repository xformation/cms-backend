package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AddNewBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AddNewBook entity.
 */
public interface AddNewBookSearchRepository extends ElasticsearchRepository<AddNewBook, Long> {
}
