package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Facility;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Facility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FacilityRepository extends JpaRepository<Facility, Long> {

}
