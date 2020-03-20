package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the StudentFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentFeeRepository extends JPASearchRepository<StudentFee, Long> {

}
