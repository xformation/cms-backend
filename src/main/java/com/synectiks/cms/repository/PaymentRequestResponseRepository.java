package com.synectiks.cms.repository;

import com.synectiks.cms.domain.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentRequestResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestResponseRepository extends JPASearchRepository<PaymentRequestResponse, Long> {

}
