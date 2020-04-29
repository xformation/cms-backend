package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Book;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JPASearchRepository<Book, Long> {

}
