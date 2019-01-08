package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicYear;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicYear entity.
 */
public interface AcademicYearSearchRepository extends ElasticsearchRepository<AcademicYear, Long> {
}
