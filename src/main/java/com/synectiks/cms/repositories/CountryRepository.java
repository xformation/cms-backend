package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Country;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Country entity.
 */
@Repository
public interface CountryRepository extends JPASearchRepository<Country, Long> {

}
