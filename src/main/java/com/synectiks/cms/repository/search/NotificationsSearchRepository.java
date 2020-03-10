package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Notifications} entity.
 */
public interface NotificationsSearchRepository extends JPASearchRepository<Notifications, Long> {
}
