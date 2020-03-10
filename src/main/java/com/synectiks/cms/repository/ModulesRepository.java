package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Modules;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Modules entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModulesRepository extends JPASearchRepository<Modules, Long> {

}
