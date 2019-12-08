package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Book;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Book entity.
 */
public interface BookSearchRepository extends JPASearchRepository<Book, Long> {
}
