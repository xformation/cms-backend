package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Subject;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Subject entity.
 */
@Repository
public interface SubjectRepository extends JPASearchRepository<Subject, Long> {

}
