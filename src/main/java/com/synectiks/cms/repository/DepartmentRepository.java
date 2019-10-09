package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Department;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Department entity.
 */
@Repository
public interface DepartmentRepository extends JPASearchRepository<Department, Long> {

}
