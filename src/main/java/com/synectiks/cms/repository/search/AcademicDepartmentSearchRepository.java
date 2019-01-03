package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicDepartment;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicDepartment entity.
 */
public interface AcademicDepartmentSearchRepository extends ElasticsearchRepository<AcademicDepartment, Long> {
}
