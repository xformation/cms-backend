package com.synectiks.cms.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentRepository;

@Component
public class AllRepositories {

	@Autowired
	public CollegeRepository collegeRepository;
	
	@Autowired
	public StudentRepository studentRepository;
	
	@Autowired
	public CountryRepository countryRepository;
	
	@Autowired
	public StateRepository stateRepository;
	
	@Autowired
	public CityRepository cityRepository;
	
}
