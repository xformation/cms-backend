package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Subject;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Subject entity.
 */
@Repository
public interface SubjectRepository extends JPASearchRepository<Subject, Long> {

}
