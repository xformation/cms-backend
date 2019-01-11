package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, SectionMapper.class, BranchMapper.class, DepartmentMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "department.id", target = "departmentId")
    StudentDTO toDto(Student student);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "departmentId", target = "department")
    Student toEntity(StudentDTO studentDTO);

    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
