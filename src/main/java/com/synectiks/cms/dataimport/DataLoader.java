package com.synectiks.cms.dataimport;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.apache.poi.ss.formula.functions.T;
import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.reader.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;
import org.springframework.web.multipart.MultipartFile;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.ExceptionRecord;

public abstract class DataLoader {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	public abstract <T> void saveCmsData(ReadableWorkbook wb, Class<T> cls);
	public abstract  <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException, Exception;
	private AllRepositories allRepositories;
	private String sheetName;
	private String fileName;
	
	public DataLoader(String sheetName, AllRepositories allRepositories) {
		this.sheetName = sheetName;
		this.allRepositories = allRepositories;
	}
	
	public <T> void load(MultipartFile f, Class<T> cls) {
		this.fileName = f != null ? f.getOriginalFilename() : null;
		try (InputStream is = f.getInputStream(); ReadableWorkbook wb = new ReadableWorkbook(is)) {
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
				List<ExceptionRecord> exceptionList = new ArrayList<>();
				StringBuilder sb = new StringBuilder(String.format("\nInvalid records found for table - %s: \n", this.sheetName));
				sb.append("Rows having invalid records\n");
				rows.forEach(row -> {

					if (list.size() == CmsConstants.BATCH_SIZE) {
//						allRepositories.findRepository(cls).saveAll(list);
						allRepositories.findRepository(this.sheetName).saveAll(list);
						list.clear();
					}
					if (exceptionList.size() == CmsConstants.BATCH_SIZE) {
						allRepositories.findRepository("exception_record").saveAll(exceptionList);
						exceptionList.clear();
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
							logger.error("Exception in loading data from excel file :"+e.getMessage(), e);
						} catch(Exception e) {
							ExceptionRecord expObj = getExceptionObject(row, e);
							sb.append(String.format("%s : %s  , %s\n", e.getClass().getSimpleName(), e.getMessage(), row.toString()));
							if(expObj != null) {
								exceptionList.add(expObj);
							}
						}
					}
				});
				// Save remaining items
//				allRepositories.findRepository(cls).saveAll(list);
				allRepositories.findRepository(this.sheetName).saveAll(list);
				list.clear();
				if(exceptionList.size() > 0) {
					logger.warn(sb.toString());
					logger.info("Saving records having exceptions/errors in exception_record table");
					allRepositories.findRepository("exception_record").saveAll(exceptionList);
				}
				exceptionList.clear();
			}
		} catch (Exception e) {
			logger.error(String.format("Failed to iterate %s sheet rows ", this.sheetName), e);
		}
		logger.debug(String.format("Saving %s data completed.", this.sheetName));
	}
	
	private ExceptionRecord getExceptionObject(Row row, Exception exp) {
		ExceptionRecord obj = new ExceptionRecord(); 
		obj.setExceptionSource((this.fileName != null ? "File - "+this.fileName : "") + ", worksheet - "+this.sheetName);
		obj.setExceptionType(exp.getClass().getSimpleName()+" : "+exp.getMessage());
		obj.setExceptionRecord(row.toString().length() > 255 ? row.toString().substring(0, 254) : row.toString());
		obj.setExceptionDate(LocalDate.now());
		return obj;
	}
}
