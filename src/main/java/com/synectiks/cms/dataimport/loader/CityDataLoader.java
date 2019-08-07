package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.State;
import com.synectiks.cms.service.util.CommonUtil;


public class CityDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AllRepositories allRepositories;
	
	public CityDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		City obj = CommonUtil.createCopyProperties(cls.newInstance(), City.class);
		obj.setCityName((row.getCellAsString(0).orElse(null)));
		obj.setCityCode((row.getCellAsString(1).orElse(null)));
		obj.setStdCode((row.getCellAsString(2).orElse(null)));
		State state = new State();
		state.setStateName((row.getCellAsString(3).orElse(null)));
		Optional<State> st = allRepositories.stateRepository.findOne(Example.of(state));
		obj.setState(st.isPresent() ? st.get() : null);
		return (T)obj;
	}
	
}
