package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Holiday entity.
 */
@Repository
public interface HolidayRepository extends JPASearchRepository<Holiday, Long> {

}
