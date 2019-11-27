package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.NotificationsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Notifications} and its DTO {@link NotificationsDTO}.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface NotificationsMapper extends EntityMapper<NotificationsDTO, Notifications> {

    @Mapping(source = "academicYear.id", target = "academicYearId")
    NotificationsDTO toDto(Notifications notifications);

    @Mapping(source = "academicYearId", target = "academicYear")
    Notifications toEntity(NotificationsDTO notificationsDTO);

    default Notifications fromId(Long id) {
        if (id == null) {
            return null;
        }
        Notifications notifications = new Notifications();
        notifications.setId(id);
        return notifications;
    }
}
