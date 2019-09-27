package com.synectiks.cms.dataimport;

import java.util.Map;

import com.synectiks.cms.dataimport.loader.*;
import org.springframework.stereotype.Component;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class DataLoaderFactory {
	
	public DataLoader getLoader(String tableName, AllRepositories allRepositories){  
		
        if(tableName == null){  
        	return null;  
        }  
//        if(tableName.equalsIgnoreCase("STUDENT")) {  
//            return new StudentDataLoader(this.studentRepository, tableName);  
//        }   
        
        if(tableName.equalsIgnoreCase("COUNTRY")) {  
        	return new CountryDataLoader(tableName, allRepositories); 
        }
        if(tableName.equalsIgnoreCase("STATE")) {  
        	return new StateDataLoader(tableName, allRepositories);  
        }
        if(tableName.equalsIgnoreCase("CITY")) {  
        	return new CityDataLoader(tableName, allRepositories); 
        }
        if(tableName.equalsIgnoreCase("COLLEGE")) {  
        	return new CollegeDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("BRANCH")) {  
        	return new BranchDataLoader(tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("AUTHORIZED_SIGNATORY")){
            return new AuthorizedSignatoryLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("ACADEMIC_YEAR")){
            return new AcademicYearLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("DEPARTMENT")){
            return new DepartmentLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("TERM")){
            return new TermDataLoader (tableName, allRepositories);
        }
        if(tableName.equalsIgnoreCase("HOLIDAY")){
            return new HolidayDataLoader (tableName, allRepositories);
        }

        if(tableName.equalsIgnoreCase("BATCH")){
            return new BatchDataLoader (tableName, allRepositories);
        }


        return null;  
	}
	
	public Class getClassName(String table) {
		if(CommonUtil.isNullOrEmpty(table)) return null;
		Map<String, CmsTableWithDomainAndRepositoryMapper> map = CmsConstants.initTableDomainRepositoryMapperMap();
		CmsTableWithDomainAndRepositoryMapper mapper = map.get(table);
		return mapper.getDomain();
	}
	
//	public Class getClassName(String entity) {
//		if(entity.equalsIgnoreCase("COUNTRY")) {  
//        	return Country.class;
//        }
//		if(entity.equalsIgnoreCase("STATE")) {  
//        	return State.class;
//        }
//		if(entity.equalsIgnoreCase("CITY")) {  
//        	return City.class;
//        }
//		if(entity.equalsIgnoreCase("COLLEGE")) {  
//        	return College.class;
//        }
//		if(entity.equalsIgnoreCase("BRANCH")) {  
//        	return Branch.class;
//        }
//		return null;
//	}
}
