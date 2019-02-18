package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdminAttendance and its DTO AdminAttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, LectureMapper.class})
public interface AdminAttendanceMapper extends EntityMapper<AdminAttendanceDTO, AdminAttendance> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "lecture.id", target = "lectureId")
    AdminAttendanceDTO toDto(AdminAttendance adminAttendance);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "lectureId", target = "lecture")
    AdminAttendance toEntity(AdminAttendanceDTO adminAttendanceDTO);

    default AdminAttendance fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdminAttendance adminAttendance = new AdminAttendance();
        adminAttendance.setId(id);
        return adminAttendance;
    }
}
