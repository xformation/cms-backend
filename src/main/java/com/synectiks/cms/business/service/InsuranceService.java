package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.domain.enumeration.TypeOfInsurance;
import com.synectiks.cms.graphql.types.Insurance.AddInsuranceInput;
import com.synectiks.cms.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.cms.repository.InsuranceRepository;
import com.synectiks.cms.repository.VehicleRepository;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Component
public class InsuranceService {
            private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        InsuranceRepository insuranceRepository;

        public List<CmsInsuranceVo> getCmsInsuranceList(TypeOfInsurance typeOfInsurance) {
            Insurance insurance = new Insurance();

            List<Insurance> list = this.insuranceRepository.findAll(Example.of(insurance));
            List<CmsInsuranceVo> ls = changeInsuranceToCmsInsuranceList(list);
            Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
            return ls;
        }

        public List<Insurance> getInsuranceList(TypeOfInsurance typeOfInsurance) {
            Insurance insurance = new  Insurance();

            List<Insurance> list = this.insuranceRepository.findAll(Example.of(insurance));
            Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
            return list;
        }

        public List<CmsInsuranceVo> getInsuranceList(){
            List<Insurance> list = this.insuranceRepository.findAll();
            List<CmsInsuranceVo> ls = changeInsuranceToCmsInsuranceList(list);
            Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
            return ls;
        }

        public CmsInsuranceVo getCmsInsurance(Long id){
            Optional<Insurance> v = this.insuranceRepository.findById(id);
            if(v.isPresent()) {
                CmsInsuranceVo vo = CommonUtil.createCopyProperties(v.get(), CmsInsuranceVo.class);
                convertDatesAndProvideDependencies(v.get(), vo);
                logger.debug("CmsInsurance for given id : "+id+". CmsInsurance object : "+vo);
                return vo;
            }
            logger.debug("Insutarnce object not found for the given id. "+id+". Returning null object");
            return null;
        }
        public Insurance getInsurance(Long id){
            Optional< Insurance> v = this.insuranceRepository.findById(id);
            if(v.isPresent()) {
                return v.get();
            }
            logger.debug("Insurance object not found for the given id. "+id+". Returning null");
            return null;
        }
        private List<CmsInsuranceVo> changeInsuranceToCmsInsuranceList(List<Insurance> list){
            List<CmsInsuranceVo> ls = new ArrayList<>();
            for(Insurance i: list) {
                CmsInsuranceVo vo = CommonUtil.createCopyProperties(i, CmsInsuranceVo.class);
                convertDatesAndProvideDependencies(i, vo);
                ls.add(vo);
            }
            return ls;
        }

        private void convertDatesAndProvideDependencies(Insurance i, CmsInsuranceVo vo) {
            if(i.getDateOfInsurance() != null) {
                vo.setStrDateOfInsurance(DateFormatUtil.changeLocalDateFormat(i.getDateOfInsurance(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                vo.setDateOfInsurance(null);
            }
            if(i.getValidTill() != null) {
                vo.setStrValidTill(DateFormatUtil.changeLocalDateFormat(i.getValidTill(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                vo.setValidTill(null);
            }

        }

        public CmsInsuranceVo addInsurance(AddInsuranceInput cmsInsuranceVo) {
            logger.info("Saving Insurance");
            CmsInsuranceVo vo = null;
            try {
                Insurance insurance = null;
                if (cmsInsuranceVo.getId() == null) {
                    logger.debug("Adding new insurance");
                    insurance = CommonUtil.createCopyProperties(cmsInsuranceVo, Insurance.class);
                } else {
                    logger.debug("Updating existing insurance");
                    insurance = this.insuranceRepository.findById(cmsInsuranceVo.getId()).get();

                    if (cmsInsuranceVo.getInsuranceCompany() != null) {
                        insurance.setInsuranceCompany(cmsInsuranceVo.getInsuranceCompany());
                    }

                    if (cmsInsuranceVo.getTypeOfInsurance() != null) {
                        insurance.setTypeOfInsurance(cmsInsuranceVo.getTypeOfInsurance());
                    }

                    }
                insurance.setDateOfInsurance(cmsInsuranceVo.getStrDateOfInsurance() != null ? DateFormatUtil.convertStringToLocalDate(cmsInsuranceVo.getStrDateOfInsurance(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);




                insurance.setValidTill(cmsInsuranceVo.getStrValidTill() != null ? DateFormatUtil.convertStringToLocalDate(cmsInsuranceVo.getStrValidTill(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
                insurance = this.insuranceRepository.save(insurance);
                vo = CommonUtil.createCopyProperties(insurance, CmsInsuranceVo.class);
                vo.setStrDateOfInsurance(insurance.getDateOfInsurance() != null ? DateFormatUtil.changeLocalDateFormat(insurance.getDateOfInsurance(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
                vo.setStrValidTill(insurance.getValidTill() != null ? DateFormatUtil.changeLocalDateFormat(insurance.getValidTill(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
                vo.setExitCode(0L);
                if (cmsInsuranceVo.getId() == null) {
                    vo.setExitDescription("Insurance is added successfully");
                    logger.debug("Insurance is added successfully");
                } else {
                    vo.setExitDescription("Insurance is updated successfully");
                    logger.debug("Insurance is updated successfully");
                }
            } catch (Exception e) {
                vo = new CmsInsuranceVo();
                vo.setExitCode(1L);
                vo.setExitDescription("Due to some exception, insurance data not be saved");
                logger.error("Insurance save failed. Exception : ", e);
            }
            logger.info("Insurance saved successfully");
            List ls = getInsuranceList();
            vo.setDataList(ls);
            return vo;
        }
    }


