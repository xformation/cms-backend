package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdminOverview entity.
 */
@Repository
public interface AdminOverviewRepository
		extends JPASearchRepository<AdminOverview, Long> {

}
