package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.TypeOfGradingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeOfGrading and its DTO TypeOfGradingDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeOfGradingMapper extends EntityMapper<TypeOfGradingDTO, TypeOfGrading> {



    default TypeOfGrading fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeOfGrading typeOfGrading = new TypeOfGrading();
        typeOfGrading.setId(id);
        return typeOfGrading;
    }
}
