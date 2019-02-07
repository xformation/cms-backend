package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.SignatoryLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity SignatoryLink and its DTO SignatoryLinkDTO.
 */
@Mapper(componentModel = "spring", uses = {AuthorizedSignatoryMapper.class, LegalEntityMapper.class})
public interface SignatoryLinkMapper extends EntityMapper<SignatoryLinkDTO, SignatoryLink> {

    @Mapping(source = "authorizedSignatory.id", target = "authorizedSignatoryId")
    @Mapping(source = "legalEntity.id", target = "legalEntityId")
    SignatoryLinkDTO toDto(SignatoryLink signatoryLink);

    @Mapping(source = "authorizedSignatoryId", target = "authorizedSignatory")
    @Mapping(source = "legalEntityId", target = "legalEntity")
    SignatoryLink toEntity(SignatoryLinkDTO signatoryLinkDTO);

    default SignatoryLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        SignatoryLink signatoryLink = new SignatoryLink();
        signatoryLink.setId(id);
        return signatoryLink;
    }
}
