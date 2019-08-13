package com.synectiks.cms.dataimport.loader;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.College;
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
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
//		int count = this.allRepositories.findRepository(cls).findAll().size();
		int count = this.allRepositories.findRepository(this.sheetName).findAll().size();
		
		if(count > 0) {
			logger.warn("College already exists. This application only supports one college");
			return null;
		}
		if(this.isCollegeExist) {
			logger.warn("One first entry will be considard. Other entries will be ingnored.");
			return null;
		}
			
		College obj = CommonUtil.createCopyProperties(cls.newInstance(), College.class);
		obj.setShortName(row.getCellAsString(0).orElse(null));
		obj.setLogoPath(row.getCellAsString(1).orElse(null));
		obj.setBackgroundImagePath(row.getCellAsString(2).orElse(null));
		obj.setInstructionInformation(row.getCellAsString(3).orElse(null));
		obj.setLogoFileName(row.getCellAsString(4).orElse(null));
		obj.setBackgroundImageFileName(row.getCellAsString(5).orElse(null));
		this.isCollegeExist = true;
		return (T)obj;
		
	}
	
}
