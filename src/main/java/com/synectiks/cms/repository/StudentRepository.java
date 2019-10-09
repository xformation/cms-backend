package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Student;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Student entity.
 */
@Repository
public interface StudentRepository extends JPASearchRepository<Student, Long> {

}
