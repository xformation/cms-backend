package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Documents;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Documents entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentsRepository extends JPASearchRepository<Documents, Long> {

}
