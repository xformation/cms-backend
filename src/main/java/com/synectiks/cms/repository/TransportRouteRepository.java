package com.synectiks.cms.repository;

import com.synectiks.cms.domain.TransportRoute;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TransportRoute entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TransportRouteRepository extends JpaRepository<TransportRoute, Long> {

}
