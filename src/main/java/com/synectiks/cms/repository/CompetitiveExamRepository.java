package com.synectiks.cms.repository;

import com.synectiks.cms.domain.CompetitiveExam;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CompetitiveExam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetitiveExamRepository extends JPASearchRepository<CompetitiveExam, Long> {

}
