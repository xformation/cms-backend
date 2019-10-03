package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicExamSetting;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicExamSetting entity.
 */
@Repository
public interface AcademicExamSettingRepository extends JpaRepository<AcademicExamSetting, Long> {

}
