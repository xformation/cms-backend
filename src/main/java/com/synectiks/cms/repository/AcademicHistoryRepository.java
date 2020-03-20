package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AcademicHistory;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AcademicHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicHistoryRepository extends JPASearchRepository<AcademicHistory, Long> {

}
