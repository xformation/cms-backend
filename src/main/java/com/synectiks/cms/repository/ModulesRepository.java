package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.Modules;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the Modules entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModulesRepository extends JPASearchRepository<Modules, Long> {

}
