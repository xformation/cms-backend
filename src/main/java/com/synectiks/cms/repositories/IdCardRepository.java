package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.IdCard;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the IdCard entity.
 */
@Repository
public interface IdCardRepository extends JPASearchRepository<IdCard, Long> {

}
