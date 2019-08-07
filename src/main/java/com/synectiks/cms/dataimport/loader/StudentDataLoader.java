//package com.synectiks.cms.dataimport.loader;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//import org.dhatim.fastexcel.reader.ReadableWorkbook;
//import org.dhatim.fastexcel.reader.Row;
//import org.dhatim.fastexcel.reader.Sheet;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Example;
//
//import com.synectiks.cms.constant.CmsConstants;
//import com.synectiks.cms.dataimport.DataLoader;
//import com.synectiks.cms.domain.Student;
//import com.synectiks.cms.repository.StudentRepository;
//
//public class StudentDataLoader extends DataLoader {
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	
//	private StudentRepository studentRepository;
//	private String sheetName ;
//	public StudentDataLoader(StudentRepository studentRepository, String sheetName) {
//		this.studentRepository = studentRepository;
//		this.sheetName = sheetName;
//	}
//	
//	@Override
//	public void saveCmsData(ReadableWorkbook wb) {
//		logger.debug("Saving student data started.");
//
//		Sheet sheet = wb.findSheet(this.sheetName).orElse(null);
//		try {
//			try (Stream<Row> rows = sheet.openStream()) {
//				List<Student> studentList = new ArrayList<>();
//				rows.forEach(row -> {
//
//					if (studentList.size() == CmsConstants.BATCH_SIZE) {
//						this.studentRepository.saveAll(studentList);
//						studentList.clear();
//					}
//
//					// Skip first header row
//					if (row.getRowNum() > 1) {
//						Student student = this.getStudent(row);
//						if(!this.studentRepository.exists(Example.of(student))) {
//							studentList.add(student);
//						}
//
//					}
//				});
//				// Save remaining items
//				this.studentRepository.saveAll(studentList);
//				studentList.clear();
//			}
//		} catch (Exception e) {
//			logger.error("Failed to iterate student sheet rows ", e);
//		}
//		logger.debug("Saving student data completed.");
//	}
//	
//	private Student getStudent(Row row) {
//		Student student = new Student();
//		return student;
//		
//	}
//	
//}
