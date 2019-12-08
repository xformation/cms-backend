package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Country;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Country entity.
 */
public interface CountrySearchRepository extends JPASearchRepository<Country, Long> {
}
