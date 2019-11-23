package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.Section;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Section entity.
 */
@Repository
public interface SectionRepository extends JPASearchRepository<Section, Long> {

}
