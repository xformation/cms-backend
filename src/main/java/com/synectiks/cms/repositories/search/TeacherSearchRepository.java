package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Teacher;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Teacher entity.
 */
public interface TeacherSearchRepository extends JPASearchRepository<Teacher, Long> {
}
