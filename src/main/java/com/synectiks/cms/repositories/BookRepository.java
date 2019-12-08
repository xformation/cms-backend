package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Book;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Book entity.
 */
@Repository
public interface BookRepository extends JPASearchRepository<Book, Long> {

}
