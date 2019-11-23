package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.AdmissionEnquiry;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionEnquiry entity.
 */
public interface AdmissionEnquirySearchRepository extends JPASearchRepository<AdmissionEnquiry, Long> {
}
