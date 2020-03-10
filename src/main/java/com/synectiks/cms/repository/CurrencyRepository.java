package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Currency;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Currency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyRepository extends JPASearchRepository<Currency, Long> {

}
