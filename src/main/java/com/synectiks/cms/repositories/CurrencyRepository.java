package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Currency;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Currency entity.
 */
@Repository
public interface CurrencyRepository extends JPASearchRepository<Currency, Long> {

}
