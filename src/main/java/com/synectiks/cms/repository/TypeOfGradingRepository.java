package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.TypeOfGrading;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the TypeOfGrading entity.
 */
@Repository
public interface TypeOfGradingRepository
		extends JPASearchRepository<TypeOfGrading, Long> {

}
