package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.AuthorizedSignatory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AuthorizedSignatory entity.
 */
@Repository
public interface AuthorizedSignatoryRepository
		extends JPASearchRepository<AuthorizedSignatory, Long> {

}
