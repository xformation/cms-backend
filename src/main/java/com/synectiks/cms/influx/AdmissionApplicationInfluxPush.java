package com.synectiks.cms.influx;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.synectiks.cms.business.service.CommonService;
import com.synectiks.cms.config.ApplicationProperties;
import com.synectiks.cms.domain.AcademicYear;
import com.synectiks.cms.domain.AdmissionApplication;
import com.synectiks.cms.domain.CmsAdmissionApplicationVo;
import com.synectiks.cms.domain.enumeration.AdmissionStatusEnum;
import com.synectiks.cms.repository.AdmissionApplicationRepository;
import com.synectiks.cms.service.util.CommonUtil;

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
	
	@Autowired
	private AdmissionApplicationRepository admissionApplicationRepository;
	
	@Override
	public void pushData() throws ParseException, Exception {
		AcademicYear ay = this.commonService.getActiveAcademicYear();
		AdmissionApplication aa = new AdmissionApplication();
		aa.setAcademicyear(ay);
		List<AdmissionApplication> list = this.admissionApplicationRepository.findAll(Example.of(aa));
		influxDB = influxDbDataSource.getInfluxDatabase();
		Point point = null;
		int declined =0;
		int inProgress=0;
		int accepted=0;
		List<CmsAdmissionApplicationVo> ls = new ArrayList<>();
		CmsAdmissionApplicationVo vo = new CmsAdmissionApplicationVo();
		
		for(AdmissionApplication ap: list) {
			if(!ap.getBranch().getId().equals(vo.getBranchId())){
				vo = CommonUtil.createCopyProperties(ap, CmsAdmissionApplicationVo.class);
				if(AdmissionStatusEnum.ACCEPTED.equals(ap.getAdmissionStatus())) {
					++accepted;
				}else if(AdmissionStatusEnum.INPROCESS.equals(ap.getAdmissionStatus())) {
					++inProgress;
				}else if(AdmissionStatusEnum.DECLINED.equals(ap.getAdmissionStatus())) {
					++declined;
				}
				vo.setTotalAccepted(new Long(accepted));
				vo.setTotalInprocess(new Long(inProgress));
				vo.setTotalDeclined(new Long(declined));
				vo.setBranchId(ap.getBranch().getId());
				ls.add(vo);
			}else {
				if(AdmissionStatusEnum.ACCEPTED.equals(ap.getAdmissionStatus())) {
					++accepted;
				}else if(AdmissionStatusEnum.INPROCESS.equals(ap.getAdmissionStatus())) {
					++inProgress;
				}else if(AdmissionStatusEnum.DECLINED.equals(ap.getAdmissionStatus())) {
					++declined;
				}
				if(vo != null) {
					vo.setTotalAccepted(new Long(accepted));
					vo.setTotalInprocess(new Long(inProgress));
					vo.setTotalDeclined(new Long(declined));
				}
			}
		}
		
		logger.debug("Total admission applications : "+ls.size());
		for(AdmissionStatusEnum ase: AdmissionStatusEnum.values()) {
			for(CmsAdmissionApplicationVo apvo: ls) {
				
				long tm = System.currentTimeMillis();
				String cnt = "0";
				if(ase.name().toString().equals(AdmissionStatusEnum.ACCEPTED.toString())) {
					cnt = String.valueOf(apvo.getTotalAccepted());
				}else if(ase.name().toString().equals(AdmissionStatusEnum.INPROCESS.toString())) {
					cnt = String.valueOf(apvo.getTotalInprocess());
				}else if(ase.name().toString().equals(AdmissionStatusEnum.DECLINED.toString())) {
					cnt = String.valueOf(apvo.getTotalDeclined());
				}
				
				point = Point.measurement("AdmissionApplication")
							.tag("TbranchName", apvo.getBranch().getBranchName())
							.tag("TadmissionStatus", ase.name().toString())
							.tag("TrecordCount", cnt)
							.addField("BranchName", apvo.getBranch().getBranchName())
							.addField("AdmissionStatus", ase.name().toString())
							.addField("RecordCount", cnt)
							.addField("time", tm)
							.build();
				influxDB.write(applicationProperties.getInfluxDb(), "autogen", point);
			}
		}
	
		influxDbDataSource.closeInfluxDatabase(influxDB);
	}
	
	
}
