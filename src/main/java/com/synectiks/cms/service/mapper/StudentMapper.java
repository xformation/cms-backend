package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, BatchMapper.class, SectionMapper.class, BranchMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "branch.id", target = "branchId")
    StudentDTO toDto(Student student);

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "branchId", target = "branch")
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
