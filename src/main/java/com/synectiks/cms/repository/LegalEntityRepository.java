package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.LegalEntity;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the LegalEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LegalEntityRepository extends JPASearchRepository<LegalEntity, Long> {

}
