package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Student;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Student entity.
 */
@Repository
public interface StudentRepository extends JPASearchRepository<Student, Long> {

}
