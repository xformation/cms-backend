package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Country;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Country entity.
 */
@Repository
public interface CountryRepository extends JPASearchRepository<Country, Long> {

}
