package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AdmissionEnquiry;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdmissionEnquiry entity.
 */
@Repository
public interface AdmissionEnquiryRepository
		extends JPASearchRepository<AdmissionEnquiry, Long> {

}
