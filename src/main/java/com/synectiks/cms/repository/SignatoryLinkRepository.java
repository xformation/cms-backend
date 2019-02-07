package com.synectiks.cms.repository;

import com.synectiks.cms.domain.SignatoryLink;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SignatoryLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SignatoryLinkRepository extends JpaRepository<SignatoryLink, Long> {

}
