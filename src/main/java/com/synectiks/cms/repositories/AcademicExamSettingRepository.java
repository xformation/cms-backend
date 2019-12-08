package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AcademicExamSetting;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AcademicExamSetting entity.
 */
@Repository
public interface AcademicExamSettingRepository extends JPASearchRepository<AcademicExamSetting, Long> {

}
