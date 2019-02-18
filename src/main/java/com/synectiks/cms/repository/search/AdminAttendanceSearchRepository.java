package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdminAttendance;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminAttendance entity.
 */
public interface AdminAttendanceSearchRepository extends ElasticsearchRepository<AdminAttendance, Long> {
}
