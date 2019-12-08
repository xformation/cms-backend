package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Notifications;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Notifications} entity.
 */
public interface NotificationsSearchRepository extends JPASearchRepository<Notifications, Long> {
}
