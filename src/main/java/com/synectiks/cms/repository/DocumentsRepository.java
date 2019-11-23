package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Documents;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Documents entity.
 */
@Repository
public interface DocumentsRepository extends JPASearchRepository<Documents, Long> {

}
