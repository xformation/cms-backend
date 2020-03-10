package com.synectiks.cms.repository;

import com.synectiks.cms.domain.FeeDetails;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeeDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeDetailsRepository extends JPASearchRepository<FeeDetails, Long> {

}
