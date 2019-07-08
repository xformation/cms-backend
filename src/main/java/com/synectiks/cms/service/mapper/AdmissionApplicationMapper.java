package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.AdmissionApplicationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AdmissionApplication and its DTO AdmissionApplicationDTO.
 */
@Mapper(componentModel = "spring", uses = {StudentMapper.class, AcademicYearMapper.class})
public interface AdmissionApplicationMapper extends EntityMapper<AdmissionApplicationDTO, AdmissionApplication> {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "academicyear.id", target = "academicyearId")
    AdmissionApplicationDTO toDto(AdmissionApplication admissionApplication);

    @Mapping(source = "studentId", target = "student")
    @Mapping(source = "academicyearId", target = "academicyear")
    AdmissionApplication toEntity(AdmissionApplicationDTO admissionApplicationDTO);

    default AdmissionApplication fromId(Long id) {
        if (id == null) {
            return null;
        }
        AdmissionApplication admissionApplication = new AdmissionApplication();
        admissionApplication.setId(id);
        return admissionApplication;
    }
}
