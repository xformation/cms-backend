package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.IdCard;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the IdCard entity.
 */
public interface IdCardSearchRepository extends JPASearchRepository<IdCard, Long> {
}
