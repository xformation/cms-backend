package com.synectiks.cms.service.mapper;

import com.synectiks.commons.entities.cms.*;
import com.synectiks.cms.service.dto.CurrencyDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Currency and its DTO CurrencyDTO.
 */
@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface CurrencyMapper extends EntityMapper<CurrencyDTO, Currency> {

    @Mapping(source = "country.id", target = "countryId")
    CurrencyDTO toDto(Currency currency);

    @Mapping(source = "countryId", target = "country")
    Currency toEntity(CurrencyDTO currencyDTO);

    default Currency fromId(Long id) {
        if (id == null) {
            return null;
        }
        Currency currency = new Currency();
        currency.setId(id);
        return currency;
    }
}
