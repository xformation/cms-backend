package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.DueDateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DueDate and its DTO DueDateDTO.
 */
@Mapper(componentModel = "spring", uses = {CollegeMapper.class, BranchMapper.class})
public interface DueDateMapper extends EntityMapper<DueDateDTO, DueDate> {

    @Mapping(source = "college.id", target = "collegeId")
    @Mapping(source = "branch.id", target = "branchId")
    DueDateDTO toDto(DueDate dueDate);

    @Mapping(source = "collegeId", target = "college")
    @Mapping(source = "branchId", target = "branch")
    DueDate toEntity(DueDateDTO dueDateDTO);

    default DueDate fromId(Long id) {
        if (id == null) {
            return null;
        }
        DueDate dueDate = new DueDate();
        dueDate.setId(id);
        return dueDate;
    }
}
