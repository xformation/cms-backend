package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Student;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Student entity.
 */
public interface StudentSearchRepository extends JPASearchRepository<Student, Long> {
}
