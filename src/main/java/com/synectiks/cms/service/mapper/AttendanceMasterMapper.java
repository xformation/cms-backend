package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AttendanceMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AttendanceMaster and its DTO AttendanceMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {TeachMapper.class, SectionMapper.class, BatchMapper.class})
public interface AttendanceMasterMapper extends EntityMapper<AttendanceMasterDTO, AttendanceMaster> {

    @Mapping(source = "teach.id", target = "teachId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "batch.id", target = "batchId")
    AttendanceMasterDTO toDto(AttendanceMaster attendanceMaster);

    @Mapping(source = "teachId", target = "teach")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "batchId", target = "batch")
    AttendanceMaster toEntity(AttendanceMasterDTO attendanceMasterDTO);

    default AttendanceMaster fromId(Long id) {
        if (id == null) {
            return null;
        }
        AttendanceMaster attendanceMaster = new AttendanceMaster();
        attendanceMaster.setId(id);
        return attendanceMaster;
    }
}
