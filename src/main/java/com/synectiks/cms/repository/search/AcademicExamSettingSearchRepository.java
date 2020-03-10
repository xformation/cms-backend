package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicExamSetting entity.
 */
public interface AcademicExamSettingSearchRepository extends JPASearchRepository<AcademicExamSetting, Long> {
}
