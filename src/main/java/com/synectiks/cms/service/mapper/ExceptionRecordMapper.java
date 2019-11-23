package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.ExceptionRecordDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ExceptionRecord} and its DTO {@link ExceptionRecordDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ExceptionRecordMapper extends EntityMapper<ExceptionRecordDTO, ExceptionRecord> {



    default ExceptionRecord fromId(Long id) {
        if (id == null) {
            return null;
        }
        ExceptionRecord exceptionRecord = new ExceptionRecord();
        exceptionRecord.setId(id);
        return exceptionRecord;
    }
}
