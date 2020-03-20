package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the AdminOverview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminOverviewRepository extends JPASearchRepository<AdminOverview, Long> {

}
