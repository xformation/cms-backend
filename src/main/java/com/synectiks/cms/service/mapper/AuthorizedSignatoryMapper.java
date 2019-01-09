package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AuthorizedSignatoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AuthorizedSignatory and its DTO AuthorizedSignatoryDTO.
 */
@Mapper(componentModel = "spring", uses = {LegalEntityMapper.class})
public interface AuthorizedSignatoryMapper extends EntityMapper<AuthorizedSignatoryDTO, AuthorizedSignatory> {

    @Mapping(source = "legalEntity.id", target = "legalEntityId")
    AuthorizedSignatoryDTO toDto(AuthorizedSignatory authorizedSignatory);

    @Mapping(source = "legalEntityId", target = "legalEntity")
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
