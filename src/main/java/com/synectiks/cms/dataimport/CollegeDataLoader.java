package com.synectiks.cms.dataimport;

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
import com.synectiks.cms.domain.College;
import com.synectiks.cms.repository.CollegeRepository;


public class CollegeDataLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	private CollegeRepository collegeRepository;
	
	public CollegeDataLoader(CollegeRepository collegeRepository) {
		this.collegeRepository = collegeRepository;
	}
	
	@Override
	public void saveCmsData(ReadableWorkbook wb) {
		logger.debug("Saving college data started.");

		Sheet sheet = wb.findSheet("college").orElse(null);
		try {
			try (Stream<Row> rows = sheet.openStream()) {
				List<College> collegeList = new ArrayList<>();
				rows.forEach(row -> {

					if (collegeList.size() == CmsConstants.BATCH_SIZE) {
						this.collegeRepository.saveAll(collegeList);
						collegeList.clear();
					}

					// Skip first header row
					if (row.getRowNum() > 1) {
						College college = this.getCollege(row);
						if(!this.collegeRepository.exists(Example.of(college))) {
							collegeList.add(college);
						}

					}
				});
				// Save remaining items
				this.collegeRepository.saveAll(collegeList);
				collegeList.clear();
			}
		} catch (Exception e) {
			logger.error("Failed to iterate college sheet rows ", e);
		}
		logger.debug("Saving college data completed.");
	}
	
	private College getCollege(Row row) {
		College college = new College();
		college.setShortName(row.getCellAsString(0).orElse(null));
		college.setLogoPath(row.getCellAsString(1).orElse(null));
		college.setBackgroundImagePath(row.getCellAsString(2).orElse(null));
		college.setInstructionInformation(row.getCellAsString(3).orElse(null));
		college.setLogoFileName(row.getCellAsString(4).orElse(null));
		college.setBackgroundImageFileName(row.getCellAsString(5).orElse(null));
		return college;
		
	}
	
}
