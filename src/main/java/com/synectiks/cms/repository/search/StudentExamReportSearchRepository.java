package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentExamReport entity.
 */
public interface StudentExamReportSearchRepository extends JPASearchRepository<StudentExamReport, Long> {
}
