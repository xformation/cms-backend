package com.synectiks.cms.dataimport;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import com.synectiks.cms.constant.CmsConstants;

public abstract class DataLoader {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	public abstract <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls);
	public abstract  <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException;
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public DataLoader(String sheetName, AllRepositories allRepositories) {
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}
	
	public <T> void load(MultipartFile f, Class<T> cls) {
		try (InputStream is = f.getInputStream();
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsData(wb, cls);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls) {
		logger.debug(String.format("Saving %s data started.",this.sheetName));
		
		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
		try {
			T instance = cls.newInstance();
			try (Stream<Row> rows = sheet.openStream()) {
				List<T> list = new ArrayList<>();
				rows.forEach(row -> {

					if (list.size() == CmsConstants.BATCH_SIZE) {
//						allRepositories.findRepository(cls).saveAll(list);
						allRepositories.findRepository(this.sheetName).saveAll(list);
						list.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						try {
							T obj = getObject(row, cls);
							if(obj != null) {
//								if(!allRepositories.findRepository(cls).exists(Example.of(obj))) {
//									list.add(obj);
//								}
								if(!allRepositories.findRepository(this.sheetName).exists(Example.of(obj))) {
									list.add(obj);
								}
							}
							
						} catch (InstantiationException | IllegalAccessException e) {
							// TODO Auto-generated catch block
							logger.error("Exception in loading data from excel file :"+e.getMessage(), e);
						} 
						

					}
				});
				// Save remaining items
//				allRepositories.findRepository(cls).saveAll(list);
				allRepositories.findRepository(this.sheetName).saveAll(list);
				list.clear();
			}
		} catch (Exception e) {
			logger.error(String.format("Failed to iterate %s sheet rows ", this.sheetName), e);
		}
		logger.debug(String.format("Saving %s data completed.", this.sheetName));
	}
	
}
