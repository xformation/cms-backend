package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.LibraryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Library and its DTO LibraryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LibraryMapper extends EntityMapper<LibraryDTO, Library> {



    default Library fromId(Long id) {
        if (id == null) {
            return null;
        }
        Library library = new Library();
        library.setId(id);
        return library;
    }
}
