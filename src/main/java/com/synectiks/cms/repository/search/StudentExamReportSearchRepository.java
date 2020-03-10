package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentExamReport entity.
 */
public interface StudentExamReportSearchRepository extends JPASearchRepository<StudentExamReport, Long> {
}
