package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentAttendanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentAttendance and its DTO StudentAttendanceDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentYearMapper.class, DepartmentsMapper.class, SubjectMapper.class, SemesterMapper.class, SectionMapper.class, PeriodsMapper.class, StudentMapper.class})
public interface StudentAttendanceMapper extends EntityMapper<StudentAttendanceDTO, StudentAttendance> {

    @Mapping(source = "studentYear.id", target = "studentYearId")
    @Mapping(source = "departments.id", target = "departmentsId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "semester.id", target = "semesterId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "periods.id", target = "periodsId")
    @Mapping(source = "student.id", target = "studentId")
    StudentAttendanceDTO toDto(StudentAttendance studentAttendance);

    @Mapping(source = "studentYearId", target = "studentYear")
    @Mapping(source = "departmentsId", target = "departments")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "semesterId", target = "semester")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "periodsId", target = "periods")
    @Mapping(source = "studentId", target = "student")
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
