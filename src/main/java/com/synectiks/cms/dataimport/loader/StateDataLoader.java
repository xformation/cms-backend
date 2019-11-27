package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;


public class StateDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	
	public StateDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException {
		StringBuilder sb = new StringBuilder();
		State obj = CommonUtil.createCopyProperties(cls.newInstance(), State.class);
		
		String stateName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(stateName)) {
			sb.append("state_name, ");
			logger.warn("Mandatory field missing. Field name - state_name");
		}else {
			obj.setStateName(stateName);
		}
		
		obj.setDivisionType((row.getCellAsString(1).orElse(null)));
		obj.setStateCode((row.getCellAsString(2).orElse(null)));
		
		String countryName = row.getCellAsString(3).orElse(null);
		if(CommonUtil.isNullOrEmpty(countryName)) {
			sb.append("country_name, ");
			logger.warn("Mandatory field missing. Field name - country_name");
		}else {
			Country country = new Country();
			country.setCountryName(countryName);
			Optional<Country> ct = this.allRepositories.findRepository("country").findOne(Example.of(country));
			obj.setCountry(ct.isPresent() ? ct.get() : null);
		}
		if(sb.length() > 0) {
			String msg = "Field name - ";
			throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
		}
		return (T)obj;
	}
}
