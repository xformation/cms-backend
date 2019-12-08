package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AcademicYear entity.
 */
@Repository
public interface AcademicYearRepository extends JPASearchRepository<AcademicYear, Long> {

}
