package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdmissionApplication;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdmissionApplication entity.
 */
public interface AdmissionApplicationSearchRepository extends ElasticsearchRepository<AdmissionApplication, Long> {
}
