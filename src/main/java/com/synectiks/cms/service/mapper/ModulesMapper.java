package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.ModulesDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Modules} and its DTO {@link ModulesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModulesMapper extends EntityMapper<ModulesDTO, Modules> {



    default Modules fromId(Long id) {
        if (id == null) {
            return null;
        }
        Modules modules = new Modules();
        modules.setId(id);
        return modules;
    }
}
