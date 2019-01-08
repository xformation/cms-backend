package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AcademicYearDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AcademicYear and its DTO AcademicYearDTO.
 */
@Mapper(componentModel = "spring", uses = {HolidayMapper.class, TermMapper.class})
public interface AcademicYearMapper extends EntityMapper<AcademicYearDTO, AcademicYear> {

    @Mapping(source = "holiday.id", target = "holidayId")
    @Mapping(source = "term.id", target = "termId")
    AcademicYearDTO toDto(AcademicYear academicYear);

    @Mapping(source = "holidayId", target = "holiday")
    @Mapping(source = "termId", target = "term")
    AcademicYear toEntity(AcademicYearDTO academicYearDTO);

    default AcademicYear fromId(Long id) {
        if (id == null) {
            return null;
        }
        AcademicYear academicYear = new AcademicYear();
        academicYear.setId(id);
        return academicYear;
    }
}
