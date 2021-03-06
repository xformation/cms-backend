package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.User;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
public interface UserSearchRepository extends JPASearchRepository<User, Long> {
}
