package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.StudentAttendance;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentAttendance entity.
 */
@Repository
public interface StudentAttendanceRepository
		extends JPASearchRepository<StudentAttendance, Long> {

}
