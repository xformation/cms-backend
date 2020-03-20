package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Book;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Book entity.
 */
public interface BookSearchRepository extends JPASearchRepository<Book, Long> {
}
