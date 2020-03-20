package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Documents;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Documents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentsRepository extends JPASearchRepository<Documents, Long> {

}
