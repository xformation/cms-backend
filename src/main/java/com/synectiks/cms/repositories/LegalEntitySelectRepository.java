package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.LegalEntityAuthSignatoryLink;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the LegalEntityAuthSignatoryLink entity.
 */
@Repository
public interface LegalEntitySelectRepository
		extends JPASearchRepository<LegalEntityAuthSignatoryLink, Long> {

}
