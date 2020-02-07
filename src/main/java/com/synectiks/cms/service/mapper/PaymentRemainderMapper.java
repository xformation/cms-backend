package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.PaymentRemainderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PaymentRemainder and its DTO PaymentRemainderDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PaymentRemainderMapper extends EntityMapper<PaymentRemainderDTO, PaymentRemainder> {



    default PaymentRemainder fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentRemainder paymentRemainder = new PaymentRemainder();
        paymentRemainder.setId(id);
        return paymentRemainder;
    }
}
