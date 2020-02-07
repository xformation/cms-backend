package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.DueDateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DueDate and its DTO DueDateDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DueDateMapper extends EntityMapper<DueDateDTO, DueDate> {



    default DueDate fromId(Long id) {
        if (id == null) {
            return null;
        }
        DueDate dueDate = new DueDate();
        dueDate.setId(id);
        return dueDate;
    }
}
