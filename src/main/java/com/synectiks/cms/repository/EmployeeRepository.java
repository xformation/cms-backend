package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Employee;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Employee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmployeeRepository extends JPASearchRepository<Employee, Long> {

}
