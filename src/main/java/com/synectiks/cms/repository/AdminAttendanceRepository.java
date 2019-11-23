package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.AdminAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdminAttendance entity.
 */
@Repository
public interface AdminAttendanceRepository
		extends JPASearchRepository<AdminAttendance, Long> {

}
