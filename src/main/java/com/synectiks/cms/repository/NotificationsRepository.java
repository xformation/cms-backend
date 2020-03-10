package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Notifications;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Notifications entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NotificationsRepository extends JPASearchRepository<Notifications, Long> {

}
