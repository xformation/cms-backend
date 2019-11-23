package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.commons.entities.cms.PaymentRemainder;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the PaymentRemainder entity.
 */
@Repository
public interface PaymentRemainderRepository
		extends JPASearchRepository<PaymentRemainder, Long> {

}
