package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.AdminOverview;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the AdminOverview entity.
 */
public interface AdminOverviewSearchRepository extends JPASearchRepository<AdminOverview, Long> {
}
