package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AcademicExamSetting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicExamSettingRepository extends JPASearchRepository<AcademicExamSetting, Long> {

}
