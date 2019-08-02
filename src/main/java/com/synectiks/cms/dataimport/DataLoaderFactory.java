package com.synectiks.cms.dataimport;

import org.springframework.stereotype.Component;

import com.synectiks.cms.dataimport.loader.CityDataLoader;
import com.synectiks.cms.dataimport.loader.CollegeDataLoader;
import com.synectiks.cms.dataimport.loader.CountryDataLoader;
import com.synectiks.cms.dataimport.loader.StateDataLoader;

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
        return null;  
	}
}
