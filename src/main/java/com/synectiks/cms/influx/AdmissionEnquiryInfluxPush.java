package com.synectiks.cms.influx;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.filter.admissionenquiry.AdmissionEnquiryProcessor;
import com.synectiks.cms.repository.BranchRepository;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;

@Component
public class AdmissionEnquiryInfluxPush implements InfluxPush {

	private final Logger logger = LoggerFactory.getLogger(AdmissionEnquiryInfluxPush.class);

	private InfluxDB influxDB;

	@Autowired
	private InfluxDbDataSource influxDbDataSource;

	@Autowired
	private ApplicationProperties applicationProperties;

	@Autowired
	private CommonService commonService;

	@Autowired
	private BranchRepository branchRepository;

	@Autowired
	private AdmissionEnquiryProcessor admissionEnquiryProcessor;

	@Override
	public void pushData() throws ParseException, Exception {
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;

		AcademicYear ay = this.commonService.getActiveAcademicYear();
		List<Branch> list = this.commonService.findAllBranch();

		for(Branch branch: list) {
			Long totalConverted = this.commonService.getTotalConverted(branch, ay);
			Long totalDeclined = this.commonService.getTotalDeclined(branch, ay);
			Long totalFollowUp = this.commonService.getTotalFollowUp(branch,ay);
			long tm = System.currentTimeMillis();
			point = Point.measurement("AdmissionEnquiry")
						.tag("TbranchName", branch.getBranchName())
						.tag("Tconverted", String.valueOf(totalConverted))
						.tag("Tdeclined", String.valueOf(totalDeclined))
						.tag("Tfollowup", String.valueOf(totalFollowUp))
						.addField("BranchName", branch.getBranchName())
						.addField("Converted", totalConverted)
						.addField("Declined", totalDeclined)
						.addField("Followup", totalFollowUp)
						.addField("time", tm)
						.build();
			influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
		}

		influxDbDataSource.closeInfluxDatabase(influxDB);
	}

}
