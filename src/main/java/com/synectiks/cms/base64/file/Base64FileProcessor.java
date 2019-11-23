package com.synectiks.cms.base64.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.commons.entities.cms.QueryResult;
import com.synectiks.cms.exceptions.BranchIdNotFoundException;
import com.synectiks.cms.exceptions.FileNameNotFoundException;
import com.synectiks.cms.exceptions.FilePathNotFoundException;
import com.synectiks.cms.exceptions.UnSupportedFileTypeException;
import com.synectiks.cms.service.util.CommonUtil;

@Component
public class Base64FileProcessor  {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String createBase64StringFromFile(String filePath) {
		logger.info(String.format("Start creating base64 encoded string from file: %s",filePath));
		String base64EncodedString = null;
		String fPath = Paths.get("", filePath).toString();
		File file = new File(filePath);
		if(!file.exists()) {
			return null;
		}
		try (FileInputStream ins = new FileInputStream(file)) {
			byte binaryData[] = new byte[(int) file.length()];
			ins.read(binaryData);
			base64EncodedString = Base64.getEncoder().encodeToString(binaryData);
		} catch (Exception e ) {
			logger.error(String.format("Exception while reading the file: %s ",filePath),e);
		} 
		logger.info("Base64 encoded string created successfully from file.");
		return base64EncodedString;
	}
	
	public QueryResult createFileFromBase64String(String base64EncodeStr, String filePath, String fileName, String branchId, String fileExt) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
		logger.info("Start creating file from base64 encoded string.");
		
		if(filePath == null) {
			throw new FilePathNotFoundException("File path not provided to save uploaded file");
		}
		if(fileName == null) {
			fileName = getDefaultValues();
		}
		
		String completeFilePath = filePath;
		if(branchId != null) {
			completeFilePath = filePath+File.separator+CmsConstants.BRANCH_ID_PLACEHOLDER_REPLACER+branchId;
		}
		
		File f = new File(completeFilePath);
		if(!f.exists()) {
			f.mkdirs();
		}
		
		QueryResult res = new QueryResult();
		res.setStatusCode(0);
		res.setStatusDesc("File created successfully from base64 encoded string.");
		
		String[] strings = null;
		String extension = null;
		String base64Str = null;
		if(CommonUtil.isNullOrEmpty(fileExt)) {
			strings = base64EncodeStr.split(",");
			extension =	  getFileExtensionFromBase64Srting(strings[0]);
			base64Str = strings[1];
		}else {
			extension = fileExt;
			base64Str = base64EncodeStr;
		}
		
		String absFileName = completeFilePath+File.separator+fileName+"."+extension;
		
		byte[] data = DatatypeConverter.parseBase64Binary(base64Str);
		f = new File(absFileName);
		
		logger.debug("Starting file creation from base64 encoded string.");
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(f))) {
		    outputStream.write(data);
		} catch (IOException e) {
		    logger.error("Exception while creating file from base64 encoded string: ",e);
		    res.setStatusCode(1);
			res.setStatusDesc("Exception while creating file from base64 encoded string.");
		}
		logger.info("File created successfully from base64 encoded string.");
		return res;
	}
	
	private String getDefaultValues() {
		String systemGeneratedFileName = RandomStringUtils.random(12, true, true);
		logger.debug("Random file name : "+systemGeneratedFileName);
		return systemGeneratedFileName;
	}
	
	public String getFileExtensionFromFileName(String fileName) {
		return fileName.substring(fileName.lastIndexOf(".")+1);
	}
	
	public String getFileExtensionFromBase64Srting(String str) {
		String extension = null;
		switch (str) {
		    case "data:image/jpeg;base64":
		        extension = "jpeg";
		        break;
		    case "data:image/jpg;base64":
		        extension = "jpg";
		        break;
		    case "data:image/png;base64":
		        extension = "png";
		        break;
		    case "data:text/plain;base64":
		        extension = "txt";
		        break;
		    case "data:application/pdf;base64":
		        extension = "pdf";
		        break;
		    case "data:application/vnd.openxmlformats-officedocument.wordprocessingml.document;base64":
		        extension = "docx";
		        break;
		    case "data:application/msword;base64":
		        extension = "doc";
		        break;
		    case "data:application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;base64":
		        extension = "xlsx";
		        break;
		    case "data:application/vnd.ms-excel;base64":
		        extension = "xls";
		        break;
		    default:
		        throw new UnSupportedFileTypeException(str + " file type not supported for file upload");
		        
		}
		logger.debug(String.format("File format of uploaded file is %s", extension));
		return extension;
	}

	
}
