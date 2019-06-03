package com.synectiks.cms.repository;

import com.synectiks.cms.domain.StudentFacilityLink;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the StudentFacilityLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StudentFacilityLinkRepository extends JpaRepository<StudentFacilityLink, Long> {

}
