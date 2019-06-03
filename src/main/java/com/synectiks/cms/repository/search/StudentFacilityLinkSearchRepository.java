package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.StudentFacilityLink;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StudentFacilityLink entity.
 */
public interface StudentFacilityLinkSearchRepository extends ElasticsearchRepository<StudentFacilityLink, Long> {
}
