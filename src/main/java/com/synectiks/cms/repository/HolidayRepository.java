package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Holiday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HolidayRepository extends JPASearchRepository<Holiday, Long> {

}
