package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Lecture entity.
 */
public interface LectureSearchRepository extends JPASearchRepository<Lecture, Long> {
}
