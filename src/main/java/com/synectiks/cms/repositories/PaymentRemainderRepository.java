package com.synectiks.cms.repositories;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.entities.PaymentRemainder;
import com.synectiks.cms.utils.JPASearchRepository;

/**
 * Spring Data repository for the PaymentRemainder entity.
 */
@Repository
public interface PaymentRemainderRepository
		extends JPASearchRepository<PaymentRemainder, Long> {

}
