package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Lecture entity.
 */
@Repository
public interface LectureRepository extends JPASearchRepository<Lecture, Long> {

}
