package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentAttendance entity.
 */
public interface StudentAttendanceSearchRepository extends JPASearchRepository<StudentAttendance, Long> {
}
