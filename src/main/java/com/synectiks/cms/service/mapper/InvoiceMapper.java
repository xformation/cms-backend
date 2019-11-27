package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.InvoiceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Invoice and its DTO InvoiceDTO.
 */
@Mapper(componentModel = "spring", uses = {FeeCategoryMapper.class, FeeDetailsMapper.class, DueDateMapper.class, PaymentRemainderMapper.class, CollegeMapper.class, BranchMapper.class, StudentMapper.class, AcademicYearMapper.class})
public interface InvoiceMapper extends EntityMapper<InvoiceDTO, Invoice> {

    @Mapping(source = "feeCategory.id", target = "feeCategoryId")
    @Mapping(source = "feeDetails.id", target = "feeDetailsId")
    @Mapping(source = "dueDate.id", target = "dueDateId")
    @Mapping(source = "paymentRemainder.id", target = "paymentRemainderId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "academicYear.id", target = "academicYearId")
    InvoiceDTO toDto(Invoice invoice);

    @Mapping(source = "feeCategoryId", target = "feeCategory")
    @Mapping(source = "feeDetailsId", target = "feeDetails")
    @Mapping(source = "dueDateId", target = "dueDate")
    @Mapping(source = "paymentRemainderId", target = "paymentRemainder")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "academicYearId", target = "academicYear")
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
