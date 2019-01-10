package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.CourseOfferDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CourseOffer and its DTO CourseOfferDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, DepartmentMapper.class, SubjectMapper.class})
public interface CourseOfferMapper extends EntityMapper<CourseOfferDTO, CourseOffer> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "subject.id", target = "subjectId")
    CourseOfferDTO toDto(CourseOffer courseOffer);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "subjectId", target = "subject")
    CourseOffer toEntity(CourseOfferDTO courseOfferDTO);

    default CourseOffer fromId(Long id) {
        if (id == null) {
            return null;
        }
        CourseOffer courseOffer = new CourseOffer();
        courseOffer.setId(id);
        return courseOffer;
    }
}
