package com.synectiks.cms.dataimport.loader;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.commons.entities.cms.College;
import com.synectiks.cms.exceptions.AdditionalCollegeFoundException;
import com.synectiks.cms.exceptions.MandatoryFieldMissingException;
import com.synectiks.cms.service.util.CommonUtil;


public class CollegeDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private boolean isCollegeExist = false;
	private AllRepositories allRepositories;
	private String sheetName;
	public CollegeDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, AdditionalCollegeFoundException, MandatoryFieldMissingException {
//		int count = this.allRepositories.findRepository(cls).findAll().size();
		int count = this.allRepositories.findRepository(this.sheetName).findAll().size();
		String exceptionMsg = "College already exists. This application only supports one college";
		if(count > 0 || this.isCollegeExist) {
			logger.warn(exceptionMsg);
			throw new AdditionalCollegeFoundException(exceptionMsg);
		}
		StringBuilder sb = new StringBuilder();	
		
		College obj = CommonUtil.createCopyProperties(cls.newInstance(), College.class);
		
		String shortName = row.getCellAsString(0).orElse(null);
		if(CommonUtil.isNullOrEmpty(shortName)) {
			sb.append("short_name, ");
			logger.warn("Mandatory field missing. Field name - short_name");
		}else {
			obj.setShortName(shortName);
		}
		obj.setLogoPath(row.getCellAsString(1).orElse(null));
		obj.setBackgroundImagePath(row.getCellAsString(2).orElse(null));
		obj.setInstructionInformation(row.getCellAsString(3).orElse(null));
		obj.setLogoFileName(row.getCellAsString(4).orElse(null));
		obj.setBackgroundImageFileName(row.getCellAsString(5).orElse(null));
		if(sb.length() > 0) {
			String msg = "Field name - ";
			throw new MandatoryFieldMissingException(msg+sb.substring(0, sb.lastIndexOf(",")));
		}
		this.isCollegeExist = true;
		return (T)obj;
		
	}
	
}
