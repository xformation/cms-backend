package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Library;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Library entity.
 */
@Repository
public interface LibraryRepository extends JPASearchRepository<Library, Long> {

}
