package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicYear;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicYear entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {

}
