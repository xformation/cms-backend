package com.synectiks.cms.dataimport;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.util.HeaderUtil;


@RestController
@RequestMapping("/api")
public class CmsExcelDataImportRestController {
	private final Logger logger = LoggerFactory.getLogger(CmsExcelDataImportRestController.class);
	
	@Autowired
	private DataLoaderFactory dataLoaderFactory;
	
	@Autowired
	private AllRepositories allRepositories;
	
	private String [] allEntities = 
		{		"country","state","city",
				"academic_year","holiday","term",
				"college","branch","department",
				"batch","section", "teacher", 
				"attendance_master", "student"
		};
	
	@RequestMapping(method = RequestMethod.POST, value = "/cmsdataimport/{tableName}")
	public ResponseEntity<QueryResult> doImport(@RequestParam("file") MultipartFile file, @PathVariable String tableName) throws URISyntaxException {
		String msg = "Data successfully imported for entity - "+tableName;
		QueryResult result = new QueryResult();
		result.setStatusCode(0);
		result.setStatusDesc(msg);
		
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
		
		if("ALL".equalsIgnoreCase(tableName)){
			for(int i=0; i<allEntities.length; i++) {
				DataLoader dataLoader = this.dataLoaderFactory.getLoader(allEntities[i], this.allRepositories);
				if(dataLoader == null) {
					msg = "Application does not support data import for entity - "+allEntities[i];
					logger.warn(msg);
				}
				try {
					dataLoader.load(file,this.dataLoaderFactory.getClassName(allEntities[i]));
				}catch(Exception e) {
					msg = "Due to some error data import failed for entity - "+allEntities[i];
					result.setStatusCode(1);
					logger.error(msg, e);
				}
			}
//			for(String entity: CmsConstants.tabelName) {
//				try {
//					if(!"ALL".equalsIgnoreCase(entity)) {
//						DataLoader dataLoader = this.dataLoaderFactory.getLoader(entity, this.allRepositories);
//						if(dataLoader == null) {
//							logger.warn("Application does not support data import for entity - "+entity);
//						}
//						dataLoader.load(file, this.dataLoaderFactory.getClassName(entity));
//						
//					}
//				}catch(Exception e) {
//					logger.error("Data import failed for entiry : "+entity);
//				}
//			}
		}else {
			DataLoader dataLoader = this.dataLoaderFactory.getLoader(tableName, this.allRepositories);
			if(dataLoader == null) {
				msg = "Application does not support data import for entity - "+tableName;
				logger.warn(msg);
			}
			try {
				dataLoader.load(file,this.dataLoaderFactory.getClassName(tableName));
			}catch(Exception e) {
				msg = "Due to some error data import failed for entity - "+tableName;
				result.setStatusCode(1);
				logger.error(msg, e);
			}
		}
		return ResponseEntity.created(new URI("/api/cmsdataimport/"+tableName))
	            .headers(HeaderUtil.createEntityCreationAlert(tableName, msg))
	            .body(result);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/cmsdataimport")
	public List<String> getTableLilst(){
		return CmsConstants.tabelName;
	}
}
