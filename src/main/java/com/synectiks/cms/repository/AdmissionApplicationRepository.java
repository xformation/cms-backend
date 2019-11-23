package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdmissionApplication entity.
 */
@Repository
public interface AdmissionApplicationRepository
		extends JPASearchRepository<AdmissionApplication, Long> {

}
