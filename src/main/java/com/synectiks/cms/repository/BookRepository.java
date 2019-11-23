package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Book;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Book entity.
 */
@Repository
public interface BookRepository extends JPASearchRepository<Book, Long> {

}
