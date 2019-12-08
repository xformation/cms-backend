package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.ReportsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Reports and its DTO ReportsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReportsMapper extends EntityMapper<ReportsDTO, Reports> {



    default Reports fromId(Long id) {
        if (id == null) {
            return null;
        }
        Reports reports = new Reports();
        reports.setId(id);
        return reports;
    }
}
