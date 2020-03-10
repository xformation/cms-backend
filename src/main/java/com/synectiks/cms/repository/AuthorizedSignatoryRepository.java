package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AuthorizedSignatory;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AuthorizedSignatory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorizedSignatoryRepository extends JPASearchRepository<AuthorizedSignatory, Long> {

}
