package com.synectiks.cms.repository;

import com.synectiks.cms.domain.IdCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the IdCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IdCardRepository extends JpaRepository<IdCard, Long> {

}