package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.AttendanceMaster;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AttendanceMaster entity.
 */
public interface AttendanceMasterSearchRepository extends JPASearchRepository<AttendanceMaster, Long> {
}
