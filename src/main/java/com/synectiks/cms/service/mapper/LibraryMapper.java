package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.LibraryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Library and its DTO LibraryDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, SubjectMapper.class})
public interface LibraryMapper extends EntityMapper<LibraryDTO, Library> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "subject.id", target = "subjectId")
    LibraryDTO toDto(Library library);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "subjectId", target = "subject")
    Library toEntity(LibraryDTO libraryDTO);

    default Library fromId(Long id) {
        if (id == null) {
            return null;
        }
        Library library = new Library();
        library.setId(id);
        return library;
    }
}
