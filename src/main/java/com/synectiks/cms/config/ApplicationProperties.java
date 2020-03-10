package com.synectiks.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Cms.
 * <p>
 * Properties are configured in the application.yml file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

	private String influxDbUrl;
	private String influxDb;
	private String influxDbUsername;
	private String influxDbPassword;
	private String influxDbLogLevel;
	private String secSrvUrl;
	private String preferenceSrvUrl;
    private String kafkaUrl;
    private String indexEventFireUrl;

	public String getInfluxDbUrl() {
		return influxDbUrl;
	}
	public void setInfluxDbUrl(String influxDbUrl) {
		this.influxDbUrl = influxDbUrl;
	}
	public String getInfluxDb() {
		return influxDb;
	}
	public void setInfluxDb(String influxDb) {
		this.influxDb = influxDb;
	}
	public String getInfluxDbUsername() {
		return influxDbUsername;
	}
	public void setInfluxDbUsername(String influxDbUsername) {
		this.influxDbUsername = influxDbUsername;
	}
	public String getInfluxDbPassword() {
		return influxDbPassword;
	}
	public void setInfluxDbPassword(String influxDbPassword) {
		this.influxDbPassword = influxDbPassword;
	}
	public String getInfluxDbLogLevel() {
		return influxDbLogLevel;
	}
	public void setInfluxDbLogLevel(String influxDbLogLevel) {
		this.influxDbLogLevel = influxDbLogLevel;
	}
	public String getSecSrvUrl() {
		return secSrvUrl;
	}
	public void setSecSrvUrl(String secSrvUrl) {
		this.secSrvUrl = secSrvUrl;
	}
	public String getPreferenceSrvUrl() {
		return preferenceSrvUrl;
	}
	public void setPreferenceSrvUrl(String preferenceSrvUrl) {
		this.preferenceSrvUrl = preferenceSrvUrl;
	}

    public String getKafkaUrl() {
        return kafkaUrl;
    }

    public void setKafkaUrl(String kafkaUrl) {
        this.kafkaUrl = kafkaUrl;
    }

    public String getIndexEventFireUrl() {
        return indexEventFireUrl;
    }

    public void setIndexEventFireUrl(String indexEventFireUrl) {
        this.indexEventFireUrl = indexEventFireUrl;
    }

}
