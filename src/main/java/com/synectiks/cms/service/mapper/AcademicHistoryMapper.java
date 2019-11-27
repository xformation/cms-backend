package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicHistoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicHistory and its DTO AcademicHistoryDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface AcademicHistoryMapper extends EntityMapper<AcademicHistoryDTO, AcademicHistory> {

    @Mapping(source = "student.id", target = "studentId")
    AcademicHistoryDTO toDto(AcademicHistory academicHistory);

    @Mapping(source = "studentId", target = "student")
    AcademicHistory toEntity(AcademicHistoryDTO academicHistoryDTO);

    default AcademicHistory fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicHistory academicHistory = new AcademicHistory();
        academicHistory.setId(id);
        return academicHistory;
    }
}
