package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.TeachDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teach and its DTO TeachDTO.
 */
@Mapper(componentModel = "spring", uses = {SubjectMapper.class, TeacherMapper.class})
public interface TeachMapper extends EntityMapper<TeachDTO, Teach> {

    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "teacher.id", target = "teacherId")
    TeachDTO toDto(Teach teach);

    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "teacherId", target = "teacher")
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
