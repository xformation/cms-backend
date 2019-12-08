package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.AuthorizedSignatoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuthorizedSignatory and its DTO AuthorizedSignatoryDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, CollegeMapper.class})
public interface AuthorizedSignatoryMapper extends EntityMapper<AuthorizedSignatoryDTO, AuthorizedSignatory> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    AuthorizedSignatoryDTO toDto(AuthorizedSignatory authorizedSignatory);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    AuthorizedSignatory toEntity(AuthorizedSignatoryDTO authorizedSignatoryDTO);

    default AuthorizedSignatory fromId(Long id) {
        if (id == null) {
            return null;
        }
        AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setId(id);
        return authorizedSignatory;
    }
}
