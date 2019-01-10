package com.synectiks.cms.repository;

import com.synectiks.cms.domain.CourseOffer;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CourseOffer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CourseOfferRepository extends JpaRepository<CourseOffer, Long> {

}
