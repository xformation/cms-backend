package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentAttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentAttendance and its DTO StudentAttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, LectureMapper.class})
public interface StudentAttendanceMapper extends EntityMapper<StudentAttendanceDTO, StudentAttendance> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "lecture.id", target = "lectureId")
    StudentAttendanceDTO toDto(StudentAttendance studentAttendance);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "lectureId", target = "lecture")
    StudentAttendance toEntity(StudentAttendanceDTO studentAttendanceDTO);

    default StudentAttendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentAttendance studentAttendance = new StudentAttendance();
        studentAttendance.setId(id);
        return studentAttendance;
    }
}
