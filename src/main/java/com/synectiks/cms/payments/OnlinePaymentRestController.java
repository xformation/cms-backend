package com.synectiks.cms.payments;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.domain.QueryResult;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.web.rest.errors.BadRequestAlertException;
import com.synectiks.cms.web.rest.util.HeaderUtil;

/**
 * REST controller for managing online payment message data.
 */
@RestController
@RequestMapping("/api")
public class OnlinePaymentRestController {

    private final Logger logger = LoggerFactory.getLogger(OnlinePaymentRestController.class);
    
    @RequestMapping(method = RequestMethod.GET, value = "/cmspayment/{entity}")
    public ResponseEntity<QueryResult> getMessage(@PathVariable String entity) throws ParseException, Exception {
        logger.debug("REST request to get payment message : "+entity);
        if (CommonUtil.isNullOrEmpty(entity)) {
            throw new BadRequestAlertException("Null/blank entity is not allowed", entity, "null/blank entity");
        }
        String message = createMessage(entity);

        QueryResult rs = new QueryResult();
        rs.setStatusCode(HttpStatus.OK.value());
        rs.setStatusDesc(message);
        
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert("", ""))
                .body(rs);
    }

    private String createMessage(String amt) throws NoSuchAlgorithmException, InvalidKeyException {
    	String m = CmsConstants.MERCHANT_ID+"|"+UniqueOrderNo.getUniqueOrderNo()+"|"+"NA"+"|"+amt+"|NA|NA|NA|INR|NA|R|"+CmsConstants.SECURITY_ID+"|NA|NA|F|NA|NA|NA|NA|NA|NA|NA|"+CmsConstants.RESPONSE_URL;
    	String chkSum = CheckSumGenerator.getCheckSum(m);
    	String msg = m+"|"+chkSum;
    	logger.debug("Payment message : "+msg);
    	return msg;
    }
    
  
    
    
}
