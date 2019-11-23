package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Teacher;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Teacher entity.
 */
@Repository
public interface TeacherRepository extends JPASearchRepository<Teacher, Long> {

}
