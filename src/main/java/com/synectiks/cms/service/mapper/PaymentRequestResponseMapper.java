package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.PaymentRequestResponseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PaymentRequestResponse} and its DTO {@link PaymentRequestResponseDTO}.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, AcademicYearMapper.class})
public interface PaymentRequestResponseMapper extends EntityMapper<PaymentRequestResponseDTO, PaymentRequestResponse> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "academicYear.id", target = "academicYearId")
    PaymentRequestResponseDTO toDto(PaymentRequestResponse paymentRequestResponse);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "academicYearId", target = "academicYear")
    PaymentRequestResponse toEntity(PaymentRequestResponseDTO paymentRequestResponseDTO);

    default PaymentRequestResponse fromId(Long id) {
        if (id == null) {
            return null;
        }
        PaymentRequestResponse paymentRequestResponse = new PaymentRequestResponse();
        paymentRequestResponse.setId(id);
        return paymentRequestResponse;
    }
}
