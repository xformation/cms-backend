package com.synectiks.cms.dataimport.loader;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.service.util.CommonUtil;


public class CountryDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CountryDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		Country obj = CommonUtil.createCopyProperties(cls.newInstance(), Country.class);
		obj.setCountryName(row.getCellAsString(0).orElse(null));
		obj.setCountryCode(row.getCellAsString(1).orElse(null));
		obj.setIsdCode(row.getCellAsString(2).orElse(null));
		return (T)obj;
	}
}
