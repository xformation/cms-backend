package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AdmissionEnquiry;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionEnquiry entity.
 */
public interface AdmissionEnquirySearchRepository extends JPASearchRepository<AdmissionEnquiry, Long> {
}
