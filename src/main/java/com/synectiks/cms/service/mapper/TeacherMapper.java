package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.TeacherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Teacher} and its DTO {@link TeacherDTO}.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, BranchMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "branch.id", target = "branchId")
    TeacherDTO toDto(Teacher teacher);

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "branchId", target = "branch")
    Teacher toEntity(TeacherDTO teacherDTO);

    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
