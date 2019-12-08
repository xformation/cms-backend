package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AcademicHistory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AcademicHistory entity.
 */
@Repository
public interface AcademicHistoryRepository
		extends JPASearchRepository<AcademicHistory, Long> {

}
