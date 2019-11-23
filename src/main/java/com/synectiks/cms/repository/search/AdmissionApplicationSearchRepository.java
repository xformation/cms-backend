package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionApplication entity.
 */
public interface AdmissionApplicationSearchRepository extends JPASearchRepository<AdmissionApplication, Long> {
}
