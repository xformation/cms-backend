package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Department;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Department entity.
 */
public interface DepartmentSearchRepository extends JPASearchRepository<Department, Long> {
}
