package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.IdCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity IdCard and its DTO IdCardDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface IdCardMapper extends EntityMapper<IdCardDTO, IdCard> {



    default IdCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        IdCard idCard = new IdCard();
        idCard.setId(id);
        return idCard;
    }
}
