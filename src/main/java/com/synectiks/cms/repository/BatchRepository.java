package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Batch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Batch entity.
 */
@Repository
public interface BatchRepository extends JPASearchRepository<Batch, Long> {

}
