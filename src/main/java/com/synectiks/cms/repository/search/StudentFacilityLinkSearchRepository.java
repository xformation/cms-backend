package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentFacilityLink;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFacilityLink entity.
 */
public interface StudentFacilityLinkSearchRepository extends JPASearchRepository<StudentFacilityLink, Long> {
}
