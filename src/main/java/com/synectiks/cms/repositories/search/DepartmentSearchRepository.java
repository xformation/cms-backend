package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Department;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Department entity.
 */
public interface DepartmentSearchRepository extends JPASearchRepository<Department, Long> {
}
