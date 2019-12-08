package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AcademicExamSetting;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicExamSetting entity.
 */
public interface AcademicExamSettingSearchRepository extends JPASearchRepository<AcademicExamSetting, Long> {
}
