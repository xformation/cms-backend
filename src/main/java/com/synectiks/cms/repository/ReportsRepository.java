package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Reports;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Reports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportsRepository extends JPASearchRepository<Reports, Long> {

}
