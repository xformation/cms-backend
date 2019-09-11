package com.synectiks.cms.influx;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CmsInvoiceService;
import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsAdmissionApplicationVo;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.repository.BranchRepository;

@Component
public class FeeInfluxPush implements InfluxPush {

	private final Logger logger = LoggerFactory.getLogger(FeeInfluxPush.class);
	
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
	private CmsInvoiceService cmsInvoiceService;
	
	@Override
	public void pushData() throws ParseException, Exception {
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;
		
		AcademicYear ay = this.commonService.getActiveAcademicYear();
		List<Branch> list = this.branchRepository.findAll();
		
		for(Branch branch: list) {
			Long amtCollected = this.cmsInvoiceService.getTotalCollectedAmount(branch, ay, null);
			Long amtPending = this.cmsInvoiceService.getTotalPendingAmount(branch, ay, null);
			Long amtOverDue = this.cmsInvoiceService.getTotalOverDueAmount(branch, ay);
			long tm = System.currentTimeMillis();
			point = Point.measurement("Fee")
						.tag("TbranchName", branch.getBranchName())
						.tag("Tcollected", String.valueOf(amtCollected))
						.tag("Tpending", String.valueOf(amtPending))
						.tag("Toverdue", String.valueOf(amtOverDue))
						.addField("BranchName", branch.getBranchName())
						.addField("Collected", String.valueOf(amtCollected))
						.addField("Pending", String.valueOf(amtPending))
						.addField("Overdue", String.valueOf(amtOverDue))
						.addField("time", tm)
						.build();
			influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
		}
	
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
}
