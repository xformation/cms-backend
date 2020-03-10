package com.synectiks.cms.repository;

import com.synectiks.cms.domain.LateFee;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LateFee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LateFeeRepository extends JPASearchRepository<LateFee, Long> {

}
