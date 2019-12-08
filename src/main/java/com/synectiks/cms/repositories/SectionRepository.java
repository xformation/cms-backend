package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Section;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Section entity.
 */
@Repository
public interface SectionRepository extends JPASearchRepository<Section, Long> {

}
