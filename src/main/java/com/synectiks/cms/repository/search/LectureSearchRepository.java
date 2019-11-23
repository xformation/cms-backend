package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Lecture entity.
 */
public interface LectureSearchRepository extends JPASearchRepository<Lecture, Long> {
}
