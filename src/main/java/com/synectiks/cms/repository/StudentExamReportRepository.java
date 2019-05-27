package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentExamReport;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentExamReport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentExamReportRepository extends JpaRepository<StudentExamReport, Long> {

}
