package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdminAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminAttendance entity.
 */
public interface AdminAttendanceSearchRepository extends JPASearchRepository<AdminAttendance, Long> {
}
