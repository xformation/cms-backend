package com.synectiks.cms.influx;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Pong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;

@Component
public class InfluxDbDataSource {

    private final Logger logger = LoggerFactory.getLogger(InfluxDbDataSource.class);

	@Autowired
	private ApplicationProperties applicationProperties;

	public InfluxDB getInfluxDatabase() {
		InfluxDB influxDB = InfluxDBFactory.connect(applicationProperties.getInfluxDbUrl(), applicationProperties.getInfluxDbUsername(), 
				applicationProperties.getInfluxDbPassword());
		Pong png = null;
		try {
			List<String> dbList = influxDB.describeDatabases();
			if(!dbList.contains(applicationProperties.getInfluxDb())) {
				influxDB.createDatabase(applicationProperties.getInfluxDb());
				logger.debug(String.format("Influx DB: %s created successfully", applicationProperties.getInfluxDb()));
			}
		}catch (Exception e) {
			logger.error("Cannot create influx db : ",e);
			return null;
		}
	    try {
	    	influxDB.setLogLevel(setLogLevel(applicationProperties.getInfluxDbLogLevel()));
	        png = influxDB.ping();
	        logger.info("##################################################################################");
		    logger.info("#  Connected to InfluxDB Version: " + png.getVersion() + " #");
		    logger.info("##################################################################################");
	    } catch (Exception e) {
	        logger.error("Could not connect to Influx db: ",e.getMessage());
	        return null;
	    }
	    return influxDB;
	}
	
	public void dropInfluxDatabase(InfluxDB influxDB) {
		List<String> dbList = influxDB.describeDatabases();
		if(dbList.contains(applicationProperties.getInfluxDb())) {
			influxDB.deleteDatabase(applicationProperties.getInfluxDb());
			logger.debug("Influx database instance deleted successfully.");
		}
	}
	
	public void closeInfluxDatabase(InfluxDB influxDB) {
		influxDB.close();
		logger.debug("Influx database instance closed successfully.");
	}
	
	private InfluxDB.LogLevel setLogLevel(String level) {
		switch (level) {
			case CmsConstants.INFLUXDB_LOG_LEVEL_BASIC:
				return InfluxDB.LogLevel.BASIC;
			case CmsConstants.INFLUXDB_LOG_LEVEL_FULL:
				return InfluxDB.LogLevel.FULL;
			case CmsConstants.INFLUXDB_LOG_LEVEL_HEADERS:
				return InfluxDB.LogLevel.HEADERS;
			default:
				return InfluxDB.LogLevel.NONE;
		}
	}
	
	
}
