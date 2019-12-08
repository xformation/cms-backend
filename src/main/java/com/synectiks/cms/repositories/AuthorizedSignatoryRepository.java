package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.AuthorizedSignatory;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the AuthorizedSignatory entity.
 */
@Repository
public interface AuthorizedSignatoryRepository
		extends JPASearchRepository<AuthorizedSignatory, Long> {

}
