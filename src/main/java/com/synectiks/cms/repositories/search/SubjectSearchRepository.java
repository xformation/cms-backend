package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Subject;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Subject entity.
 */
public interface SubjectSearchRepository extends JPASearchRepository<Subject, Long> {
}
