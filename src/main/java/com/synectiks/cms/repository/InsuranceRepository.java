package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Insurance;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Insurance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InsuranceRepository extends JPASearchRepository<Insurance, Long> {

}
