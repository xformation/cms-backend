package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.CompetitiveExam;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the CompetitiveExam entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompetitiveExamRepository extends JPASearchRepository<CompetitiveExam, Long> {

}
