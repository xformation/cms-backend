package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentFee entity.
 */
@Repository
public interface StudentFeeRepository extends JPASearchRepository<StudentFee, Long> {

}
