package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.PeriodsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Periods and its DTO PeriodsDTO.
 */
@Mapper(componentModel = "spring", uses = {SectionMapper.class})
public interface PeriodsMapper extends EntityMapper<PeriodsDTO, Periods> {

    @Mapping(source = "section.id", target = "sectionId")
    PeriodsDTO toDto(Periods periods);

    @Mapping(source = "sectionId", target = "section")
    Periods toEntity(PeriodsDTO periodsDTO);

    default Periods fromId(Long id) {
        if (id == null) {
            return null;
        }
        Periods periods = new Periods();
        periods.setId(id);
        return periods;
    }
}
