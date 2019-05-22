package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.TypeOfGradingDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TypeOfGrading and its DTO TypeOfGradingDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicExamSettingMapper.class})
public interface TypeOfGradingMapper extends EntityMapper<TypeOfGradingDTO, TypeOfGrading> {

    @Mapping(source = "academicExamSetting.id", target = "academicExamSettingId")
    TypeOfGradingDTO toDto(TypeOfGrading typeOfGrading);

    @Mapping(source = "academicExamSettingId", target = "academicExamSetting")
    TypeOfGrading toEntity(TypeOfGradingDTO typeOfGradingDTO);

    default TypeOfGrading fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeOfGrading typeOfGrading = new TypeOfGrading();
        typeOfGrading.setId(id);
        return typeOfGrading;
    }
}
