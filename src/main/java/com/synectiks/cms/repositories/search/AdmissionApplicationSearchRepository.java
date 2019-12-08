package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionApplication entity.
 */
public interface AdmissionApplicationSearchRepository extends JPASearchRepository<AdmissionApplication, Long> {
}
