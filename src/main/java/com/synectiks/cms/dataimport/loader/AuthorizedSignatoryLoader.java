package com.synectiks.cms.dataimport.loader;

import java.util.Optional;

import org.dhatim.fastexcel.reader.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Example;

import com.synectiks.cms.dataimport.AllRepositories;
import com.synectiks.cms.dataimport.DataLoader;
import com.synectiks.cms.entities.AuthorizedSignatory;
import com.synectiks.cms.entities.Branch;
import com.synectiks.cms.entities.College;
import com.synectiks.cms.service.util.CommonUtil;


public class AuthorizedSignatoryLoader extends DataLoader {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private AllRepositories allRepositories;
	private String sheetName;
	
	public AuthorizedSignatoryLoader(String sheetName, AllRepositories allRepositories) {
		super(sheetName, allRepositories);
		this.allRepositories = allRepositories;
		this.sheetName = sheetName;
	}
	
	@Override
	public <T> T getObject(Row row, Class<T> cls) throws InstantiationException, IllegalAccessException {
		AuthorizedSignatory obj = CommonUtil.createCopyProperties(cls.newInstance(), AuthorizedSignatory.class);
		obj.setSignatoryName(row.getCellAsString(0).orElse(null));
        obj.setSignatoryFatherName(row.getCellAsString(1).orElse(null));
        obj.setSignatoryDesignation(row.getCellAsString(2).orElse(null));
        obj.setAddress1(row.getCellAsString(3).orElse(null));
		obj.setAddress2(row.getCellAsString(4).orElse(null));
		obj.setAddress3(row.getCellAsString(5).orElse(null));
		obj.setAddress4(row.getCellAsString(6).orElse(null));
		obj.setAddress5(row.getCellAsString(7).orElse(null));
		obj.setEmail(row.getCellAsString(8).orElse(null));
        obj.setPanCardNumber(row.getCellAsString(9).orElse(null));
        
		String branchName = row.getCellAsString(10).orElse(null);
		String branchAddress = row.getCellAsString(11).orElse(null);
		if(!CommonUtil.isNullOrEmpty(branchName) && !CommonUtil.isNullOrEmpty(branchAddress)) {
			Branch branch = new Branch();
			branch.setBranchName(branchName);
			branch.setAddress1(branchAddress);
			Optional<Branch> b = this.allRepositories.findRepository("branch").findOne(Example.of(branch));
			obj.setBranch(b.isPresent() ? b.get() : null);
		}
        
        String collegeName = row.getCellAsString(12).orElse(null);
		if(!CommonUtil.isNullOrEmpty(collegeName)) {
			College college = new College();
			college.setShortName(collegeName);
			Optional<College> c = this.allRepositories.findRepository("college").findOne(Example.of(college));
			obj.setCollege(c.isPresent() ? c.get() : null);
		}

		return (T)obj;
	}
	
}
