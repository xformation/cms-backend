package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AcademicYear entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicYearRepository extends JPASearchRepository<AcademicYear, Long> {

}
