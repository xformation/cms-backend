package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentExamReport entity.
 */
@Repository
public interface StudentExamReportRepository
		extends JPASearchRepository<StudentExamReport, Long> {

}
