package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AttendanceMasterDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AttendanceMaster and its DTO AttendanceMasterDTO.
 */
@Mapper(componentModel = "spring", uses = {TeachMapper.class, BatchMapper.class, SectionMapper.class})
public interface AttendanceMasterMapper extends EntityMapper<AttendanceMasterDTO, AttendanceMaster> {

    @Mapping(source = "teach.id", target = "teachId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "section.id", target = "sectionId")
    AttendanceMasterDTO toDto(AttendanceMaster attendanceMaster);

    @Mapping(source = "teachId", target = "teach")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "sectionId", target = "section")
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
