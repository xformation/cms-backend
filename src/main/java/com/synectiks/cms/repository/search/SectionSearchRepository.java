package com.synectiks.cms.repository.search;

import com.synectiks.commons.entities.cms.Section;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data Elasticsearch repository for the Section entity.
 */
public interface SectionSearchRepository extends JPASearchRepository<Section, Long> {
}
