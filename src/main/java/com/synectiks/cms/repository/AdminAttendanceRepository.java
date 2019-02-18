package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdminAttendance;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdminAttendance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminAttendanceRepository extends JpaRepository<AdminAttendance, Long> {

}
