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
import com.synectiks.cms.domain.City;
import com.synectiks.cms.domain.State;


public class CityDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	

	private AllRepositories allRepositories;
	private String sheetName;
	
	public CityDataLoader(String sheetName, AllRepositories allRepositories) {
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;;
	}
	
	@Override
	public void saveCmsData(ReadableWorkbook wb) {
		logger.debug(String.format("Saving %s data started.", this.getClass().getName()));

		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<City> list = new ArrayList<>();
				rows.forEach(row -> {

					if (list.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.cityRepository.saveAll(list);
						list.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						City city = this.getCity(row);
						if(!allRepositories.cityRepository.exists(Example.of(city))) {
							list.add(city);
						}

					}
				});
				// Save remaining items
				allRepositories.cityRepository.saveAll(list);
				list.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate city sheet rows ", e);
		}
		logger.debug(String.format("Saving %s data completed..", this.getClass().getName()));
	}
	
	private City getCity(Row row) {
		City city = new City();
		city.setCityName((row.getCellAsString(0).orElse(null)));
		city.setCityCode((row.getCellAsString(1).orElse(null)));
		city.setStdCode((row.getCellAsString(2).orElse(null)));
		State state = new State();
		state.setStateName((row.getCellAsString(3).orElse(null)));
		Optional<State> st = allRepositories.stateRepository.findOne(Example.of(state));
		city.setState(st.isPresent() ? st.get() : null);
		return city;
	}
	
}
