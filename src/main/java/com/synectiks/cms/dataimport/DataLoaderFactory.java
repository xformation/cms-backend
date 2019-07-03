package com.synectiks.cms.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.dataimport.loader.CollegeDataLoader;
import com.synectiks.cms.dataimport.loader.CountryDataLoader;
import com.synectiks.cms.dataimport.loader.StudentDataLoader;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.StudentRepository;

@Component
public class DataLoaderFactory {
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	public DataLoader getLoader(String tableName){  
		
        if(tableName == null){  
        	return null;  
        }  
//        if(tableName.equalsIgnoreCase("STUDENT")) {  
//            return new StudentDataLoader(this.studentRepository, tableName);  
//        }   
        if(tableName.equalsIgnoreCase("COLLEGE")) {  
            return new CollegeDataLoader(this.collegeRepository, tableName);  
        }
        if(tableName.equalsIgnoreCase("COUNTRY")) {  
            return new CountryDataLoader(this.countryRepository, tableName);  
        }
        return null;  
	}
}
