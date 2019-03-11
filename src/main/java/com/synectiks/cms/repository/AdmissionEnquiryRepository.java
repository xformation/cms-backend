package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AdmissionEnquiry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AdmissionEnquiry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdmissionEnquiryRepository extends JpaRepository<AdmissionEnquiry, Long> {

}
