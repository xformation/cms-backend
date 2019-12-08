package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.PaymentRemainderDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity PaymentRemainder and its DTO PaymentRemainderDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, BranchMapper.class})
public interface PaymentRemainderMapper extends EntityMapper<PaymentRemainderDTO, PaymentRemainder> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    PaymentRemainderDTO toDto(PaymentRemainder paymentRemainder);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    PaymentRemainder toEntity(PaymentRemainderDTO paymentRemainderDTO);

    default PaymentRemainder fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentRemainder paymentRemainder = new PaymentRemainder();
        paymentRemainder.setId(id);
        return paymentRemainder;
    }
}
