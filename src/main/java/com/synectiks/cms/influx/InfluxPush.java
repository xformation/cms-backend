package com.synectiks.cms.influx;

import java.text.ParseException;

public interface InfluxPush {
	public void pushData() throws ParseException, Exception;
}
