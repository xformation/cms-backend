package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicExamSetting;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicExamSetting entity.
 */
public interface AcademicExamSettingSearchRepository extends ElasticsearchRepository<AcademicExamSetting, Long> {
}
