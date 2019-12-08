package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.TypeOfGrading;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the TypeOfGrading entity.
 */
@Repository
public interface TypeOfGradingRepository
		extends JPASearchRepository<TypeOfGrading, Long> {

}
