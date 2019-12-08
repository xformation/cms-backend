package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.StudentFacilityLink;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the StudentFacilityLink entity.
 */
@Repository
public interface StudentFacilityLinkRepository
		extends JPASearchRepository<StudentFacilityLink, Long> {

}
