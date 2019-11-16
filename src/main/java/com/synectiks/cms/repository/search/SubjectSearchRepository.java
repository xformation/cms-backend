package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Subject entity.
 */
public interface SubjectSearchRepository extends JPASearchRepository<Subject, Long> {
}
