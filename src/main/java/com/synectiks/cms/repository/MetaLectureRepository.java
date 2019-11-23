package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.MetaLecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the MetaLecture entity.
 */
@Repository
public interface MetaLectureRepository extends JPASearchRepository<MetaLecture, Long> {

}
