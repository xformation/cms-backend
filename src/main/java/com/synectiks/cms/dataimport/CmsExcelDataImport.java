package com.synectiks.cms.dataimport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;


@RestController
@RequestMapping("/api")
public class CmsExcelDataImport {
	private final Logger logger = LoggerFactory.getLogger(CmsExcelDataImport.class);
	
	@Autowired
	DataLoaderFactory dataLoaderFactory;
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsdataimport/{tableName}")
	public void doImport(@RequestParam("file") MultipartFile file, @PathVariable String tableName) {
		
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		if(CommonUtil.isNullOrEmpty(tableName)) {
			throw new RuntimeException("Invalid excel file name: " + fileName);
		}

		if(fileName.contains("..")) {
            throw new RuntimeException("Filename contains invalid path sequence: " + fileName);
        }
		
		if(!fileName.contains(CmsConstants.XLS_FILE_EXTENSION) || !fileName.contains(CmsConstants.XLSX_FILE_EXTENSION)) {
			throw new RuntimeException("Invalid excel file. File extension not found: " + fileName);
		}
		
		DataLoader dataLoader = this.dataLoaderFactory.getLoader(tableName);
		dataLoader.load(file);
	}
	
	
}
