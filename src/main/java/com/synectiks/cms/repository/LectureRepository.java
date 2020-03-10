package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Lecture;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Lecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LectureRepository extends JPASearchRepository<Lecture, Long> {

}
