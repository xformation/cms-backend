package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.LegalEntity;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the LegalEntity entity.
 */
@Repository
public interface LegalEntityRepository extends JPASearchRepository<LegalEntity, Long> {

}
