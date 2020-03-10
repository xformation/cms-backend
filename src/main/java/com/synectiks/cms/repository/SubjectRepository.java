package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Subject;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Subject entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubjectRepository extends JPASearchRepository<Subject, Long> {

}
