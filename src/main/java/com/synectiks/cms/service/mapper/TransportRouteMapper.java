package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.TransportRouteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TransportRoute and its DTO TransportRouteDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TransportRouteMapper extends EntityMapper<TransportRouteDTO, TransportRoute> {



    default TransportRoute fromId(Long id) {
        if (id == null) {
            return null;
        }
        TransportRoute transportRoute = new TransportRoute();
        transportRoute.setId(id);
        return transportRoute;
    }
}
