package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentFee;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFee entity.
 */
public interface StudentFeeSearchRepository extends JPASearchRepository<StudentFee, Long> {
}
