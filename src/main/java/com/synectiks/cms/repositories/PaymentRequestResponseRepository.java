package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.PaymentRequestResponse;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the PaymentRequestResponse entity.
 */
@Repository
public interface PaymentRequestResponseRepository
		extends JPASearchRepository<PaymentRequestResponse, Long> {

}
