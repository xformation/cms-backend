package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentExamReport entity.
 */
public interface StudentExamReportSearchRepository extends JPASearchRepository<StudentExamReport, Long> {
}
