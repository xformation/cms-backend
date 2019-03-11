package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdmissionEnquiry;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionEnquiry entity.
 */
public interface AdmissionEnquirySearchRepository extends ElasticsearchRepository<AdmissionEnquiry, Long> {
}
