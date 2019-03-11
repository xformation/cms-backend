package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdmissionApplication;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdmissionApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionApplicationRepository extends JpaRepository<AdmissionApplication, Long> {

}
