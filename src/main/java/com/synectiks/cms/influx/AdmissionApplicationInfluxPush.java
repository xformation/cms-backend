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
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.Branch;
import com.synectiks.cms.domain.CmsAdmissionApplicationVo;
import com.synectiks.cms.domain.CmsTempStudentVo;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;

@Component
public class AdmissionApplicationInfluxPush implements InfluxPush {

	private final Logger logger = LoggerFactory.getLogger(AdmissionApplicationInfluxPush.class);
	
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
		List<Branch> branchList = this.commonService.findAllBranch();
		
		for(Branch branch: branchList) {
			List<CmsAdmissionApplicationVo> ls = this.commonService.getAllAdmisionApplications(branch.getId(), ay.getId());
			int accepted=ls.size();
			
			List<CmsTempStudentVo> tempList = this.commonService.getAllTempAdmisionApplications(branch.getId(), ay.getId());
			int inProgress=tempList.size();
			
			for(AdmissionStatusEnum ase: AdmissionStatusEnum.values()) {
					String cnt = "0";
					if(ase.name().toString().equals(AdmissionStatusEnum.ACCEPTED.toString())) {
						cnt = String.valueOf(accepted);
					}else if(ase.name().toString().equals(AdmissionStatusEnum.INPROCESS.toString())) {
						cnt = String.valueOf(inProgress);
					}
					
					point = Point.measurement("AdmissionApplication")
								.tag("TbranchName", branch.getBranchName())
								.tag("TadmissionStatus", ase.name().toString())
								.tag("TrecordCount", cnt)
								.addField("BranchName", branch.getBranchName())
								.addField("AdmissionStatus", ase.name().toString())
								.addField("RecordCount", Integer.parseInt(cnt))
								.addField("time", System.currentTimeMillis())
								.build();
					influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
			}
			
		}
		
		
//		AdmissionApplication aa = new AdmissionApplication();
//		aa.setAcademicyear(ay);
//		List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(Example.of(aa));
		
		
		
		
//		CmsAdmissionApplicationVo vo = new CmsAdmissionApplicationVo();
		
//		for(AdmissionApplication ap: list) {
//			if(!ap.getBranch().getId().equals(vo.getBranchId())){
//				vo = CommonUtil.createCopyProperties(ap, CmsAdmissionApplicationVo.class);
//				if(AdmissionStatusEnum.ACCEPTED.equals(ap.getAdmissionStatus())) {
//					++accepted;
//				}else if(AdmissionStatusEnum.INPROCESS.equals(ap.getAdmissionStatus())) {
//					++inProgress;
//				}else if(AdmissionStatusEnum.DECLINED.equals(ap.getAdmissionStatus())) {
//					++declined;
//				}
//				vo.setTotalAccepted(new Long(accepted));
//				vo.setTotalInprocess(new Long(inProgress));
//				vo.setTotalDeclined(new Long(declined));
//				vo.setBranchId(ap.getBranch().getId());
//				ls.add(vo);
//			}else {
//				if(AdmissionStatusEnum.ACCEPTED.equals(ap.getAdmissionStatus())) {
//					++accepted;
//				}else if(AdmissionStatusEnum.INPROCESS.equals(ap.getAdmissionStatus())) {
//					++inProgress;
//				}else if(AdmissionStatusEnum.DECLINED.equals(ap.getAdmissionStatus())) {
//					++declined;
//				}
//				if(vo != null) {
//					vo.setTotalAccepted(new Long(accepted));
//					vo.setTotalInprocess(new Long(inProgress));
//					vo.setTotalDeclined(new Long(declined));
//				}
//			}
//		}
//		
//		logger.debug("Total admission applications : "+ls.size());
//		for(AdmissionStatusEnum ase: AdmissionStatusEnum.values()) {
//			for(CmsAdmissionApplicationVo apvo: ls) {
//				
//				long tm = System.currentTimeMillis();
//				String cnt = "0";
//				if(ase.name().toString().equals(AdmissionStatusEnum.ACCEPTED.toString())) {
//					cnt = String.valueOf(apvo.getTotalAccepted());
//				}else if(ase.name().toString().equals(AdmissionStatusEnum.INPROCESS.toString())) {
//					cnt = String.valueOf(apvo.getTotalInprocess());
//				}else if(ase.name().toString().equals(AdmissionStatusEnum.DECLINED.toString())) {
//					cnt = String.valueOf(apvo.getTotalDeclined());
//				}
//				
//				point = Point.measurement("AdmissionApplication")
//							.tag("TbranchName", apvo.getBranch().getBranchName())
//							.tag("TadmissionStatus", ase.name().toString())
//							.tag("TrecordCount", cnt)
//							.addField("BranchName", apvo.getBranch().getBranchName())
//							.addField("AdmissionStatus", ase.name().toString())
//							.addField("RecordCount", Integer.parseInt(cnt))
//							.addField("time", tm)
//							.build();
//				influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
//			}
//		}
	
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
	
}
