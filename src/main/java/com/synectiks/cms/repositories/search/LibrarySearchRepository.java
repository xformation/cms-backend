package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Library;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Library entity.
 */
public interface LibrarySearchRepository extends JPASearchRepository<Library, Long> {
}
