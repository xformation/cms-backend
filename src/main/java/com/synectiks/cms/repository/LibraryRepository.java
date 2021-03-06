package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Library;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Library entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LibraryRepository extends JPASearchRepository<Library, Long> {

}
