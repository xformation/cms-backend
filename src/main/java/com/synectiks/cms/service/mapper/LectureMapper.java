package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LectureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Lecture and its DTO LectureDTO.
 */
@Mapper(componentModel = "spring", uses = {AttendanceMasterMapper.class})
public interface LectureMapper extends EntityMapper<LectureDTO, Lecture> {

    @Mapping(source = "attendancemaster.id", target = "attendancemasterId")
    LectureDTO toDto(Lecture lecture);

    @Mapping(source = "attendancemasterId", target = "attendancemaster")
    Lecture toEntity(LectureDTO lectureDTO);

    default Lecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lecture lecture = new Lecture();
        lecture.setId(id);
        return lecture;
    }
}
