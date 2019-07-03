package com.synectiks.cms.dataimport;

import java.io.InputStream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public abstract class DataLoader {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public abstract void saveCmsData(ReadableWorkbook wb);
	
	public void load(MultipartFile f) {
		try (InputStream is = f.getInputStream();
				ReadableWorkbook wb = new ReadableWorkbook(is)) {
			saveCmsData(wb);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
}
