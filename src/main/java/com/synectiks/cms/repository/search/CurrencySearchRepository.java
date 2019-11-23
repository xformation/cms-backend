package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Currency;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Currency entity.
 */
public interface CurrencySearchRepository extends JPASearchRepository<Currency, Long> {
}
