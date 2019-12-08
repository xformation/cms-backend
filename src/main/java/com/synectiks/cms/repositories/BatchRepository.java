package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Batch;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Batch entity.
 */
@Repository
public interface BatchRepository extends JPASearchRepository<Batch, Long> {

}
