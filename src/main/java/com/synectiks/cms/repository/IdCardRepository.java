package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.IdCard;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the IdCard entity.
 */
@Repository
public interface IdCardRepository extends JPASearchRepository<IdCard, Long> {

}
