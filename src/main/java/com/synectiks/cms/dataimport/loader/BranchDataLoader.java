package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.service.util.CommonUtil;


public class BranchDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public BranchDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		Branch obj = CommonUtil.createCopyProperties(cls.newInstance(), Branch.class);
		obj.setBranchName(row.getCellAsString(0).orElse(null));
		obj.setAddress1(row.getCellAsString(1).orElse(null));
		obj.setAddress2(row.getCellAsString(2).orElse(null));
		obj.setBranchHead(row.getCellAsString(3).orElse(null));
		String collegeName = row.getCellAsString(4).orElse(null);
		if(!CommonUtil.isNullOrEmpty(collegeName)) {
			College college = new College();
			college.setShortName(collegeName);
			Optional<College> c = this.allRepositories.findRepository("college").findOne(Example.of(college));
			obj.setCollege(c.isPresent() ? c.get() : null);
		}
		String cityName = row.getCellAsString(5).orElse(null);
		if(!CommonUtil.isNullOrEmpty(cityName)) {
			City city = new City();
			city.setCityName(cityName);
			Optional<City> c = this.allRepositories.findRepository("city").findOne(Example.of(city));
			obj.setCity(c.isPresent() ? c.get() : null);
		}
		String stateName = row.getCellAsString(6).orElse(null);
		if(!CommonUtil.isNullOrEmpty(stateName)) {
			State state = new State();
			state.setStateName(stateName);
			Optional<State> c = this.allRepositories.findRepository("state").findOne(Example.of(state));
			obj.setState(c.isPresent() ? c.get() : null);
		}
		return (T)obj;
	}
	
}
