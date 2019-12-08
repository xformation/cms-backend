package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Reports;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Reports entity.
 */
@Repository
public interface ReportsRepository extends JPASearchRepository<Reports, Long> {

}
