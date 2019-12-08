package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Lecture entity.
 */
@Repository
public interface LectureRepository extends JPASearchRepository<Lecture, Long> {

}
