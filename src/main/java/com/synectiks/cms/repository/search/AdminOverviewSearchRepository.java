package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminOverview entity.
 */
public interface AdminOverviewSearchRepository extends JPASearchRepository<AdminOverview, Long> {
}
