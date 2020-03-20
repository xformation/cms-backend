package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the PaymentRequestResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRequestResponseRepository extends JPASearchRepository<PaymentRequestResponse, Long> {

}
