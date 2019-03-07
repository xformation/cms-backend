package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.DocumentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Documents and its DTO DocumentsDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface DocumentsMapper extends EntityMapper<DocumentsDTO, Documents> {

    @Mapping(source = "student.id", target = "studentId")
    DocumentsDTO toDto(Documents documents);

    @Mapping(source = "studentId", target = "student")
    Documents toEntity(DocumentsDTO documentsDTO);

    default Documents fromId(Long id) {
        if (id == null) {
            return null;
        }
        Documents documents = new Documents();
        documents.setId(id);
        return documents;
    }
}
