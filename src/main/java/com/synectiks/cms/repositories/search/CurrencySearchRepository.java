package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Currency;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Currency entity.
 */
public interface CurrencySearchRepository extends JPASearchRepository<Currency, Long> {
}
