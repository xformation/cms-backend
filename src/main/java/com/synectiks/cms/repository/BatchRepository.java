package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Batch;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Batch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BatchRepository extends JPASearchRepository<Batch, Long> {

}
