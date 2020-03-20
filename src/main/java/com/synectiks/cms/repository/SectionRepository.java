package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Section;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Section entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SectionRepository extends JPASearchRepository<Section, Long> {

}
