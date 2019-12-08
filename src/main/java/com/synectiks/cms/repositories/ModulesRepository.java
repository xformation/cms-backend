package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.Modules;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the Modules entity.
 */
@Repository
public interface ModulesRepository extends JPASearchRepository<Modules, Long> {

}
