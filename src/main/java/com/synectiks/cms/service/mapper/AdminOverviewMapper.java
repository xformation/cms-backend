package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.AdminOverviewDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdminOverview and its DTO AdminOverviewDTO.
 */
@Mapper(componentModel = "spring", uses = {DepartmentMapper.class, BatchMapper.class})
public interface AdminOverviewMapper extends EntityMapper<AdminOverviewDTO, AdminOverview> {

    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "batch.id", target = "batchId")
    AdminOverviewDTO toDto(AdminOverview adminOverview);

    @Mapping(source = "departmentId", target = "department")
    @Mapping(source = "batchId", target = "batch")
    AdminOverview toEntity(AdminOverviewDTO adminOverviewDTO);

    default AdminOverview fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdminOverview adminOverview = new AdminOverview();
        adminOverview.setId(id);
        return adminOverview;
    }
}
