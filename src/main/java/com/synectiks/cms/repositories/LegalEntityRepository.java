package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.LegalEntity;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the LegalEntity entity.
 */
@Repository
public interface LegalEntityRepository extends JPASearchRepository<LegalEntity, Long> {

}
