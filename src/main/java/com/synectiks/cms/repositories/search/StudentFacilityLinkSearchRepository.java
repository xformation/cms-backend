package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.StudentFacilityLink;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFacilityLink entity.
 */
public interface StudentFacilityLinkSearchRepository extends JPASearchRepository<StudentFacilityLink, Long> {
}
