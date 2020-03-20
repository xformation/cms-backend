package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Notifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationsRepository extends JPASearchRepository<Notifications, Long> {

}
