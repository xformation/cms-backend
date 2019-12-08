package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AdminOverview entity.
 */
@Repository
public interface AdminOverviewRepository
		extends JPASearchRepository<AdminOverview, Long> {

}
