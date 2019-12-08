package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AdminAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminAttendance entity.
 */
public interface AdminAttendanceSearchRepository extends JPASearchRepository<AdminAttendance, Long> {
}
