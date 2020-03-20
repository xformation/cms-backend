package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.TypeOfGrading;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the TypeOfGrading entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeOfGradingRepository extends JPASearchRepository<TypeOfGrading, Long> {

}
