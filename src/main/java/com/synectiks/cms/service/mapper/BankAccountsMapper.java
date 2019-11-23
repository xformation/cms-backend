package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.BankAccountsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity BankAccounts and its DTO BankAccountsDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, CollegeMapper.class})
public interface BankAccountsMapper extends EntityMapper<BankAccountsDTO, BankAccounts> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    BankAccountsDTO toDto(BankAccounts bankAccounts);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    BankAccounts toEntity(BankAccountsDTO bankAccountsDTO);

    default BankAccounts fromId(Long id) {
        if (id == null) {
            return null;
        }
        BankAccounts bankAccounts = new BankAccounts();
        bankAccounts.setId(id);
        return bankAccounts;
    }
}
