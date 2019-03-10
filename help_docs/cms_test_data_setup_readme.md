CMS test data setup

Introduction:

	CMS test data setup utility creates test master data like college, branch, department, batch, section, student, teacher, lecture schedule 
	and student attendance as per the given lecture schedules. This data can be used for demo and testing purposes.

Functional information:

	Test data needs to be provided in an excel file cms_test_data_setup.xlsx. 
	file location: /src/test/cms_test_data_setup.xlsx

	cms_test_data_setup.xlsx file contains three sheets. 
		cmstestdata: This contains test data
		state: This contains the list of Indian states. This data gets stored in state table.
		city: This contains list of all the cities state wise. This data gets stored in city table.
	
	minimum set of information like academic year (e.g. 2018, 2019 etc.), term (e.g. 01-01-2019 to 30-06- 2019 (dd-mm-yyyy)), college name, 
	branch name, department name (e.g. Computer Science, Civil Engineering, Business Administration etc.), batch name (e.g. FIRSTYEAR, 
	SECONDYEAR etc.), section (e.g. A, B, C etc.), student name, subject name, teacher name and lecture schedule day wise (monday, tuesday, 
	wednesday, thursday, friday, saturday) and student attendance (term_wide) should be provided in cmstestdata sheet of cms_test_data_setup.xlsx 
	file and system will generate complete data set with all mappings (e.g. teacher and subject etc.) automatically.

Technical information:

	Rest service is provided to create test data.
	
		Rest URL: http://<server-ip>:<port>/api/cmstestdata/create (e.g. http://localhost:8080/api/cmstestdata/create)
		Java class: com.synectiks.cms.automated.testdatasetup.CmsAutomatedTestDataSetupProcessor

		CmsAutomatedTestDataSetupProcessor.java is a spring based rest controller.
	
	Apache POI api is used to read excel file. Following dependency added in pom.xml
	
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi</artifactId>
			<version>3.17</version>
		</dependency>
		<dependency>
			<groupId>org.apache.poi</groupId>
			<artifactId>poi-ooxml</artifactId>
			<version>3.17</version>
		</dependency>
		
		