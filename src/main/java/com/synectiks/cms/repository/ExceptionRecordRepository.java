package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.ExceptionRecord;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the ExceptionRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExceptionRecordRepository extends JPASearchRepository<ExceptionRecord, Long> {

}
