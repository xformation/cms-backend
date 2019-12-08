package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.CompetitiveExam;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the CompetitiveExam entity.
 */
public interface CompetitiveExamSearchRepository extends JPASearchRepository<CompetitiveExam, Long> {
}
