package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Teach;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Teach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachRepository extends JPASearchRepository<Teach, Long> {

}
