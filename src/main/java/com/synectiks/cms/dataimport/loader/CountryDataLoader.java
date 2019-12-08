package com.synectiks.cms.dataimport.loader;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.entities.Country;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;

public class CountryDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public CountryDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, MandatoryFieldMissingException {
		StringBuilder sb = new StringBuilder();	
		Country obj = CommonUtil.createCopyProperties(cls.newInstance(), Country.class);
		
		String countryName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(countryName)) {
			sb.append("country_name, ");
			logger.warn("Mandatory field missing. Field name - country_name");
		}else {
			obj.setCountryName(countryName);
		}
		
		obj.setCountryCode(row.getCellAsString(1).orElse(null));
		obj.setIsdCode(row.getCellAsString(2).orElse(null));
		if(sb.length() > 0) {
			String msg = "Field name - ";
			throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
		}
		return (T)obj;
	}
}
