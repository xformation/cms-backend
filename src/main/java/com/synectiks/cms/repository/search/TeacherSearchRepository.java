package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teacher entity.
 */
public interface TeacherSearchRepository extends JPASearchRepository<Teacher, Long> {
}
