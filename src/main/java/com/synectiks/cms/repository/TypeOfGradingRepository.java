package com.synectiks.cms.repository;

import com.synectiks.cms.domain.TypeOfGrading;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TypeOfGrading entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeOfGradingRepository extends JpaRepository<TypeOfGrading, Long> {

}
