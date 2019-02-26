package com.synectiks.cms.base64.file;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.exceptions.BranchIdNotFoundException;
import com.synectiks.cms.exceptions.FileNameNotFoundException;
import com.synectiks.cms.exceptions.FilePathNotFoundException;
import com.synectiks.cms.exceptions.UnSupportedFileTypeException;

@Component
public class Base64FileProcessor  {

	private final static Logger logger = LoggerFactory.getLogger(Class.class);
	
	public String createBase64StringFromFile(String filePath) {
		logger.info(String.format("Start creating base64 encoded string from file: %s",filePath));
		String base64EncodedString = null;
		File file = new File(filePath);
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
	
	public QueryResult createFileFromBase64String(String base64EncodeStr, String filePath, String fileName, String branchId) throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
		logger.info("Start creating file from base64 encoded string.");
		
		validateFileParameters(filePath, fileName, branchId);
		
		QueryResult res = new QueryResult();
		res.setStatusCode(0);
		res.setStatusDesc("File created successfully from base64 encoded string.");
		
		String[] strings = base64EncodeStr.split(",");
		String extension =	  getFileExtensionFromBase64Srting(strings[0]);
		
		byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
		File file = new File(generateFileName(filePath, fileName, branchId, extension));
		
		logger.debug("Starting file creation from base64 encoded string.");
		try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
		    outputStream.write(data);
		} catch (IOException e) {
		    logger.error("Exception while creating file from base64 encoded string: ",e);
		    res.setStatusCode(1);
			res.setStatusDesc("Exception while creating file from base64 encoded string.");
		}
		logger.info("File created successfully from base64 encoded string.");
		return res;
	}

	
	private String generateFileName(String filePath, String fileName, String branchId, String extension) {
		File f = new File(filePath+File.separator+branchId);
		if(!f.exists()) {
			f.mkdirs();
		}
		return filePath+File.separator+branchId+File.separator+fileName+"."+extension;
	}

	private void validateFileParameters(String filePath, String fileName, String branchId)
			throws FilePathNotFoundException, FileNameNotFoundException, BranchIdNotFoundException {
		
		if(filePath == null) {
			throw new FilePathNotFoundException("File path not provided to save uploaded file");
		}
		if(!new File(filePath).exists()) {
			throw new FilePathNotFoundException(String.format("File path does not exist: %s",filePath));
		}
		if(fileName == null) {
			throw new FileNameNotFoundException("File name not provided to save uploaded file");
		}
		if(branchId == null) {
			throw new BranchIdNotFoundException("Branch id not provided to save uploaded file");
		}
	}
	
	private String getFileExtensionFromBase64Srting(String str) {
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