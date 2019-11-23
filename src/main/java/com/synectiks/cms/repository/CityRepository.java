package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.City;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the City entity.
 */
@Repository
public interface CityRepository extends JPASearchRepository<City, Long> {

}
