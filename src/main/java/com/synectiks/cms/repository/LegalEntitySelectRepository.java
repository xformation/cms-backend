package com.synectiks.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.LegalEntityAuthSignatoryLink;


/**
 * Spring Data  repository for the LegalEntityAuthSignatoryLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LegalEntitySelectRepository extends JpaRepository<LegalEntityAuthSignatoryLink, Long> {

}
