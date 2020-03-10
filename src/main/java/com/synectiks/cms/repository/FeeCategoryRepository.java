package com.synectiks.cms.repository;

import com.synectiks.cms.domain.FeeCategory;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeeCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeCategoryRepository extends JPASearchRepository<FeeCategory, Long> {

}
