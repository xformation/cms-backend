package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.AcademicYear;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AcademicYear entity.
 */
@Repository
public interface AcademicYearRepository extends JPASearchRepository<AcademicYear, Long> {

}
