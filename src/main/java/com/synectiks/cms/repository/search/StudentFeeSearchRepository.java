package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFee entity.
 */
public interface StudentFeeSearchRepository extends JPASearchRepository<StudentFee, Long> {
}
