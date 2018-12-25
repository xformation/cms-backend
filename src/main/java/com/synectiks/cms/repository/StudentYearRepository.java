package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentYear;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentYear entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentYearRepository extends JpaRepository<StudentYear, Long> {

}
