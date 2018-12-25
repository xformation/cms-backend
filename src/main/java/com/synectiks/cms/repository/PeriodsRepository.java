package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Periods;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Periods entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeriodsRepository extends JpaRepository<Periods, Long> {

}
