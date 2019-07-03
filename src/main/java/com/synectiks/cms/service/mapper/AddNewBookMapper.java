package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AddNewBookDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AddNewBook and its DTO AddNewBookDTO.
 */
@Mapper(componentModel = "spring", uses = {BatchMapper.class, SubjectMapper.class, DepartmentMapper.class})
public interface AddNewBookMapper extends EntityMapper<AddNewBookDTO, AddNewBook> {

    @Mapping(source = "batch.id", target = "batchId")
    @Mapping(source = "subject.id", target = "subjectId")
    @Mapping(source = "department.id", target = "departmentId")
    AddNewBookDTO toDto(AddNewBook addNewBook);

    @Mapping(source = "batchId", target = "batch")
    @Mapping(source = "subjectId", target = "subject")
    @Mapping(source = "departmentId", target = "department")
    AddNewBook toEntity(AddNewBookDTO addNewBookDTO);

    default AddNewBook fromId(Long id) {
        if (id == null) {
            return null;
        }
        AddNewBook addNewBook = new AddNewBook();
        addNewBook.setId(id);
        return addNewBook;
    }
}
