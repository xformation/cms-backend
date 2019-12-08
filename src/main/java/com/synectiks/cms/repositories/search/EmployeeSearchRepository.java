package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Employee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Employee entity.
 */
public interface EmployeeSearchRepository extends JPASearchRepository<Employee, Long> {
}
