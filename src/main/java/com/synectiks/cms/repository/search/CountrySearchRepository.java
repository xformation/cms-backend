package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.Country;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Country entity.
 */
public interface CountrySearchRepository extends JPASearchRepository<Country, Long> {
}
