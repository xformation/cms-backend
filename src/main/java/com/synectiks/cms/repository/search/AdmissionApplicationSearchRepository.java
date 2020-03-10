package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionApplication entity.
 */
public interface AdmissionApplicationSearchRepository extends JPASearchRepository<AdmissionApplication, Long> {
}
