package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Student;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Student entity.
 */
public interface StudentSearchRepository extends JPASearchRepository<Student, Long> {
}
