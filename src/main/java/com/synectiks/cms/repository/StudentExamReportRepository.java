package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.StudentExamReport;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the StudentExamReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentExamReportRepository extends JPASearchRepository<StudentExamReport, Long> {

}
