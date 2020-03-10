package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Holiday;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Holiday entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HolidayRepository extends JPASearchRepository<Holiday, Long> {

}
