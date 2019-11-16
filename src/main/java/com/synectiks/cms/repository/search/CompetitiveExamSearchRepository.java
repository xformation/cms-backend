package com.synectiks.cms.repository.search;

import com.synectiks.cms.domain.CompetitiveExam;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the CompetitiveExam entity.
 */
public interface CompetitiveExamSearchRepository extends JPASearchRepository<CompetitiveExam, Long> {
}
