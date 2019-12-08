package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.StudentAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentAttendance entity.
 */
public interface StudentAttendanceSearchRepository extends JPASearchRepository<StudentAttendance, Long> {
}
