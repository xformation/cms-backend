package com.synectiks.cms.repository;

import com.synectiks.cms.domain.FeeCategory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FeeCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FeeCategoryRepository extends JpaRepository<FeeCategory, Long> {

}
