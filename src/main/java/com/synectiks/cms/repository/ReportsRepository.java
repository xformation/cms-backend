package com.synectiks.cms.repository;

import com.synectiks.cms.domain.Reports;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Reports entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long> {

}
