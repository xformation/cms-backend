package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.InvoiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Invoice and its DTO InvoiceDTO.
 */
@Mapper(componentModel = "spring", uses = {FeeCategoryMapper.class, FeeDetailsMapper.class, DueDateMapper.class, PaymentRemainderMapper.class, StudentMapper.class})
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {

    @Mapping(source = "feeCategory.id", target = "feeCategoryId")
    @Mapping(source = "feeDetails.id", target = "feeDetailsId")
    @Mapping(source = "dueDate.id", target = "dueDateId")
    @Mapping(source = "paymentRemainder.id", target = "paymentRemainderId")
    @Mapping(source = "student.id", target = "studentId")
    InvoiceDTO toDto(Invoice invoice);

    @Mapping(source = "feeCategoryId", target = "feeCategory")
    @Mapping(source = "feeDetailsId", target = "feeDetails")
    @Mapping(source = "dueDateId", target = "dueDate")
    @Mapping(source = "paymentRemainderId", target = "paymentRemainder")
    @Mapping(source = "studentId", target = "student")
    Invoice toEntity(InvoiceDTO invoiceDTO);

    default Invoice fromId(Long id) {
        if (id == null) {
            return null;
        }
        Invoice invoice = new Invoice();
        invoice.setId(id);
        return invoice;
    }
}
