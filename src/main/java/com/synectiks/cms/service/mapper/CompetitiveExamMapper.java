package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.CompetitiveExamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity CompetitiveExam and its DTO CompetitiveExamDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface CompetitiveExamMapper extends EntityMapper<CompetitiveExamDTO, CompetitiveExam> {

    @Mapping(source = "student.id", target = "studentId")
    CompetitiveExamDTO toDto(CompetitiveExam competitiveExam);

    @Mapping(source = "studentId", target = "student")
    CompetitiveExam toEntity(CompetitiveExamDTO competitiveExamDTO);

    default CompetitiveExam fromId(Long id) {
        if (id == null) {
            return null;
        }
        CompetitiveExam competitiveExam = new CompetitiveExam();
        competitiveExam.setId(id);
        return competitiveExam;
    }
}
