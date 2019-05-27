package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentExamReport;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentExamReport entity.
 */
public interface StudentExamReportSearchRepository extends ElasticsearchRepository<StudentExamReport, Long> {
}
