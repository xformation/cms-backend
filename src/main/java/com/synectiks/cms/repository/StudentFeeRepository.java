package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentFee entity.
 */
@Repository
public interface StudentFeeRepository extends JPASearchRepository<StudentFee, Long> {

}
