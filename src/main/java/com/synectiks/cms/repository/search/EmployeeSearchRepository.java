package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Employee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Employee entity.
 */
public interface EmployeeSearchRepository extends JPASearchRepository<Employee, Long> {
}
