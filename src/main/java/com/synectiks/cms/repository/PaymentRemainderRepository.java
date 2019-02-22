package com.synectiks.cms.repository;

import com.synectiks.cms.domain.PaymentRemainder;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PaymentRemainder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentRemainderRepository extends JpaRepository<PaymentRemainder, Long> {

}
