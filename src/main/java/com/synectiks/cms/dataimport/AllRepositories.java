package com.synectiks.cms.dataimport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.synectiks.cms.repository.AcademicYearRepository;
import com.synectiks.cms.repository.BankAccountsRepository;
import com.synectiks.cms.repository.BatchRepository;
import com.synectiks.cms.repository.BranchRepository;
import com.synectiks.cms.repository.CityRepository;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.CountryRepository;
import com.synectiks.cms.repository.DepartmentRepository;
import com.synectiks.cms.repository.HolidayRepository;
import com.synectiks.cms.repository.LegalEntityRepository;
import com.synectiks.cms.repository.SectionRepository;
import com.synectiks.cms.repository.StateRepository;
import com.synectiks.cms.repository.StudentRepository;
import com.synectiks.cms.repository.TermRepository;

@Component
public class AllRepositories {

	@Autowired
	private ApplicationContext context;
	
	@Autowired
	public CollegeRepository collegeRepository;
	
	@Autowired
	public AcademicYearRepository academicYearRepository;
	
	@Autowired
	public HolidayRepository holidayRepository;
	
	@Autowired
	public TermRepository termRepository;
	
	@Autowired
	public LegalEntityRepository legalEntityRepository;
	
	@Autowired
	public BankAccountsRepository bankAccountsRepository;
	
	@Autowired
	public StudentRepository studentRepository;
	
	@Autowired
	public CountryRepository countryRepository;
	
	@Autowired
	public StateRepository stateRepository;
	
	@Autowired
	public CityRepository cityRepository;
	
	@Autowired
	public BranchRepository branchRepository;
	
	@Autowired
	public DepartmentRepository departmentRepository;
	
	@Autowired
	public BatchRepository batchRepository;
	
	@Autowired
	public SectionRepository sectionRepository;
	
	
	public <T> JpaRepository findRepository (Class cls) {
		JpaRepository<Class, Long> repository;
		
		if(cls.getSimpleName().equalsIgnoreCase("country")) {
			return (CountryRepository) context.getBean(CountryRepository.class);
		}else if(cls.getSimpleName().equalsIgnoreCase("state")) {
			return (StateRepository) context.getBean(StateRepository.class);
		}else if(cls.getSimpleName().equalsIgnoreCase("city")) {
			return (CityRepository) context.getBean(CityRepository.class);
		}else if(cls.getSimpleName().equalsIgnoreCase("college")) {
			return (CollegeRepository) context.getBean(CollegeRepository.class);
		}else if(cls.getSimpleName().equalsIgnoreCase("Branch")) {
			return (BranchRepository) context.getBean(BranchRepository.class);
		}
		
		return null;
	}
	
	
}
