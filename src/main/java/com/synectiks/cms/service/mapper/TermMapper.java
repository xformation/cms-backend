package com.synectiks.cms.service.mapper;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.service.dto.TermDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Term and its DTO TermDTO.
 */
@Mapper(componentModel = "spring", uses = {AcademicYearMapper.class})
public interface TermMapper extends EntityMapper<TermDTO, Term> {

    @Mapping(source = "academicYear.id", target = "academicYearId")
    TermDTO toDto(Term term);

    @Mapping(source = "academicYearId", target = "academicYear")
    Term toEntity(TermDTO termDTO);

    default Term fromId(Long id) {
        if (id == null) {
            return null;
        }
        Term term = new Term();
        term.setId(id);
        return term;
    }
}
