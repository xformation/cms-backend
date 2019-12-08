package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.City;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the City entity.
 */
@Repository
public interface CityRepository extends JPASearchRepository<City, Long> {

}
