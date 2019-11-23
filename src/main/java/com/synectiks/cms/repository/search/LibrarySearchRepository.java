package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Library;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Library entity.
 */
public interface LibrarySearchRepository extends JPASearchRepository<Library, Long> {
}
