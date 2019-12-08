package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.BatchDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Batch and its DTO BatchDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class})
public interface BatchMapper extends EntityMapper<BatchDTO, Batch> {

    @Mapping(source = "department.id", target = "departmentId")
    BatchDTO toDto(Batch batch);

    @Mapping(source = "departmentId", target = "department")
    Batch toEntity(BatchDTO batchDTO);

    default Batch fromId(Long id) {
        if (id == null) {
            return null;
        }
        Batch batch = new Batch();
        batch.setId(id);
        return batch;
    }
}
