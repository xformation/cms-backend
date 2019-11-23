package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentExamReport entity.
 */
@Repository
public interface StudentExamReportRepository
		extends JPASearchRepository<StudentExamReport, Long> {

}
