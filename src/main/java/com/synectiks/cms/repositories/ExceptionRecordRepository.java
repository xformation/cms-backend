package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.ExceptionRecord;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the ExceptionRecord entity.
 */
@Repository
public interface ExceptionRecordRepository
		extends JPASearchRepository<ExceptionRecord, Long> {

}
