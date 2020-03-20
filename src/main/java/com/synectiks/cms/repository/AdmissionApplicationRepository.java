package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AdmissionApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionApplicationRepository extends JPASearchRepository<AdmissionApplication, Long> {

}
