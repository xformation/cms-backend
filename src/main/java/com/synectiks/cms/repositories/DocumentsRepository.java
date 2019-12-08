package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Documents;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Documents entity.
 */
@Repository
public interface DocumentsRepository extends JPASearchRepository<Documents, Long> {

}
