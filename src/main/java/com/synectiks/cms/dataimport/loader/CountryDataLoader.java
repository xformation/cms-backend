package com.synectiks.cms.dataimport.loader;

import java.util.ArrayList;
import java.util.List;
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


public class CountryDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	
	private String sheetName;
	
	public CountryDataLoader(String sheetName, AllRepositories allRepositories) {
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}
	
	@Override
	public void saveCmsData(ReadableWorkbook wb) {
		logger.debug(String.format("Saving %s data started.", this.getClass().getName()));

		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<Country> countryList = new ArrayList<>();
				rows.forEach(row -> {

					if (countryList.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.countryRepository.saveAll(countryList);
						countryList.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						Country college = this.getCountry(row);
						if(!allRepositories.countryRepository.exists(Example.of(college))) {
							countryList.add(college);
						}

					}
				});
				// Save remaining items
				allRepositories.countryRepository.saveAll(countryList);
				countryList.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate country sheet rows ", e);
		}
		logger.debug(String.format("Saving %s data completed..", this.getClass().getName()));
	}
	
	private Country getCountry(Row row) {
		Country country = new Country();
		country.setCountryName(row.getCellAsString(0).orElse(null));
		country.setCountryCode(row.getCellAsString(1).orElse(null));
		country.setIsdCode(row.getCellAsString(2).orElse(null));
		return country;
		
	}
	
}
