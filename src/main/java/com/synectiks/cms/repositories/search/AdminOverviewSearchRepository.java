package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminOverview entity.
 */
public interface AdminOverviewSearchRepository extends JPASearchRepository<AdminOverview, Long> {
}
