package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.HolidayDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Holiday and its DTO HolidayDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface HolidayMapper extends EntityMapper<HolidayDTO, Holiday> {

    @Mapping(source = "academicyear.id", target = "academicyearId")
    HolidayDTO toDto(Holiday holiday);

    @Mapping(source = "academicyearId", target = "academicyear")
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
