package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentAttendance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentAttendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentAttendanceRepository extends JpaRepository<StudentAttendance, Long> {

}
