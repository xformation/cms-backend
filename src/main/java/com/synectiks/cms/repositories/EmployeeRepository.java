package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Employee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Employee entity.
 */
@Repository
public interface EmployeeRepository extends JPASearchRepository<Employee, Long> {

}
