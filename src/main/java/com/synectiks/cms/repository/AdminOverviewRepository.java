package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdminOverview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminOverviewRepository extends JPASearchRepository<AdminOverview, Long> {

}
