package com.synectiks.cms.service.mapper;

import com.synectiks.cms.entities.*;
import com.synectiks.cms.service.dto.DocumentsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Documents and its DTO DocumentsDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, VehicleMapper.class, EmployeeMapper.class, ContractMapper.class})
public interface DocumentsMapper extends EntityMapper<DocumentsDTO, Documents> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "vehicle.id", target = "vehicleId")
    @Mapping(source = "employee.id", target = "employeeId")
    @Mapping(source = "contract.id", target = "contractId")
    DocumentsDTO toDto(Documents documents);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "vehicleId", target = "vehicle")
    @Mapping(source = "employeeId", target = "employee")
    @Mapping(source = "contractId", target = "contract")
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
