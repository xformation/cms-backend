package com.synectiks.cms.repositories.search;

import com.synectiks.cms.entities.MetaLecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the MetaLecture entity.
 */
public interface MetaLectureSearchRepository extends JPASearchRepository<MetaLecture, Long> {
}
