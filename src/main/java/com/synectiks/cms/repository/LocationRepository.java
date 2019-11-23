package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Location;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Location entity.
 */
@Repository
public interface LocationRepository extends JPASearchRepository<Location, Long> {

}
