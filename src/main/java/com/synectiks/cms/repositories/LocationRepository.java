package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Location;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Location entity.
 */
@Repository
public interface LocationRepository extends JPASearchRepository<Location, Long> {

}
