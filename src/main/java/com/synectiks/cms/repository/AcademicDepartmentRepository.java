package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicDepartment;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicDepartment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicDepartmentRepository extends JpaRepository<AcademicDepartment, Long> {

}
