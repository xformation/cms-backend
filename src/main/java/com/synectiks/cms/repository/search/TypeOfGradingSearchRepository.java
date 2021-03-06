package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.TypeOfGrading;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the TypeOfGrading entity.
 */
public interface TypeOfGradingSearchRepository extends JPASearchRepository<TypeOfGrading, Long> {
}
