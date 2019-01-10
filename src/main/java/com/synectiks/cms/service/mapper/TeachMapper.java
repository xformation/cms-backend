package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.TeachDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teach and its DTO TeachDTO.
 */
@Mapper(componentModel = "spring", uses = {TeacherMapper.class, SubjectMapper.class})
public interface TeachMapper extends EntityMapper<TeachDTO, Teach> {

    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(source = "subject.id", target = "subjectId")
    TeachDTO toDto(Teach teach);

    @Mapping(source = "teacherId", target = "teacher")
    @Mapping(source = "subjectId", target = "subject")
    Teach toEntity(TeachDTO teachDTO);

    default Teach fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teach teach = new Teach();
        teach.setId(id);
        return teach;
    }
}
