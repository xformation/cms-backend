package com.synectiks.cms.dataimport.loader;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.commons.entities.cms.City;
import com.synectiks.commons.entities.cms.State;
import com.synectiks.cms.service.util.CommonUtil;


public class CityDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private AllRepositories allRepositories;
	private Map<String, Object> DATA_MAP = new HashMap<String, Object>();
	
	public CityDataLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		City obj = CommonUtil.createCopyProperties(cls.newInstance(), City.class);
		obj.setCityName((row.getCellAsString(0).orElse(null)));
		obj.setCityCode((row.getCellAsString(1).orElse(null)));
		String stateName = row.getCellAsString(2).orElse(null);
		State state = null;
		if(!CommonUtil.isNullOrEmpty(stateName)) {
			if(DATA_MAP.get(stateName) != null) {
				state =  (State)DATA_MAP.get(stateName);
			}else {
				state = new State();
				state.setStateName(stateName);
				Optional<State> st = allRepositories.findRepository("state").findOne(Example.of(state));
				if(st.isPresent()) {
					state = st.get() ;
					DATA_MAP.put(stateName, state);
				}
			}
		}
		obj.setState(state);
		obj.setStdCode(state != null ? state.getStateCode() : null);
		return (T)obj;
	}
	
}
