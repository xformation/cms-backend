package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicSubject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicSubject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicSubjectRepository extends JpaRepository<AcademicSubject, Long> {

}
