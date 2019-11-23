package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the PaymentRequestResponse entity.
 */
@Repository
public interface PaymentRequestResponseRepository
		extends JPASearchRepository<PaymentRequestResponse, Long> {

}
