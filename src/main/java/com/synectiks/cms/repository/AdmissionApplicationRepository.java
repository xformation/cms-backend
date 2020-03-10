package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdmissionApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionApplicationRepository extends JPASearchRepository<AdmissionApplication, Long> {

}
