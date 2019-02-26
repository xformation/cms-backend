package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.DocumentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Documents and its DTO DocumentsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DocumentsMapper extends EntityMapper<DocumentsDTO, Documents> {



    default Documents fromId(Long id) {
        if (id == null) {
            return null;
        }
        Documents documents = new Documents();
        documents.setId(id);
        return documents;
    }
}
