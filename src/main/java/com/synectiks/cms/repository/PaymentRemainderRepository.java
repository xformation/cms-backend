package com.synectiks.cms.repository;

import org.springframework.stereotype.Repository;

import com.synectiks.cms.domain.PaymentRemainder;
import com.synectiks.cms.utils.JPASearchRepository;


/**
 * Spring Data  repository for the PaymentRemainder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRemainderRepository extends JPASearchRepository<PaymentRemainder, Long> {

}
