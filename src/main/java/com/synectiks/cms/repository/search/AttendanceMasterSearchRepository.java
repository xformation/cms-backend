package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AttendanceMaster;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AttendanceMaster entity.
 */
public interface AttendanceMasterSearchRepository extends ElasticsearchRepository<AttendanceMaster, Long> {
}
