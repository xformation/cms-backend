package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Subject;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Subject entity.
 */
public interface SubjectSearchRepository extends JPASearchRepository<Subject, Long> {
}
