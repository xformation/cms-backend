package com.synectiks.cms.repository;

import com.synectiks.cms.domain.CollegeBranches;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CollegeBranches entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CollegeBranchesRepository extends JpaRepository<CollegeBranches, Long> {

}
