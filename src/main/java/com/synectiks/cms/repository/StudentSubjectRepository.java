package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentSubject;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentSubject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentSubjectRepository extends JpaRepository<StudentSubject, Long> {

}
