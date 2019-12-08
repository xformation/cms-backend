package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Notifications;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Notifications entity.
 */
@Repository
public interface NotificationsRepository
		extends JPASearchRepository<Notifications, Long> {

}
