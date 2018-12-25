package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Departments;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the Departments entity.
 */
public interface DepartmentsSearchRepository extends ElasticsearchRepository<Departments, Long> {
}
