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
import com.synectiks.cms.service.util.CommonUtil;


public class StateDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	
	public StateDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		State obj = CommonUtil.createCopyProperties(cls.newInstance(), State.class);
		obj.setStateName((row.getCellAsString(0).orElse(null)));
		obj.setDivisionType((row.getCellAsString(1).orElse(null)));
		obj.setStateCode((row.getCellAsString(2).orElse(null)));
		Country country = new Country();
		country.setCountryName(((row.getCellAsString(3).orElse(null))));
		Optional<Country> ct = allRepositories.findRepository("country").findOne(Example.of(country));
		obj.setCountry(ct.isPresent() ? ct.get() : null);
		return (T)obj;
	}
}
