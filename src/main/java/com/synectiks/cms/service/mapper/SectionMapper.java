package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.SectionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Section and its DTO SectionDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class})
public interface SectionMapper extends EntityMapper<SectionDTO, Section> {

    @Mapping(source = "batch.id", target = "batchId")
    SectionDTO toDto(Section section);

    @Mapping(source = "batchId", target = "batch")
    Section toEntity(SectionDTO sectionDTO);

    default Section fromId(Long id) {
        if (id == null) {
            return null;
        }
        Section section = new Section();
        section.setId(id);
        return section;
    }
}
