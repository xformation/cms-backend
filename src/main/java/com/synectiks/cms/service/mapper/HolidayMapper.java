package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.HolidayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Holiday and its DTO HolidayDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface HolidayMapper extends EntityMapper<HolidayDTO, Holiday> {

    @Mapping(source = "academicYear.id", target = "academicYearId")
    HolidayDTO toDto(Holiday holiday);

    @Mapping(source = "academicYearId", target = "academicYear")
    Holiday toEntity(HolidayDTO holidayDTO);

    default Holiday fromId(Long id) {
        if (id == null) {
            return null;
        }
        Holiday holiday = new Holiday();
        holiday.setId(id);
        return holiday;
    }
}
