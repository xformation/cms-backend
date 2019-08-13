package com.synectiks.cms.dataimport;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class AllRepositories {

	@Autowired
	private ApplicationContext context;
	
	public <T> JpaRepository findRepository (String table) {
		if(CommonUtil.isNullOrEmpty(table)) return null;
		Map<String, CmsTableWithDomainAndRepositoryMapper> map = CmsConstants.initTableDomainRepositoryMapperMap();
		CmsTableWithDomainAndRepositoryMapper mapper = map.get(table);
		return (JpaRepository)context.getBean(mapper.getRepository());
	}
	
//	public <T> JpaRepository findRepository (Class cls) {
////		JpaRepository<Class, Long> repository;
//		
//		if(cls.getSimpleName().equalsIgnoreCase("country")) {
//			return (CountryRepository) context.getBean(CountryRepository.class);
//		}else if(cls.getSimpleName().equalsIgnoreCase("state")) {
//			return (StateRepository) context.getBean(StateRepository.class);
//		}else if(cls.getSimpleName().equalsIgnoreCase("city")) {
//			return (CityRepository) context.getBean(CityRepository.class);
//		}else if(cls.getSimpleName().equalsIgnoreCase("college")) {
//			return (CollegeRepository) context.getBean(CollegeRepository.class);
//		}else if(cls.getSimpleName().equalsIgnoreCase("Branch")) {
//			return (BranchRepository) context.getBean(BranchRepository.class);
//		}
//		
//		return null;
//	}
	
	
}
