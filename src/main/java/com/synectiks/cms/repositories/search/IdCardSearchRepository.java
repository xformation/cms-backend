package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.IdCard;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the IdCard entity.
 */
public interface IdCardSearchRepository extends JPASearchRepository<IdCard, Long> {
}
