package com.synectiks.cms.repository;

import com.synectiks.cms.domain.MetaLecture;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MetaLecture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MetaLectureRepository extends JPASearchRepository<MetaLecture, Long> {

}
