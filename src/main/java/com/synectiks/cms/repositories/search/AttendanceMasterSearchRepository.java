package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AttendanceMaster;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AttendanceMaster entity.
 */
public interface AttendanceMasterSearchRepository extends JPASearchRepository<AttendanceMaster, Long> {
}
