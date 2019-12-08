package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AdminAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdminAttendance entity.
 */
@Repository
public interface AdminAttendanceRepository
		extends JPASearchRepository<AdminAttendance, Long> {

}
