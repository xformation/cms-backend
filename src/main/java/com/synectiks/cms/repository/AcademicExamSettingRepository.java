package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicExamSetting;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicExamSetting entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicExamSettingRepository extends JPASearchRepository<AcademicExamSetting, Long> {

}
