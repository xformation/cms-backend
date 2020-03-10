package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Country;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Country entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CountryRepository extends JPASearchRepository<Country, Long> {

}
