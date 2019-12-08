package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.AdminAttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdminAttendance and its DTO AdminAttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {LectureMapper.class, BranchMapper.class, CollegeMapper.class, DepartmentMapper.class, AcademicYearMapper.class, SectionMapper.class, StudentMapper.class})
public interface AdminAttendanceMapper extends EntityMapper<AdminAttendanceDTO, AdminAttendance> {

    @Mapping(source = "lecture.id", target = "lectureId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "student.id", target = "studentId")
    AdminAttendanceDTO toDto(AdminAttendance adminAttendance);

    @Mapping(source = "lectureId", target = "lecture")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "academicyearId", target = "academicyear")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "studentId", target = "student")
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
