package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.AttendanceMaster;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AttendanceMaster entity.
 */
@Repository
public interface AttendanceMasterRepository
		extends JPASearchRepository<AttendanceMaster, Long> {

}
