package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Authority;

import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JPASearchRepository<Authority, String> {
}
