package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Holiday entity.
 */
@Repository
public interface HolidayRepository extends JPASearchRepository<Holiday, Long> {

}
