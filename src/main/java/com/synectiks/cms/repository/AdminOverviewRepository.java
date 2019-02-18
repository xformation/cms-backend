package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdminOverview;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdminOverview entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdminOverviewRepository extends JpaRepository<AdminOverview, Long> {

}
