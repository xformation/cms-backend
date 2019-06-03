package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentFacilityLinkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentFacilityLink and its DTO StudentFacilityLinkDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, FacilityMapper.class})
public interface StudentFacilityLinkMapper extends EntityMapper<StudentFacilityLinkDTO, StudentFacilityLink> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "facility.id", target = "facilityId")
    StudentFacilityLinkDTO toDto(StudentFacilityLink studentFacilityLink);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "facilityId", target = "facility")
    StudentFacilityLink toEntity(StudentFacilityLinkDTO studentFacilityLinkDTO);

    default StudentFacilityLink fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentFacilityLink studentFacilityLink = new StudentFacilityLink();
        studentFacilityLink.setId(id);
        return studentFacilityLink;
    }
}
