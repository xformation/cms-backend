1. Run the sql file on influx command prompt to create database and user

	open command prompt and type follwoing command
	cd C:\influxdb\influxdb-1.0.0-1
	influx -import -path=C:\mycode\cms-backend\src\model\influx_ddl.sql

2.	influx Database name, URL, username, password, log_level are given in application-dev.yml file
	database, username and password should match to the one given in influx_ddl.sql file
	
3.	There is a rest controller to insert data in influx db from a specific tables. URL is as below:
	http://localhost:8080/api/cmsinfluxpush/student_attendance
	
	in the above URL, we have passed student_attendance table name. This way we can insert data in influx database for any specific entity.
	
	we need to add code for that entity in cms application. for student_attendance, we have StudentAttendanceInfluxPush.java

4.	influx related code is assembled in com.synectiks.cms.influx package 

5. 	once data is written in influx db from java api, we can query the influx db to see this data
		
	5.1	open command prompt
	5.2	change to influx directory like ( cd C:\influxdb\influxdb-1.0.0-1)
	5.3	run influx.ext by typing influx at command prompt
	5.4	type following command to connect to cmsdb or whatever db 
		use cmsdb
	5.5	check your measurement that you have created using JAVA API. In student_attendance case, measurement is StudentAttendance. Type below command
		show StudentAttendance
	5.6 Now query the measurement with the select query as below
		select * from StudentAttendance
		

REFERENCE URL
1. https://www.baeldung.com/java-influxdb
2. http://www.mastertheboss.com/jboss-server/jboss-monitoring/using-influxdb-from-a-java-enterprise-application
3. https://github.com/influxdata/influxdb-java/blob/master/src/test/java/org/influxdb/InfluxDBTest.java		
		
	
	
	