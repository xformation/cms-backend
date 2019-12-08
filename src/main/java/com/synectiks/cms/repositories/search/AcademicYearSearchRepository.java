package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AcademicYear;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AcademicYear entity.
 */
public interface AcademicYearSearchRepository extends JPASearchRepository<AcademicYear, Long> {
}
