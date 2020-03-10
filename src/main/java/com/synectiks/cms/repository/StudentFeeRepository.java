package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentFeeRepository extends JPASearchRepository<StudentFee, Long> {

}
