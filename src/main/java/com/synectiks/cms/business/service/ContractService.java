package com.synectiks.cms.business.service;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.CmsContractVo;

import com.synectiks.cms.domain.Contract;

import com.synectiks.cms.domain.enumeration.TypeOfOwnerShip;
import com.synectiks.cms.graphql.types.Contract.AddContractInput;
import com.synectiks.cms.repository.ContractRepository;
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
public class ContractService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ContractRepository contractRepository;

    public List<CmsContractVo> getCmsContractList(TypeOfOwnerShip typeOfOwnerShip) {
        Contract contract = new Contract();
        contract.setTypeOfOwnerShip(typeOfOwnerShip);
        List<Contract> list = this.contractRepository.findAll(Example.of(contract));
        List<CmsContractVo> ls = changeContractToCmsContractList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<Contract> getContractList(TypeOfOwnerShip typeOfOwnerShip) {
        Contract contract = new  Contract();
        contract.setTypeOfOwnerShip(typeOfOwnerShip);
        List<Contract> list = this.contractRepository.findAll(Example.of(contract));
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }

    public List<CmsContractVo> getContractList(){
        List<Contract> list = this.contractRepository.findAll();
        List<CmsContractVo> ls = changeContractToCmsContractList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsContractVo getCmsContract(Long id){
        Optional<Contract> c = this.contractRepository.findById(id);
        if(c.isPresent()) {
            CmsContractVo vo = CommonUtil.createCopyProperties(c.get(), CmsContractVo.class);
            convertDatesAndProvideDependencies(c.get(), vo);
            logger.debug("CmsContract for given id : "+id+". CmsContract object : "+vo);
            return vo;
        }
        logger.debug("Contract object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Contract getContract(Long id){
        Optional< Contract> c = this.contractRepository.findById(id);
        if(c.isPresent()) {
            return c.get();
        }
        logger.debug("Contract object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsContractVo> changeContractToCmsContractList(List<Contract> list){
        List<CmsContractVo> ls = new ArrayList<>();
        for(Contract c: list) {
            CmsContractVo vo = CommonUtil.createCopyProperties(c, CmsContractVo.class);
            convertDatesAndProvideDependencies(c, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Contract c, CmsContractVo vo) {
        if (c.getStartDate() != null) {
            vo.setStrStartDate(DateFormatUtil.changeLocalDateFormat(c.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setStartDate(null);

        }

            if(c.getEndDate() != null) {
                vo.setStrEndDate(DateFormatUtil.changeLocalDateFormat(c.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
                vo.setEndDate(null);
            }

        }
    public CmsContractVo addContract(AddContractInput cmsContractVo) {
        logger.info("Saving Contract");
        CmsContractVo vo = null;
        try {
            Contract contract = null;
            if (cmsContractVo.getId() == null) {
                logger.debug("Adding new contract");
                contract = CommonUtil.createCopyProperties(cmsContractVo, Contract.class);
            } else {
                logger.debug("Updating existing contract");
                contract = this.contractRepository.findById(cmsContractVo.getId()).get();

                if (cmsContractVo.getVendorName() != null) {
                    contract.setVendorName(cmsContractVo.getVendorName());
                }
                if (cmsContractVo.getDurationOfContract() != null) {
                    contract.setDurationOfContract(cmsContractVo.getDurationOfContract());
                }
                if (cmsContractVo.getTypeOfOwnerShip() != null) {
                    contract.setTypeOfOwnerShip(cmsContractVo.getTypeOfOwnerShip());
                }
            }
            contract.setStartDate(cmsContractVo.getStrStartDate() != null ? DateFormatUtil.convertStringToLocalDate(cmsContractVo.getStrStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            contract.setEndDate(cmsContractVo.getStrEndDate() != null ? DateFormatUtil.convertStringToLocalDate(cmsContractVo.getStrEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            contract = this.contractRepository.save(contract);
            vo = CommonUtil.createCopyProperties(contract, CmsContractVo.class);
            vo.setStrStartDate(contract.getStartDate() != null ? DateFormatUtil.changeLocalDateFormat(contract.getStartDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setStrEndDate(contract.getEndDate() != null ? DateFormatUtil.changeLocalDateFormat(contract.getEndDate(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setExitCode(0L);
            if (cmsContractVo.getId() == null) {
                vo.setExitDescription("Contract is added successfully");
                logger.debug("Contract is added successfully");
            } else {
                vo.setExitDescription("Contract is updated successfully");
                logger.debug("Contract is updated successfully");
            }


        } catch (Exception e) {
            vo = new CmsContractVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, contract data not be saved");
            logger.error("Contract save failed. Exception : ", e);
        }
        logger.info("Contract saved successfully");
        List ls = getContractList();
        vo.setDataList(ls);
        return vo;
    }
}
