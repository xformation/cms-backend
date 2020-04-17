package com.synectiks.cms.influx;

import java.text.ParseException;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsAdmissionEnquiryVo;

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

	@Override
	public void pushData() throws ParseException, Exception {
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;

		AcademicYear ay = this.commonService.getActiveAcademicYear();
		List<Branch> list = this.commonService.findAllBranch();

		for(Branch branch: list) {
			List<CmsAdmissionEnquiryVo> listConverted = this.commonService.getAdmissionEnqueryListByStatus(branch, ay, CmsConstants.STATUS_CONVERTED_TO_ADMISSION);
			List<CmsAdmissionEnquiryVo> listDeclined = this.commonService.getAdmissionEnqueryListByStatus(branch, ay, CmsConstants.STATUS_DECLINED);
			List<CmsAdmissionEnquiryVo> listFollowUp = this.commonService.getAdmissionEnqueryListByStatus(branch, ay, CmsConstants.STATUS_FOLLOWUP);
			
			long tm = System.currentTimeMillis();
			point = Point.measurement("AdmissionEnquiry")
						.tag("TbranchName", branch.getBranchName())
						.tag("Tconverted", String.valueOf(listConverted.size()))
						.tag("Tdeclined", String.valueOf(listDeclined.size()))
						.tag("Tfollowup", String.valueOf(listFollowUp.size()))
						.addField("BranchName", branch.getBranchName())
						.addField("Converted", listConverted.size())
						.addField("Declined", listDeclined.size())
						.addField("Followup", listFollowUp.size())
						.addField("time", tm)
						.build();
			influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
		}

		influxDbDataSource.closeInfluxDatabase(influxDB);
	}

}
