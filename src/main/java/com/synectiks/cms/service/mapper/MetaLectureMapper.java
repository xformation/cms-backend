package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.MetaLectureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MetaLecture and its DTO MetaLectureDTO.
 */
@Mapper(componentModel = "spring", uses = {BranchMapper.class, DepartmentMapper.class, SubjectMapper.class, TeacherMapper.class, TermMapper.class, AcademicYearMapper.class, SectionMapper.class, BatchMapper.class})
public interface MetaLectureMapper extends EntityMapper<MetaLectureDTO, MetaLecture> {

    @Mapping(source = "branch.id", target = "branchId")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "term.id", target = "termId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    @Mapping(source = "section.id", target = "sectionId")
    @Mapping(source = "batch.id", target = "batchId")
    MetaLectureDTO toDto(MetaLecture metaLecture);

    @Mapping(source = "branchId", target = "branch")
    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "termId", target = "term")
    @Mapping(source = "academicyearId", target = "academicyear")
    @Mapping(source = "sectionId", target = "section")
    @Mapping(source = "batchId", target = "batch")
    MetaLecture toEntity(MetaLectureDTO metaLectureDTO);

    default MetaLecture fromId(Long id) {
        if (id == null) {
            return null;
        }
        MetaLecture metaLecture = new MetaLecture();
        metaLecture.setId(id);
        return metaLecture;
    }
}
