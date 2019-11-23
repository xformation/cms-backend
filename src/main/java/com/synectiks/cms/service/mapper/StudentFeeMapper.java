package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.StudentFeeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity StudentFee and its DTO StudentFeeDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StudentFeeMapper extends EntityMapper<StudentFeeDTO, StudentFee> {



    default StudentFee fromId(Long id) {
        if (id == null) {
            return null;
        }
        StudentFee studentFee = new StudentFee();
        studentFee.setId(id);
        return studentFee;
    }
}
