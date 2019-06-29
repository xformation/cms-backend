package com.synectiks.cms.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.StudentRepository;

@Component
public class DataLoaderFactory {
	
	@Autowired
	private CollegeRepository collegeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	public DataLoader getLoader(String tableName){  
		
        if(tableName == null){  
        	return null;  
        }  
        if(tableName.equalsIgnoreCase("STUDENT")) {  
            return new StudentDataLoader(this.studentRepository);  
        }   
        if(tableName.equalsIgnoreCase("COLLEGE")) {  
            return new CollegeDataLoader(this.collegeRepository);  
        }
        return null;  
	}
}
