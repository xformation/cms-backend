package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Teacher;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Teacher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherRepository extends JPASearchRepository<Teacher, Long> {

}
