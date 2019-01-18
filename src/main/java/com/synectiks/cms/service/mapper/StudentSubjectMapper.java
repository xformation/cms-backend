package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.StudentSubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentSubject and its DTO StudentSubjectDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, SubjectMapper.class})
public interface StudentSubjectMapper extends EntityMapper<StudentSubjectDTO, StudentSubject> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "subject.id", target = "subjectId")
    StudentSubjectDTO toDto(StudentSubject studentSubject);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "subjectId", target = "subject")
    StudentSubject toEntity(StudentSubjectDTO studentSubjectDTO);

    default StudentSubject fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentSubject studentSubject = new StudentSubject();
        studentSubject.setId(id);
        return studentSubject;
    }
}
