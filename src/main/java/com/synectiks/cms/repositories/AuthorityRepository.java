package com.synectiks.cms.repositories;

import com.synectiks.cms.entities.Authority;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JPASearchRepository<Authority, String> {
}
