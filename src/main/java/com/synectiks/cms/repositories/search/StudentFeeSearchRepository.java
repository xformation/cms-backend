package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFee entity.
 */
public interface StudentFeeSearchRepository extends JPASearchRepository<StudentFee, Long> {
}
