package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Currency;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Currency entity.
 */
@Repository
public interface CurrencyRepository extends JPASearchRepository<Currency, Long> {

}
