package com.synectiks.cms.dataimport.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.domain.Country;
import com.synectiks.cms.domain.State;


public class StateDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	
	private String sheetName;
	
	public StateDataLoader(String sheetName, AllRepositories allRepositories) {
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}

	
	@Override
	public void saveCmsData(ReadableWorkbook wb) {
		logger.debug(String.format("Saving %s data started.", this.getClass().getName()));

		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<State> stateList = new ArrayList<>();
				rows.forEach(row -> {

					if (stateList.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.stateRepository.saveAll(stateList);
						stateList.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						State state = this.getState(row);
						if(!allRepositories.stateRepository.exists(Example.of(state))) {
							stateList.add(state);
						}

					}
				});
				// Save remaining items
				allRepositories.stateRepository.saveAll(stateList);
				stateList.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate state sheet rows ", e);
		}
		logger.debug(String.format("Saving %s data completed..", this.getClass().getName()));
	}
	
	private State getState(Row row) {
		State state = new State();
		state.setStateName((row.getCellAsString(0).orElse(null)));
		state.setDivisionType((row.getCellAsString(1).orElse(null)));
		state.setStateCode((row.getCellAsString(2).orElse(null)));
		Country country = new Country();
		country.setCountryName(((row.getCellAsString(3).orElse(null))));
		Optional<Country> ct = allRepositories.countryRepository.findOne(Example.of(country));
		state.setCountry(ct.isPresent() ? ct.get() : null);
		return state;
	}
	
}
