package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Department;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Department entity.
 */
public interface DepartmentSearchRepository extends JPASearchRepository<Department, Long> {
}
