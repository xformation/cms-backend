package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Library;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Library entity.
 */
@Repository
public interface LibraryRepository extends JPASearchRepository<Library, Long> {

}
